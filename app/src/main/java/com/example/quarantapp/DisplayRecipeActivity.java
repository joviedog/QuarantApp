package com.example.quarantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DisplayRecipeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipe);
        // Obtiene cada uno de los datos provenientes del intent de la actividad anterior.
        String titulo = getIntent().getStringExtra("Titulo");
        String img_url = getIntent().getStringExtra("Imagen");
        String pasos = getIntent().getStringExtra("Pasos");
        Log.d("PASOS_DATOS", "PASOS" + pasos);

        // Objetos de la interfaz
        TextView mRecipeTitle = findViewById(R.id.nombreRecetaDisplay);
        TextView mRecipeSteps = findViewById(R.id.pasosRecetaDisplay);
        ImageView mRecipeImage = findViewById(R.id.imagenRecetaDisplay);

        // Llena el layout con los datos obtenidos del Intent
        mRecipeTitle.setText(titulo);
        mRecipeSteps.setText(pasos);
        Picasso.with(DisplayRecipeActivity.this)
                .load(img_url)
               .fit()
               .centerCrop()
               .into(mRecipeImage);

    }
}
