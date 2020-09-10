package es.easyevents.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import es.easyevents.Adaptador.Adaptador;
import es.easyevents.R;
import es.easyevents.Models.TiposEventoFoto;

public class CrearEvento extends AppCompatActivity {
    private GridView gridView;
    private ArrayList<TiposEventoFoto> listaTiposEventoFotos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        //Lista con los diferentes tipos de eventos que tiene la aplicación
        listaTiposEventoFotos = new ArrayList<>();
        listaTiposEventoFotos.add(new TiposEventoFoto("Cumpleaños", R.drawable.fiestasinfantil));
        listaTiposEventoFotos.add(new TiposEventoFoto("Boda", R.drawable.boda));
        listaTiposEventoFotos.add(new TiposEventoFoto("Despedida de soltero", R.drawable.despedidasoltero));
        listaTiposEventoFotos.add(new TiposEventoFoto("Fiesta", R.drawable.fiestayoung));
        listaTiposEventoFotos.add(new TiposEventoFoto("Baby Shower", R.drawable.babyshower));

        //grid donde se almacenará las imagenes
        gridView = (GridView) findViewById(R.id.gridlayout);
        Adaptador adaptador = new Adaptador(this.getApplicationContext(), listaTiposEventoFotos);
        gridView.setAdapter(adaptador);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent event = new Intent(CrearEvento.this, EventoCreando.class);
                String tipoEventoBBDD = "";
                switch (listaTiposEventoFotos.get(i).getTipoEvento()) {
                    case "Cumpleaños":
                        tipoEventoBBDD = "cumpleanos";
                        break;
                    case "Boda":
                        tipoEventoBBDD = "boda";
                        break;
                    case "Despedida de soltero":
                        tipoEventoBBDD = "despedidadSoltero";
                        break;
                    case "Fiesta":
                        tipoEventoBBDD = "fiesta";
                        break;
                    case "Baby Shower":
                        tipoEventoBBDD = "babyShower";
                        break;
                }
                event.putExtra("tipoEvento", tipoEventoBBDD);
                startActivity(event);
                finish();
            }
        });


    }
}
