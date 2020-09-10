package es.easyevents.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;

import es.easyevents.Data.ApiUtils;
import es.easyevents.Data.MailInterface;
import es.easyevents.Models.Evento;
import es.easyevents.Models.ServerResponseNode;
import es.easyevents.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button crear;
    private Button invitado;
    private EditText editTextCodigoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        crear = (Button) findViewById(R.id.botonCrear);
        invitado = (Button) findViewById(R.id.botonInvitado);
        editTextCodigoEvento = findViewById(R.id.editTextCodigoEvento);

        invitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextCodigoEvento.getText().toString().length() < 1) {
                    Toast.makeText(MainActivity.this, "Introduzca un ID de evento", Toast.LENGTH_LONG).show();
                } else {
                    MailInterface apiServ = ApiUtils.getAPIService();
                    Call<ServerResponseNode> call = apiServ.listarEvento(Integer.parseInt(editTextCodigoEvento.getText().toString()));

                    call.enqueue(new Callback<ServerResponseNode>() {
                        @Override
                        public void onResponse(Call<ServerResponseNode> call, Response<ServerResponseNode> response) {
                            Evento eventoRes = response.body().getEvento();

                            if (response.body().getType().equals("error")) {
                                Toast.makeText(MainActivity.this, "El ID de evento no existe, introduzca un ID válido", Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(MainActivity.this, EventoDetail.class);
                                intent.putExtra("evento", new Gson().toJson(eventoRes));
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<ServerResponseNode> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "El ID de evento no existe, introduzca un ID válido", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crear = new Intent(MainActivity.this, CrearEvento.class);
                startActivity(crear);
            }
        });
    }
}
