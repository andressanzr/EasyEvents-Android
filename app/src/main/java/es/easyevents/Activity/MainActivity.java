package es.easyevents.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import es.easyevents.R;

public class MainActivity extends AppCompatActivity {
    private Button crear;
    private Button invitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        crear = (Button) findViewById(R.id.botonCrear);
        invitado = (Button) findViewById(R.id.botonInvitado);

        invitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modificar = new Intent(MainActivity.this, EventoGETbuscar.class);
                startActivity(modificar);

            }
        });
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modificar = new Intent(MainActivity.this, Login.class);
                startActivity(modificar);

            }
        });
    }
}
