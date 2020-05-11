package es.easyevents.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.easyevents.Adaptador.EventoAdaptador;
import es.easyevents.Data.ApiUtils;
import es.easyevents.Data.MailInterface;
import es.easyevents.Models.Evento;
import es.easyevents.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityGet extends AppCompatActivity {

    @BindView(R.id.listar_even)
    ListView listViewPeliculas;

    private EventoAdaptador eventoAdaptador;
    private MailInterface mAPIService;

    public static List<Evento> eventos;
    public static final String CODIGO = "CODIGO";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_pelicula);
        ButterKnife.bind(this);

        mAPIService = ApiUtils.getAPIService();

        insertarAdaptador();
        cargarEvento();
    }
    private void insertarAdaptador() {
        //Crear adaptador
        eventoAdaptador = new EventoAdaptador(getApplicationContext(), new ArrayList<Evento>());
        listViewPeliculas.setAdapter(eventoAdaptador);
        //Evento click en item de la lista
        listViewPeliculas.setOnItemClickListener((adapterView, view, i, l) -> {
            //Se lanza la Activity de detalles del evento
            Log.e("Errores", "Creando itent");
            Intent intent = new Intent(getBaseContext(), EventoDetail.class);
            intent.putExtra(CODIGO, ((Evento) eventoAdaptador.getItem(i)).getCodigo());
            startActivity(intent);
            finish();
        });

    }

    public void cargarEvento(){
        mAPIService.obtenerEvento().enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if (response.body().size() == 0)
                    Toast.makeText(getApplicationContext(),"NO EXISTE LA PELICULA", Toast.LENGTH_SHORT).show();
                else

                    mostrarEvento(response.body());
                Log.v("Error al Cargar",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Toast.makeText(ActivityGet.this, "Error al conectar", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void mostrarEvento(List<Evento> eventos){
        eventoAdaptador.EditEvent(eventos);
        Log.v("Errores::", "Se ha actualizado el adaptador.");
    }

}
