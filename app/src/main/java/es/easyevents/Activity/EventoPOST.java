package es.easyevents.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.easyevents.Data.ApiUtils;
import es.easyevents.Data.MailInterface;
import es.easyevents.Fragments.EventoFragment;
import es.easyevents.Models.Evento;
import es.easyevents.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class EventoPOST extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.codigo)
    EditText codigo;
    @BindView(R.id.imagen)
    EditText foto;
    @BindView(R.id.eventoName)
    EditText titulo;
    @BindView(R.id.personName)
    EditText afitrion;
    @BindView(R.id.mostarfecha)
    EditText fecha;
    @BindView(R.id.horaEvent)
    EditText hora;
    @BindView(R.id.mostarLugar)
    EditText lugar;
    @BindView(R.id.eventoMessage)
    EditText descripcion;

    static String snombreevento;
    static String smensaevento;
    static String snombreperson;
    static String shoraevento;
    static String sfecha;
    static String slugar;
    @BindView(R.id.eventCreado)
    Button crearEvento;
    private Calendar date;
    TimePickerDialog TPD;
    Calendar c;
    DatePickerDialog dpd;
    @BindView(R.id.botonFecha)
    Button fechas;
    @BindView(R.id.botonHora)
    Button bhora;
    @BindView(R.id.irMapa)
    Button mapa;
    private Address address;
    private MailInterface mAPIService;
    //private EventoFragment eventoFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_creando);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getAPIService();

        crearEvento.setOnClickListener(this);
        //instancia con el servidor para que se guarde los datos

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            address = (Address) bundle.get("address");
        }
        c = Calendar.getInstance();
        final int dia = c.get(Calendar.DAY_OF_MONTH);
        final int mes = c.get(Calendar.MONTH);
        final int anno = c.get(Calendar.YEAR);
        final int horas = c.get(Calendar.HOUR_OF_DAY);
        final int minutos = c.get(Calendar.MINUTE);
        //muestra el mapa en el activitymaps
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(EventoPOST.this, ActivityMaps.class);
                startActivity(map);
                Bundle parametros = new Bundle();
                map.putExtras(parametros);
                sfecha=fecha.getText().toString();
                slugar=lugar.getText().toString();
                snombreevento=titulo.getText().toString();
                smensaevento=descripcion.getText().toString();
                snombreperson=afitrion.getText().toString();
                shoraevento=hora.getText().toString();

            }
        });



        bhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //
                TPD = new TimePickerDialog(EventoPOST.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora.setText(hourOfDay + ":" + minute);
                    }
                }, horas, minutos, false);
                TPD.show();

            }
        });
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker();

            }
        });

    }
    @Override
    public void onClick(View view) {
        if(!comprobarCampos()){
            Evento evento = new Evento(Integer.parseInt(codigo.getText().toString()),foto.getText().toString(),
                    titulo.getText().toString(),descripcion.getText().toString(),afitrion.getText().toString(),fecha.getText().toString(),
                    hora.getText().toString(),lugar.getText().toString());
            enviarEvento(evento);
            Log.v("Errores",evento.toString());
        }
    }

    private void enviarEvento(final Evento evento) {
        mAPIService.guardarPost(evento).enqueue(new Callback<Evento>() {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                if (response.isSuccessful()){
                    Log.i("UI enviarEven", "post submitted to API." + response.body().toString());
                    Toast.makeText(getApplicationContext(), "Evento creado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intentListar = new Intent(getApplicationContext(), ActivityGet.class);
                    startActivity(intentListar);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                Log.e("UI senPost" , "error en el servicio");
                Toast.makeText(getApplicationContext(),
                        "ERROR EN EL SERVICIO", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                fecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                //use this date as per your requirement
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(EventoPOST.this, dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
    public boolean comprobarCampos() {
        boolean aux = false;

        if (codigo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Obligatorio CÓDIGO", Toast.LENGTH_SHORT).show();
            aux = true;
        }

        if (titulo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Obligatorio TÍTULO", Toast.LENGTH_SHORT).show();
            aux = true;
        }

        if (afitrion.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Obligatorio ANFITRIÓN", Toast.LENGTH_SHORT).show();
            aux = true;
        }

        if (fecha.getText().toString().isEmpty()) {
            Toast.makeText(this, "Obligatorio FECHA", Toast.LENGTH_SHORT).show();
            aux = true;
        }

        if (lugar.getText().toString().isEmpty()) {
            Toast.makeText(this, "Obligatorio LUGAR", Toast.LENGTH_SHORT).show();
            aux = true;
        }

        return aux;
    }

}
