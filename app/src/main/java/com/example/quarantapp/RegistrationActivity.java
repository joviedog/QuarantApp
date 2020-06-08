package com.example.quarantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    // Declara una instancia de autenticación con Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        // Crea una instancia de autenticacion para el usuario
        mAuth = FirebaseAuth.getInstance();

        Button register = findViewById(R.id.botonCrearUser);
        // Inicia el registro del usuario usando su correo electronico
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText email = findViewById(R.id.emailRegister);
                final String email_content = email.getText().toString();
                final EditText password = findViewById(R.id.passwordRegistration);
                final String password_content = password.getText().toString();
                // Verifica que los datos no sean nulos
                if (email_content.equals("") || password_content.equals("")){
                    Toast.makeText(RegistrationActivity.this, "Llenar los campos", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(email_content, password_content)
                            .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Informa de la creacion y arranca la actividad principal de la app
                                        Toast.makeText(RegistrationActivity.this, "Cuenta Creada", Toast.LENGTH_SHORT).show();
                                        Intent intent_reg = new Intent(RegistrationActivity.this, HomeMenuActivity.class);
                                        startActivity(intent_reg);
                                    } else {
                                        // Informa de la imposibilidad
                                        Toast.makeText(RegistrationActivity.this, "Cuenta No Creada. Email ya existente, prueba loggearte", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }

            }
        });






        }
}
