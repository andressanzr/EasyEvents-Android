package es.easyevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PortadaLogin extends AppCompatActivity {
    private Button IniciarSesion;
    private Button IniciarSesionFacebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada_login);
        IniciarSesion = (Button) findViewById(R.id.botonLogin);
        IniciarSesionFacebook = (Button) findViewById(R.id.botonRegistro);

        IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IniciarSesion = new Intent(PortadaLogin.this, Login.class);
                startActivity(IniciarSesion);
            }
        });

        IniciarSesionFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IniciarSesionFacebook = new Intent(PortadaLogin.this, Login.class);
                startActivity(IniciarSesionFacebook);

            }
        });
    }
}
