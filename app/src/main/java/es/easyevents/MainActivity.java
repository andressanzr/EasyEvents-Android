package es.easyevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button crear;
    private Button modificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crear = (Button) findViewById(R.id.botonCrear);
        modificar = (Button) findViewById(R.id.botonModificar);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crear = new Intent(MainActivity.this, PortadaLogin.class);
                startActivity(crear);
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modificar = new Intent(MainActivity.this, ModificarEvento.class);
                startActivity(modificar);

            }
        });
    }
}
