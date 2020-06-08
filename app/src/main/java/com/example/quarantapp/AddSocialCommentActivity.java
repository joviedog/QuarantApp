package com.example.quarantapp;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class AddSocialCommentActivity extends AppCompatActivity {
    // Variables para cada uno de los items de la interfaz de usuario, luego se les asignará
    // un valor en especial.
    private EditText mCommentTitle;
    private EditText mCommentContent;
    private Button mUploadCommentButton;
    // Referencias e instancias de acceso a la base de datos en tiempo real dispuesta en Firebase
    private DatabaseReference mDatabaseRef;

    // Obtiene el ID unico de usuario
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userKey = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_social_comment);
        // Selecciona y asigna directamente a las variables un componente usando su ID.
        mCommentTitle = findViewById(R.id.commentTitle);
        mCommentContent = findViewById(R.id.commentContent);
        mUploadCommentButton= findViewById(R.id.uploadCommentButton);

        // Instancias de Firebase Database
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        // Añade un listener para el boton de subir comentario
        mUploadCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadComment();
                Toast.makeText(AddSocialCommentActivity.this, "Publicando Comentario", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void uploadComment(){
        // Instancia un objeto de tipo comentario con los datos obtenidos del UI
        Comentario upcom = new Comentario(
                mCommentTitle.getText().toString(),
                mCommentContent.getText().toString(), userKey);
        // Le asigna un ID unico a cada comentario que se sube
        mDatabaseRef.child("Comentarios").child(System.currentTimeMillis() + "cmt").setValue(upcom);
    }
}
