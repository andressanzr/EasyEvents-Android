package es.easyevents.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.easyevents.Activity.EventoDetail;
import es.easyevents.Adaptador.EventoAdaptador;
import es.easyevents.Data.ApiUtils;
import es.easyevents.Data.MailInterface;
import es.easyevents.Models.Evento;
import es.easyevents.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventoFragment extends Fragment {

    @BindView(R.id.list_event)
    ListView listViewPeliculas;

    private EventoAdaptador eventoAdaptador;
    private MailInterface mAPIService;

    public static List<Evento> eventos;
    public static final String CODIGO = "CODIGO";

    public EventoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_email, container, false);
        ButterKnife.bind(this, view);
        mAPIService = ApiUtils.getAPIService();
        insertarAdaptador();
        cargarEvento();
        return  view;
    }
    private void insertarAdaptador() {
        //Crear adaptador
        eventoAdaptador = new EventoAdaptador(getActivity(), new ArrayList<Evento>());
        listViewPeliculas.setAdapter(eventoAdaptador);
        //Evento click en item de la lista
        listViewPeliculas.setOnItemClickListener((adapterView, view, i, l) -> {
            //Se lanza la Activity de detalles del evento
            Log.e("Errores", "Creando itent");
            Intent intent = new Intent(getActivity(), EventoDetail.class);
            intent.putExtra(CODIGO, ((Evento) eventoAdaptador.getItem(i)).getCodigo());
            startActivity(intent);
      //      getFragmentManager().beginTransaction().remove(this).commit();
//verificar com eliminar el fragment
        });

    }

    public void cargarEvento(){
        mAPIService.obtenerEvento().enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if (response.body().size() == 0)
                    Toast.makeText(getActivity(),"NO EXISTE LA PELICULA", Toast.LENGTH_SHORT).show();
                else

                    mostrarEvento(response.body());
                Log.v("Error al Cargar",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error al conectar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void mostrarEvento(List<Evento> eventos){
        eventoAdaptador.EditEvent(eventos);
        Log.v("Errores::", "Se ha actualizado el adaptador.");
    }


}