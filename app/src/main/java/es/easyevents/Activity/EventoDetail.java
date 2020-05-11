package es.easyevents.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.easyevents.Data.ApiUtils;
import es.easyevents.Data.MailInterface;
import es.easyevents.Fragments.EventoFragment;
import es.easyevents.Models.Evento;
import es.easyevents.R;
import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventoDetail extends AppCompatActivity  implements View.OnClickListener {
    @BindView(R.id.detalle_titulo)
    TextView tvTitulo;
    @BindView(R.id.detalle_codigo)
    TextView codigo;
    @BindView(R.id.detalle_Anfitrion)
    TextView anfitrion;
    @BindView(R.id.detalle_Hora)
    TextView hora;
    @BindView(R.id.detalle_fecha)
    TextView fecha;
    @BindView(R.id.detalle_Lugar)
    TextView lugar;
    @BindView(R.id.detalle_descripcion)
    TextView descripcion;
    @BindView(R.id.detalle_imagen)
    ImageView imagen;

    @BindView(R.id.btn_borrar)
    Button borrar;
    @BindView(R.id.btn_actualizar)
    Button actualizar;

    private MailInterface mAPIService;
    private int codigoPeli;
    private Evento evento;
    public static final String CODIGO = "CODIGO";
    private static final String EVEN = "EVEN";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detail);
        ButterKnife.bind(this);

        mAPIService = ApiUtils.getAPIService();

        borrar.setOnClickListener(this);
        actualizar.setOnClickListener(this);

        codigoPeli = getIntent().getExtras().getInt(CODIGO);
        enviarCodigo(codigoPeli);

        Log.v("Error"," "+codigoPeli);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_borrar:
                AlertDialog.Builder alerta = new AlertDialog.Builder(this); // Necesita el contexto de la activity no vale el de AppContexto
                alerta.setTitle("Borrar.")
                        .setMessage("¿Está seguro de borrar la película?")
                        .setCancelable(true)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                borrarPelicula(codigoPeli);
                                startActivity(new Intent(getApplicationContext(), EventoFragment.class));
                                finish();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Borrado cancelado.", Toast.LENGTH_SHORT).show();
                            }
                        });
                alerta.show();
                break;

            case R.id.btn_actualizar:
                Intent intent = new Intent(getApplicationContext(),EventoPUT.class);
                intent.putExtra(EVEN, evento);
                startActivity(intent);
                finish();
                break;
        }
    }

    public void mostrarDetallePelicula(Evento event){
        if(event != null){
            evento = event;
            tvTitulo.setText(String.valueOf(event.getTitulo()));
            codigo.setText(String.valueOf(event.getCodigo()));
            anfitrion.setText(String.valueOf(event.getAnfitrion()));
            hora.setText(String.valueOf(event.getHora()));
            fecha.setText(String.valueOf(event.getFecha()));
            lugar.setText(String.valueOf(event.getDireccion()));
            descripcion.setText(String.valueOf(event.getDescripcion()));
            Glide.with(this).load(event.getImagen()).into(imagen);

        }
    }

    public void enviarCodigo(int codigo){
        mAPIService.listarEvento(codigo).enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if (response.isSuccessful()){
                    mostrarDetallePelicula(response.body().get(0));
                    if (response.body().size() == 0){
                        Toast.makeText(getApplicationContext(),
                                "NO EXISTE LA PELICULA", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Log.e("UI senPost" , "error en el servicio");
                Toast.makeText(getApplicationContext(),
                        "ERROR EN EL SERVICIO", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void borrarPelicula(int codigo) {
        mAPIService.borrarEvento(codigo).enqueue(new Callback<Evento>() {

            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                Toast.makeText(getApplicationContext(), "Borrado correctamente", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error al borrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
