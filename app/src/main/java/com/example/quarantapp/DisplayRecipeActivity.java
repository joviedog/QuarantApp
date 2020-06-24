package com.example.quarantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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
        String userID = getIntent().getStringExtra("Usuario");

        // Objetos de la interfaz
        TextView mRecipeTitle = findViewById(R.id.nombreRecetaDisplay);
        TextView mRecipeSteps = findViewById(R.id.pasosRecetaDisplay);
        ImageView mRecipeImage = findViewById(R.id.imagenRecetaDisplay);
        ConstraintLayout mRecipeLayout = findViewById(R.id.deleteRecipeLayout);

        // Llena el layout con los datos obtenidos del Intent
        mRecipeTitle.setText(titulo);
        mRecipeSteps.setText(pasos);
        Picasso.with(DisplayRecipeActivity.this)
                .load(img_url)
               .fit()
               .centerCrop()
               .into(mRecipeImage);

        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(userID)){
            Button button = new Button(this);
            button.setText("Eliminar");
            button.setBackgroundResource(R.drawable.circlebuttonbgred);
            button.setTextColor(Color.WHITE);
            button.setLayoutParams(new
                    ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DisplayRecipeActivity.this, "Eliminando Receta",
                            Toast.LENGTH_SHORT).show();

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    Query currentItemQuery = ref.child("Recetas").orderByChild("mName");

                    currentItemQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot itemSnapshot : dataSnapshot.getChildren()){
                                itemSnapshot.getRef().removeValue();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(DisplayRecipeActivity.this, databaseError.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            if (mRecipeLayout != null){
                mRecipeLayout.addView(button);
            }
        }
    }
}
