package es.easyevents.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.easyevents.Models.Evento;
import es.easyevents.R;
import io.reactivex.annotations.Nullable;

public class EventoDetail extends AppCompatActivity {
    @BindView(R.id.detalle_tipo)
    TextView tipo;
    @BindView(R.id.detalle_titulo)
    TextView tvTitulo;
    @BindView(R.id.detalle_codigo)
    TextView codigo;
    @BindView(R.id.detalle_Anfitrion)
    TextView anfitrion;
    @BindView(R.id.detalle_fecha)
    TextView fecha;
    @BindView(R.id.detalle_Lugar)
    TextView lugar;
    @BindView(R.id.detalle_descripcion)
    TextView descripcion;
    @BindView(R.id.detalle_fecha_creado)
    TextView fechaCreado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Evento evento = new Gson().fromJson(intent.getStringExtra("evento"), Evento.class);
        mostrarDetalleEvento(evento);
    }

    public void mostrarDetalleEvento(Evento event) {
        if(event != null){
            tipo.setText(event.getType());
            tvTitulo.setText(event.getName());
            codigo.setText(event.getPublicIdCode() + "");
            anfitrion.setText(event.getHostName());
            fecha.setText(event.getDate().toLocaleString());
            lugar.setText(event.getPlace());
            descripcion.setText(event.getMessage());
            fechaCreado.setText(event.getDateCreated().toLocaleString());
        }
    }




}
