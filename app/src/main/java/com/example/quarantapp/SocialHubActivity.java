package com.example.quarantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;
import java.util.List;

public class SocialHubActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CommentAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private List<Comentario> mComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_hub);
        // Configura el uso basico para el recycler view
        mRecyclerView = findViewById(R.id.recycler_view_social);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager((this)));
        // Inicia una lista para guardar los comentarios
        mComments= new ArrayList<>();
        // Accede a la base de datos
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Comentarios");

        // Inicia un listener con respecto a la base de datos que ha iniciado su proceso de obtencion
        // de datos
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            // Si los datos cambian (Se añade un comentario) se actualiza la lista de datos y se recarga
            // con la nueva actualizacion
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot commentSnapshot : dataSnapshot.getChildren()){
                    Comentario comment = commentSnapshot.getValue(Comentario.class);
                    mComments.add(comment);
                }
                // Inicia el adaptador creado en la clase CommentAdapter
                mAdapter = new CommentAdapter(SocialHubActivity.this, mComments);
                mRecyclerView.setAdapter(mAdapter);
            }
            // Si hay un error informa del mismo
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SocialHubActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    // Inicia la pantalla para añadir un nuevo comentario
    public void AddCommentActivity(View view){
        Intent intent = new Intent(SocialHubActivity.this, AddSocialCommentActivity.class);
        startActivity(intent);
    }
}
