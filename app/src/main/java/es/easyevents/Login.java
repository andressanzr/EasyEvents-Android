package es.easyevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private Button login;
    private Button registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.botonLogin);
        registro = (Button) findViewById(R.id.botonRegistro);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loggy = new Intent(Login.this, CrearEvento.class);
                startActivity(loggy);

                Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_LONG).show();

            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loggy = new Intent(Login.this, CrearEvento.class);
                startActivity(loggy);

                Toast.makeText(Login.this, "Te has registrado correctamente, bienvenido", Toast.LENGTH_LONG).show();

            }
        });
    }
}
