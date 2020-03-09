package es.easyevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Registrar extends AppCompatActivity {
    private Button IniciarSesion;
    private Button registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        IniciarSesion = (Button) findViewById(R.id.botonIniciarSesion);
        registro = (Button) findViewById(R.id.botonCrearCuenta);

        IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IniciarSesion = new Intent(Registrar.this, Login.class);
                startActivity(IniciarSesion);
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loggy = new Intent(Registrar.this, CrearEvento.class);

                    Toast.makeText(Registrar.this, "Te has registrado correctamente, bienvenido", Toast.LENGTH_LONG).show();
                }

        });
    }

}