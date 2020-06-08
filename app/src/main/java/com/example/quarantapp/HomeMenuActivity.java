package com.example.quarantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        // Recetas OnClick
        ConstraintLayout recipes = (ConstraintLayout) findViewById(R.id.recipes);
        recipes.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View v) {
                // Inicia la actividad para mostrar las recetas disponibles.
                Intent intent_reg = new Intent(HomeMenuActivity.this, RecipeHubActivity.class);
                startActivity(intent_reg);
            }
        });
        // Calendario on Click
        ConstraintLayout calendar = (ConstraintLayout) findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View v) {
                // Inicia la actividad de Calendario y recordatorios
                // TO DO TO DO TO DO TO DO
                Toast.makeText(HomeMenuActivity.this, "Calendario", Toast.LENGTH_LONG).show();
            }
        });
        // Social On Click
        ConstraintLayout social = (ConstraintLayout) findViewById(R.id.social);
        social.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View v) {
                // Inicial la actividad de comentarios
                Intent intent_reg = new Intent(HomeMenuActivity.this, SocialHubActivity.class);
                startActivity(intent_reg);
            }
        });
    }

}
