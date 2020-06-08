package com.example.quarantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RecipeHubActivity extends AppCompatActivity implements ImageAdapter.OnItemClicked {
    // Variables para items del UI
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private ProgressBar mProgressCircle;
    // Inicia la instancia de acceso a la base de datos y crea una lista para su almacenamiento
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_hub);
        // Inicia el componente principal del layout que es un recycler view.
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager((this)));

        mProgressCircle = findViewById(R.id.progress_circle);

        // Inicia la lista de recetas
        mUploads = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Recetas");

        // Inicia un listener con respecto a la base de datos que ha iniciado su proceso de obtencion
        // de datos
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            // Si los datos cambian (Se añade una receta) se actualiza la lista de datos y se recarga
            // con la nueva actualizacion
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot recipeSnapshot : dataSnapshot.getChildren()){
                    Upload upload = recipeSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }
                // Inicia el adaptador que se creo en la clase ImageAdapter para popular el recyclerview
                mAdapter = new ImageAdapter(RecipeHubActivity.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);

                mAdapter.setOnClick(RecipeHubActivity.this);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            // Si existe un error en la bajada de los datos se muestra el error que se obtuvo
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RecipeHubActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

    }
    // Crea un intent para añadir una receta adicional
    public void AddRecipeActivity(View view){
        Intent intent = new Intent(RecipeHubActivity.this, AddRecipeActivity.class);
        startActivity(intent);
    }

    // Crea una funcionalidad de onClick para cada item de la lista.
    @Override
    public void onItemClick(int position) {
        Intent display_recipe = new Intent(RecipeHubActivity.this, DisplayRecipeActivity.class);
        // Envia datos en el intent
        display_recipe.putExtra("Titulo", mUploads.get(position).getmName());
        display_recipe.putExtra("Imagen", mUploads.get(position).getmImageUrl());
        display_recipe.putExtra("Pasos", mUploads.get(position).getmSteps());
        startActivity(display_recipe);
    }
}

