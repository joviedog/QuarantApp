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

public class LoginActivity extends AppCompatActivity {
    // Declara una instancia de autenticación con Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Instancia de Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        Button login = findViewById(R.id.botonLogUser);
        // Añade un onClick Listener para el botón de loggeo a la aplicación
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene los datos del formulario
                final EditText email = findViewById(R.id.emailLogin);
                final EditText password = findViewById(R.id.passwordLogin);
                final String password_content = password.getText().toString();
                final String email_content = email.getText().toString();
                // Verifica que no estén vacios (Y también no nulos)
                if (password_content.equals("")  || email_content.equals("")) {
                    Toast.makeText(LoginActivity.this, "Llenar los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    // Inicia el servicio de autorización de entrada para el usuario
                    mAuth.signInWithEmailAndPassword(email_content, password_content)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Dispara la actividad del menu principal de la aplicacion
                                        Toast.makeText(LoginActivity.this, "Entrando...", Toast.LENGTH_SHORT).show();
                                        Intent intent_reg = new Intent(LoginActivity.this, HomeMenuActivity.class);
                                        startActivity(intent_reg);
                                    } else {
                                        // Informa del fallo en los datos
                                        Toast.makeText(LoginActivity.this, "Verifica tu contraseña o tu conexion a internet", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });


    }
}
