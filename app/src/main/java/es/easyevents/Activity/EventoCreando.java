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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import es.easyevents.R;

public class EventoCreando extends AppCompatActivity {
    static String snombreevento;
    static String smensaevento;
    static String snombreperson;
    static String shoraevento;
    static String sfecha;
    static String slugar;
    Button fecha, bhora;
    EditText mfecha;
    EditText mLugar;
    Button mapa;
    private Button generar;
    private TextView idEvento;
    private EditText nombreEvento;
    private EditText mensaEvento;
    private EditText nombrePerson;
    private EditText horaEvento;
    private boolean isCreation;
    private Calendar date;
    TimePickerDialog TPD;
    Calendar c;
    DatePickerDialog dpd;

    private Address address;
    private Button guardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            address = (Address) bundle.get("address");
        }
        setActivityTitle();

        setContentView(R.layout.activity_evento_creando);

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
                startActivity(map);
                Bundle parametros = new Bundle();
                map.putExtras(parametros);
                sfecha=mfecha.getText().toString();
                slugar=mLugar.getText().toString();
                snombreevento=nombreEvento.getText().toString();
                smensaevento=mensaEvento.getText().toString();
                snombreperson=nombrePerson.getText().toString();
                shoraevento=horaEvento.getText().toString();

            }
        });
        //guarda los datos introducidos para mostrar en el activity FinaldelEvento
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ver = new Intent(EventoCreando.this, PrincipalActivity.class);
                ver.putExtra("Info", nombreEvento.getText().toString());
                ver.putExtra("Info1", mensaEvento.getText().toString());
                ver.putExtra("Info2", nombrePerson.getText().toString());
                ver.putExtra("Info3", mfecha.getText().toString());
                ver.putExtra("Info4", horaEvento.getText().toString());
                ver.putExtra("Info5", mLugar.getText().toString());
                if (isValidData()) {
           //         startActivity(ver);
                    finish();
                    Toast.makeText(EventoCreando.this, "Se guardaron los datos con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EventoCreando.this, "ingrese datos", Toast.LENGTH_LONG).show();

                }

            }
        });

        //muestra para seleccionar mes, día y año
        // TODO arreglar fecha
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker();

            }
        });
        final char[] chars1="ABCDEFGHI0123456789JKLMNÑOPQRSTUVWXYZ".toCharArray();
        StringBuilder string =new StringBuilder();
        Random random = new Random();

        for (int i = 0; i<10; i++) {
            char c = chars1[random.nextInt(chars1.length)+1];
            string.append(c);
        }
        final String ID = chars1.toString();
        //genera un id para el evento que cree el usuario
        generar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random= new Random();
                int ID= random.nextInt();



                idEvento.setText(ID+"");

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
                    }
                }, hora, minutos, false);
                TPD.show();

            }
        });
    }

    //Cambia el título si es para crearo editar
    private void setActivityTitle() {
        String title = "Crear nuevo evento";
        if (isCreation) title = "Editar evento";
        setTitle(title);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (address != null) mLugar.setText(address.getAddressLine(0));
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

    //Declaración de las variables
    private void References() {
        idEvento=(TextView) findViewById(R.id.codigo);
        generar=(Button)findViewById(R.id.genera);
        mfecha = (EditText) findViewById(R.id.mostarfecha);
        fecha = (Button) findViewById(R.id.botonFecha);
        mLugar = (EditText) findViewById(R.id.mostarLugar);
        mapa = (Button) findViewById(R.id.irMapa);
        guardar = (Button) findViewById(R.id.eventCreado);
        nombreEvento = (EditText) findViewById(R.id.eventoName);
        mensaEvento = (EditText) findViewById(R.id.eventoMessage);
        nombrePerson = (EditText) findViewById(R.id.personName);
        horaEvento = (EditText) findViewById(R.id.horaEvent);
        bhora = (Button) findViewById(R.id.botonHora);
        mfecha.setText(sfecha);
        mLugar.setText(slugar);
        nombreEvento.setText(snombreevento);
        mensaEvento.setText(smensaevento);
        nombrePerson.setText(snombreperson);
        horaEvento.setText(shoraevento);
    }

    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                mfecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                //use this date as per your requirement
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(EventoCreando.this, dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}
