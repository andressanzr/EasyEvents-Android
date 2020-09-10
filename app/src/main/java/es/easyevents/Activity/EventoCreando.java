package es.easyevents.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import es.easyevents.Data.ApiUtils;
import es.easyevents.Data.MailInterface;
import es.easyevents.Models.Evento;
import es.easyevents.Models.ServerResponseNode;
import es.easyevents.Models.ServerResponseNodeEventSave;
import es.easyevents.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventoCreando extends AppCompatActivity {
    private String stipoevento;
    private String snombreevento;
    private String smensajevento;
    private String snombreperson;
    private String slugar;
    private Calendar dateEvent;

    private Button fecha, bhora;

    private Button mapa;
    private EditText nombreEvento;
    private EditText mensaEvento;
    private EditText nombrePerson;
    private EditText horaEvento;

    private TextView btnPrivacidad;

    private EditText mfecha;
    private EditText mLugar;
    private TimePickerDialog TPD;
    private Calendar c;
    private DatePickerDialog dpd;

    private Address address;
    private Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_creando);

        dateEvent = Calendar.getInstance();
        stipoevento = getIntent().getStringExtra("tipoEvento");
        //Toast.makeText(EventoCreando.this, "tipo" + stipoevento, Toast.LENGTH_LONG).show();

        References();
        //Instancia un calendario con el día y mes
        c = Calendar.getInstance();
        final int dia = c.get(Calendar.DAY_OF_MONTH);
        final int mes = c.get(Calendar.MONTH);
        final int anno = c.get(Calendar.YEAR);
        final int hora = c.get(Calendar.HOUR_OF_DAY);
        final int minutos = c.get(Calendar.MINUTE);

        //muestra el mapa en el activitymaps
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(EventoCreando.this, ActivityMaps.class);
                startActivityForResult(map, 0);
            }
        });

        btnPrivacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventoCreando.this, Privacidad.class);
                startActivity(intent);
            }
        });

        // saves the event
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidData()) {
                    //guardar en BBDD
                    slugar = mLugar.getText().toString();
                    snombreevento = nombreEvento.getText().toString();
                    smensajevento = mensaEvento.getText().toString();
                    snombreperson = nombrePerson.getText().toString();
                    Date date = dateEvent.getTime();

                    MailInterface apiServ = ApiUtils.getAPIService();
                    // POST guarda evento en BBDD
                    Call<ServerResponseNodeEventSave> call = apiServ.guardarPost(stipoevento, snombreevento, smensajevento, date, date, snombreperson, slugar, new String[1]);
                    call.enqueue(new Callback<ServerResponseNodeEventSave>() {
                        @Override
                        public void onResponse(Call<ServerResponseNodeEventSave> call, Response<ServerResponseNodeEventSave> response) {
                            if (response.body().getType().equals("success")) {
                                MailInterface apiServ = ApiUtils.getAPIService();
                                int publicIDCode = response.body().getPublicIdCode();
                                // GET coge los datos del evento guardado ahora
                                Call<ServerResponseNode> callEventInfo = apiServ.listarEvento(publicIDCode);
                                callEventInfo.enqueue(new Callback<ServerResponseNode>() {
                                    @Override
                                    public void onResponse(Call<ServerResponseNode> call, Response<ServerResponseNode> response) {
                                        Evento eventoRes = response.body().getEvento();
                                        if (response.body().getType().equals("success")) {
                                            {
                                                //Toast.makeText(EventoCreando.this, "id" + eventoRes.getPublicIdCode() + " nombre" + eventoRes.getName() + " fecha " + eventoRes.getDate(), Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(EventoCreando.this, EventoDetail.class);
                                                intent.putExtra("evento", new Gson().toJson(eventoRes));
                                                startActivity(intent);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ServerResponseNode> call, Throwable t) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<ServerResponseNodeEventSave> call, Throwable t) {
                            Toast.makeText(EventoCreando.this, "Error al guardar el evento", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(EventoCreando.this, "Complete todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });


        //muestra para seleccionar mes, día y año
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker();
            }
        });

        //muestra la hora y minuto, si es am o pm
        bhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                TPD = new TimePickerDialog(EventoCreando.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        horaEvento.setText(hourOfDay + ":" + minute);
                        dateEvent.set(Calendar.HOUR, hourOfDay);
                        dateEvent.set(Calendar.MINUTE, minute);
                    }
                }, hora, minutos, false);
                TPD.show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                // get String data from Intent
                String addressIntent = data.getStringExtra("address");


                mLugar.setText(addressIntent);
            }
        }
    }

    //valida que se haya introducido los datos
    private boolean isValidData() {
        if (nombreEvento.getText().toString().length() > 0 &&
                mensaEvento.getText().toString().length() > 0 &&
                nombrePerson.getText().toString().length() > 0 &&
                horaEvento.getText().toString().length() > 0 &&
                mfecha.getText().toString().length() > 0
        ) {
            return true;
        } else {
            return false;
        }
    }
    //Declaración de referencias
    private void References() {
        mfecha = (EditText) findViewById(R.id.fechaEvent);
        fecha = (Button) findViewById(R.id.botonFecha);
        mLugar = (EditText) findViewById(R.id.mostarLugar);
        mapa = (Button) findViewById(R.id.irMapa);
        guardar = (Button) findViewById(R.id.btnCrearEvento);
        nombreEvento = (EditText) findViewById(R.id.eventoName);
        mensaEvento = (EditText) findViewById(R.id.eventoMessage);
        nombrePerson = (EditText) findViewById(R.id.personName);
        horaEvento = (EditText) findViewById(R.id.horaEvent);
        bhora = (Button) findViewById(R.id.botonHora);
        btnPrivacidad = findViewById(R.id.btnPrivacidad);
        mLugar.setText(slugar);
        nombreEvento.setText(snombreevento);
        mensaEvento.setText(smensajevento);
        nombrePerson.setText(snombreperson);
    }

    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        Calendar ahora = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                ahora.set(year, monthOfYear, dayOfMonth);
                mfecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                dateEvent.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateEvent.set(Calendar.YEAR, year);
                dateEvent.set(Calendar.MONTH, monthOfYear);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(EventoCreando.this, dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}
