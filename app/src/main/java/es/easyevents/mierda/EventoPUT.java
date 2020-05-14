package es.easyevents.mierda;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.easyevents.Data.ApiUtils;
import es.easyevents.Data.MailInterface;
import es.easyevents.Models.Evento;
import es.easyevents.R;
import io.reactivex.annotations.Nullable;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EventoPUT extends AppCompatActivity implements View.OnClickListener  {

    @BindView(R.id.eventoName)
    EditText tvTitulo;
    @BindView(R.id.personName)
    EditText anfitrion;
    @BindView(R.id.fechaEvent)
    EditText tvFecha;
    @BindView(R.id.horaEvent)
    EditText tvhora;
    @BindView(R.id.mostarLugar)
    EditText tvlugar;
    @BindView(R.id.eventoMessage)
    EditText tvDescripcion;

    @BindView(R.id.btnCrearEvento)
    Button btnActualizar;



    private MailInterface mAPIService;

    private String foto, titulo, anfitrio, fecha, hora, lugar , descripcion;

    private Evento evento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_creando);
        ButterKnife.bind(this);
        //conectamos con el servidor
        mAPIService = ApiUtils.getAPIService();

        evento = (Evento) getIntent().getSerializableExtra("EVEN");

        //cambia el titulo a actualizar evento
        setActivityTitle();
        btnActualizar.setText("Actualizar");
        btnActualizar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCrearEvento:
                //método para verificar los datos
                comprobarCampos();
                //actualiza el evento con los nuevos datos ingresados
                //actualizarEvento(new Evento(evento.getCodigo(), foto, titulo, descripcion, anfitrio,fecha, hora, lugar));
                //inicia el nuevo activity donde se vera la lista

        }
    }
    //método actualizar evento con los nuevos datos
    public void actualizarEvento(Evento evento) {
        //instancia con el servidor y hace llamado del callback
        /*
        mAPIService.modificarEvento(evento.getCodigo(), evento).enqueue(new Callback<Evento>(){
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                Toast.makeText(getApplicationContext(), "Pelicula actualizada correctamente", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
            }


        }); */
    }//cambia el titulo
    private void setActivityTitle() {
        String title = "Editar evento";
        setTitle(title);
    }//comprueba que los datos esten correctos
    public void comprobarCampos() {
/*
        if (imagen.getText().toString().isEmpty()) {
            foto = evento.getImagen();
        } else {
            foto = imagen.getText().toString();
        }

        if (tvTitulo.getText().toString().isEmpty()) {
            titulo = evento.getTitulo();
        } else {
            titulo = tvTitulo.getText().toString();
        }

        if (anfitrion.getText().toString().isEmpty()) {
            anfitrio = evento.getAnfitrion();
        } else {
            anfitrio = anfitrion.getText().toString();
        }

        if (tvFecha.getText().toString().isEmpty()) {
            fecha = String.valueOf(evento.getFecha());
        } else {
            fecha = tvFecha.getText().toString();
        }

        if (tvhora.getText().toString().isEmpty()) {
            hora = String.valueOf(evento.getHora());
        } else {
            hora = tvhora.getText().toString();
        }

        if (tvlugar.getText().toString().isEmpty()) {
            lugar  = String.valueOf(evento.getDireccion());
        } else {
            lugar  = tvlugar.getText().toString();
        }

        if (tvDescripcion.getText().toString().isEmpty()) {
            descripcion = evento.getDescripcion();
        } else {
            descripcion = tvDescripcion.getText().toString();
        }
        */
    }
}
