package es.easyevents.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import es.easyevents.Data.ApiUtils;
import es.easyevents.Data.MailInterface;
import es.easyevents.Data.RetrofitClient;
import es.easyevents.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Registrar extends AppCompatActivity {
    private Button IniciarSesion;
    private Button registro;
    private EditText nombre, telefono, email, PasswordInicial;
    private MailInterface mAPIService;
    private CompositeDisposable compositeDisposable= new CompositeDisposable();
    private  CheckBox check;
    private TextView priv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        //inicia el servidor
        mAPIService = ApiUtils.getAPIService();

       // Retrofit retrofitClient =  RetrofitClient.getInstance();
      //  mAPIService= retrofitClient.create(MailInterface.class);

        References();

        IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registrar.this, Login.class);
                startActivity(intent);
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();

                map.put("name", nombre.getText().toString());
                map.put("phone", telefono.getText().toString());
                map.put("email", email.getText().toString());
                map.put("password", PasswordInicial.getText().toString());

                Call<Void> call = mAPIService.executeSignup(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.code() == 200 && isValidData() &&validar()) {
                            Intent loggy = new Intent(Registrar.this, MainActivity.class);

                            Toast.makeText(Registrar.this,
                                    "Te registraste correctamente", Toast.LENGTH_LONG).show();
                            startActivity(loggy);

                        } else if (response.code() == 400) {
                            Toast.makeText(Registrar.this,
                                    "ERROR. verifica los datos ", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Registrar.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

                }

        });
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                }else{
                    registro.setVisibility(View.GONE);
                    Toast.makeText(Registrar.this, "Es necesario aceptar nuestra politica de privacidad, para que puedas continuar",Toast.LENGTH_SHORT).show();

                }
            }
        });

        priv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registrar.this, Privacidad.class));


            }
        });
    }

    private void References() {

        nombre = (EditText) findViewById(R.id.Nombre);
        telefono = (EditText) findViewById(R.id.telefono);
        email = (EditText) findViewById(R.id.MailInicial);
        IniciarSesion = (Button) findViewById(R.id.botonIniciarSesion);
        registro = (Button) findViewById(R.id.botonCrearCuenta);
        check= (CheckBox) findViewById(R.id.chek);
        priv =(TextView) findViewById(R.id.privacidad);
        PasswordInicial = (EditText) findViewById(R.id.PasswordInicial);

    }
    private boolean isValidData() {
        if (email.getText().toString().length() > 0 &&
                PasswordInicial.getText().toString().length() > 0&&
                nombre.getText().toString().length() > 0&&
                telefono.getText().toString().length() > 0
        ){
            return true;
        } else{
            return false;
        }
    }
    private  boolean validar(){
        String correo=email.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            Toast.makeText(this, "Ingresa un email válido", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}