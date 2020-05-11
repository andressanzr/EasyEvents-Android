package es.easyevents.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

import es.easyevents.R;

public class FinaldelEvento extends AppCompatActivity {
    private TextView nombreEvento, mnsEvento, nombrePersona,fechaEvento,horaEvento,lugarEvento,idEvento;
    private Button generar;
    private Button guardar;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaldel_evento);
        References();

        Bundle bundle= getIntent().getExtras();
        String dat= bundle.getString("Info").toString();
        nombreEvento.setText("Titulo del evento:" + dat);

        Bundle bundle1= getIntent().getExtras();
        String dat1= bundle1.getString("Info1").toString();
        mnsEvento.setText("Mensaje:"+dat1);

        Bundle bundle2= getIntent().getExtras();
        String dat2= bundle2.getString("Info2").toString();
        nombrePersona.setText("Nombre:"+dat2);

        Bundle bundle3= getIntent().getExtras();
        String dat3= bundle3.getString("Info3").toString();
        fechaEvento.setText("Fecha:"+dat3);

        Bundle bundle4= getIntent().getExtras();
        String dat4= bundle4.getString("Info4").toString();
        horaEvento.setText("Hora:"+dat4);

        Bundle bundle5= getIntent().getExtras();
        String dat5= bundle5.getString("Info5").toString();
        lugarEvento.setText("Direccion:"+dat5);

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

        //guarda los datos introducidos
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irABoard();
            }
        });
    }

    //desliza al activity principal con menú desplegable
    private void irABoard() {
        Intent intent = new Intent(FinaldelEvento.this, PrincipalActivity.class);

        startActivity(intent);
        finish();
    }
    private void References() {
        generar=(Button)findViewById(R.id.genera);
        idEvento=(TextView) findViewById(R.id.codigo);
        nombreEvento=(TextView)findViewById(R.id.nombreEvent);
        guardar=(Button) findViewById(R.id.guardarDatos);
        mnsEvento=(TextView)findViewById(R.id.mensajeEvent);
        nombrePersona=(TextView)findViewById(R.id.nombrePersonas);
        fechaEvento=(TextView)findViewById(R.id.fechaEvent);
        horaEvento=(TextView)findViewById(R.id.horaEvento);
        lugarEvento=(TextView)findViewById(R.id.lugarEvent);
    }
}
