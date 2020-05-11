package es.easyevents.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.easyevents.Data.ApiUtils;
import es.easyevents.Data.MailInterface;
import es.easyevents.Models.Evento;
import es.easyevents.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class EventoGETbuscar extends AppCompatActivity  implements View.OnClickListener  {
    @BindView(R.id.botoncodigo)
    EditText buscarCodigo;
    @BindView(R.id.botonInvitado)
    Button buscar;
    @BindView(R.id.botonCrear)
    Button crear;
    @BindView(R.id.tv_response_get)
    TextView mostrar;
    TextView tvTitulo, codigo, anfitrion, hora, fecha, lugar, descripcion;
    Button btnActualizar, btnBorrar;
    ImageView foto;

    int codigoC;
    private Evento evento;
    private MailInterface mAPIService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//se instancia el servidor
        mAPIService = ApiUtils.getAPIService();
        buscar.setOnClickListener(this);
        crear = (Button) findViewById(R.id.botonCrear);
        //referencia el activiy de crear evento
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //redirecciona al activity para crear el evento
                Intent crear = new Intent(EventoGETbuscar.this, CrearEvento.class);
                startActivity(crear);
            }
        });
    }

    @Override
    public void onClick(View view) {
        //con el código introducido lo envia y lo busca
        codigoC = Integer.parseInt(buscarCodigo.getText().toString());
        enviarCodigo(codigoC);
    }

    public void enviarCodigo(int codigo){
        mAPIService.listarEvento(codigo).enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if (response.isSuccessful()){
                    motrarDetalleEven(response.body().get(0));
                    if (response.body().size() == 0)
                        Toast.makeText(getApplicationContext(),
                                "NO EXISTE El EVENTO", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Log.e("UI senPost" , "error en el servicio");
                t.printStackTrace();
                Toast.makeText(getApplicationContext(),"ERROR EN EL SERVICIO", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void motrarDetalleEven(Evento event){
        if(event != null){
            evento = event;
            setContentView(R.layout.activity_evento_detail);

            btnActualizar = findViewById(R.id.btn_actualizar);
            btnBorrar = findViewById(R.id.btn_borrar);

            tvTitulo = findViewById(R.id.detalle_titulo);
            codigo = findViewById(R.id.detalle_codigo);
            anfitrion= findViewById(R.id.detalle_Anfitrion);
            hora  = findViewById(R.id.detalle_Hora);
            lugar = findViewById(R.id.detalle_Lugar);
            fecha = findViewById(R.id.detalle_fecha);
            descripcion = findViewById(R.id.detalle_descripcion);
            foto = findViewById(R.id.detalle_imagen);

            tvTitulo.setText(String.valueOf(event.getTitulo()));
            codigo.setText(String.valueOf(event.getCodigo()));
            anfitrion.setText(String.valueOf(event.getAnfitrion()));
            hora.setText(String.valueOf(event.getHora()));
            lugar.setText(String.valueOf(event.getDireccion()));
            fecha.setText(String.valueOf(event.getFecha()));
            descripcion.setText(String.valueOf(event.getDescripcion()));
            Glide.with(getApplicationContext()).load(event.getImagen()).into(foto);

            btnActualizar.setVisibility(View.GONE);
            btnBorrar.setVisibility(View.GONE);

        }
    }
}
