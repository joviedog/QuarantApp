package com.example.quarantapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddRecipeActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    // Variables para cada uno de los items de la interfaz de usuario, luego se les asignará
    // un valor en especial.
    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private EditText mRecipeName;
    private EditText mRecipeShortDescription;
    private EditText mRecipeSteps;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    // Variable de tipo URI, permite acceder directamente al link de la imagen almacenado en
    // Firebase Firestore para renderizarlo en el UI.
    private Uri mImageUri;
    // Referencias e instancias de acceso a la base de datos en tiempo real dispuesta en Firebase
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    // Variable para almacenar el estado actual de la subida tanto a la base de datos como a
    // Firebase Firestore/
    private StorageTask mUploadTask;
    // Obtiene el ID unico de usuario
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userKey = user.getUid();
    /// SI FALLA BORRE AQUI ARRIBA DESDE OBTIENE ID UNICO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        // Selecciona y asigna directamente a las variables un componente usando su ID.
        mButtonChooseImage = findViewById(R.id.selectorImagen);
        mButtonUpload = findViewById(R.id.uploadRecipe);
        mRecipeName = findViewById(R.id.recipeName);
        mRecipeShortDescription = findViewById(R.id.recipeShortDescription);
        mRecipeSteps = findViewById(R.id.recipeSteps);
        mImageView = findViewById(R.id.imageFrame);
        mProgressBar = findViewById(R.id.progressBar);

        // Instancias de Firebase Storage y Firebase RealTime DB
        mStorageRef = FirebaseStorage.getInstance().getReference("Recetas");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Recetas");
        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Una vez se activa dispara la funcion de subida a Firebase
                uploadFile();

                // Verifica si ya hay una tarea de subida en curso, y bloquea el boton de subida
                // en caso de ser asi. Esto para evitar la duplicacion de imagenes en FireStore.
                if (mUploadTask != null && mUploadTask.isInProgress()){
                    Toast.makeText(AddRecipeActivity.this, "Iniciando subida", Toast.LENGTH_SHORT).show();
                }else{
                    uploadFile();
                }

            }
        });

    }

    private void openFileChooser() {
        // Abre el explorador de archivos del dispositivo y permite la selección de imagen
        Intent intent_file = new Intent();
        intent_file.setType("image/*");
        intent_file.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent_file, PICK_IMAGE_REQUEST);
    }

    // Verifica que se haya seleccionado una imagen y que se haya cargado correctamente.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data !=null && data.getData() !=null) {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }
    // Obtiene la extension de la imagen que se va a subir al almacenamiento
    private String getFileExtension(Uri uri){
        // Gets the extension for the file selected by the user
        ContentResolver cR = getContentResolver();
        MimeTypeMap  mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    // Sube el archivo de imagen y los datos de cada una de las recetas a Firebase
    private void uploadFile(){
        if (mImageUri != null){
            // Crea un nombre unico para la referencia del archivo con el proposito de evitar
            // duplicados de nombre que puedan causar fallos a futuro
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." +
                    getFileExtension(mImageUri));
            // Se preparan varios Listener que tienen como proposito responder a los posibles
            // eventos que ocurran durante la subida.
            mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Se añade un delay para que el usuario pueda interactuar y ver en el UI que
                    // su foto está subiendo.
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(0);
                        }
                    }, 500);
                Toast.makeText(AddRecipeActivity.this, "Subida Exitosa", Toast.LENGTH_LONG).show();
                Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!urlTask.isSuccessful());
                Uri downloadUri = urlTask.getResult();
                // Instancia un objeto de tipo Upload que contiene los datos de la receta con los
                    // valores obtenidos de la interfaz
                Upload upload = new Upload(mRecipeName.getText().toString().trim(),
                        mRecipeShortDescription.getText().toString(),
                        mRecipeSteps.getText().toString(),
                        downloadUri.toString(), userKey);
                // Guarda el archivo en la base de datos
                String uploadId = mDatabaseRef.push().getKey();
                mDatabaseRef.child(uploadId).setValue(upload);

                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Si la subida falla, se informa al usuario del fallo
                    Toast.makeText(AddRecipeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            })
            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    // A medida que se va subiendo el archivo se actualiza la barra de progreso.
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    mProgressBar.setProgress((int) progress);
                }
            });
        }else{
            // Verifica que si se haya seleccionado una imagen antes de subir un puntero nulo.
            Toast.makeText(this, "No file Selected", Toast.LENGTH_SHORT).show();
        }
    }
}
