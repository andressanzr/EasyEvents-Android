package es.easyevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CrearEvento extends AppCompatActivity {
    private GridView gridView;
    private ArrayList<TiposEventoFoto> listaTiposEventoFotos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);
        listaTiposEventoFotos = new ArrayList<>();
        listaTiposEventoFotos.add(new TiposEventoFoto("Fiesta Infantil", R.drawable.fiestasinfantil));
        listaTiposEventoFotos.add(new TiposEventoFoto("Fiesta Young", R.drawable.fiestayoung));
        listaTiposEventoFotos.add(new TiposEventoFoto("Baby Shower", R.drawable.babyshower));

        gridView = (GridView) findViewById(R.id.gridlayout);
        Adaptador adaptador = new Adaptador(this.getApplicationContext(), listaTiposEventoFotos);
        gridView.setAdapter(adaptador);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent event = new Intent(CrearEvento.this, EventoCreando.class);
                startActivity(event);

            }
        });


    }
}
