package es.easyevents;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EventoCreando extends AppCompatActivity {
    Button fecha;
    EditText mfecha;
    EditText mLugar;
    Button mapa;

    Calendar c;
    DatePickerDialog dpd;

    private Address address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            address = (Address) bundle.get("address");
        }

        setContentView(R.layout.activity_evento_creando);
        mfecha = (EditText) findViewById(R.id.mostarfecha);
        fecha = (Button) findViewById(R.id.botonFecha);
        mLugar = (EditText) findViewById(R.id.mostarLugar);
        mapa = (Button) findViewById(R.id.irMapa);

        c = Calendar.getInstance();
        final int dia = c.get(Calendar.DAY_OF_MONTH);
        final int mes = c.get(Calendar.MONTH);
        final int anno = c.get(Calendar.YEAR);

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(EventoCreando.this, ActivityMaps.class);
                startActivity(map);
                Bundle parametros = new Bundle();
                map.putExtras(parametros);

            }
        });


        // TODO arreglar fecha
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpd = new DatePickerDialog(EventoCreando.this, new DatePickerDialog.OnDateSetListener() {


                    @Override
                    public void onDateSet(DatePicker datePicker, int mAnio, int mMes, int mDia) {
                        mfecha.setText(mDia + "/" + (mMes + 1) + "/" + mAnio);

                    }
                }, anno, mes, dia);
                dpd.show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(address!=null)mLugar.setText(address.getAddressLine(0));
    }
}
