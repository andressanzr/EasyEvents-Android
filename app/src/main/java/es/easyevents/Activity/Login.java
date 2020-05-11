package es.easyevents.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

import es.easyevents.Data.ApiUtils;
import es.easyevents.Data.MailInterface;
import es.easyevents.Data.RetrofitClient;
import es.easyevents.Models.Usuario;
import es.easyevents.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {
    private Button login;
    private Button registro;
    private EditText emailInicial;
    private CompositeDisposable compositeDisposable= new CompositeDisposable();
    private EditText passwordInicial;
    private MailInterface mAPIService;
    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inicia el servidor
        mAPIService = ApiUtils.getAPIService();
       // mAPIService= retrofitClient.create(MailInterface.class);
        initSharedPreferences();

        References();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();

                map.put("email", emailInicial.getText().toString());
                map.put("password", passwordInicial.getText().toString());

                Call<Usuario> call = mAPIService.executeLogin(map);

                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                        if (response.code() == 200 && isValidData() && validar()) {
                            Intent loggy = new Intent(Login.this, CrearEvento.class);

                            Usuario result = response.body();
                            Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_LONG).show();
                            startActivity(loggy);
                            finish();
                        } else if (response.code() == 404) {
                            Toast.makeText(Login.this, "Correo o contraseña incorrecto",
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(Login.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

               if(isValidData()&&validar()){
                    loginUser(emailInicial.getText().toString(), passwordInicial.getText().toString());
                    Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Login.this, "ingrese datos", Toast.LENGTH_LONG).show();
                }
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI();
            }
        });
    }
    private void initSharedPreferences() {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }
    private void References() {
        login = (Button) findViewById(R.id.botonLogin);
        registro = (Button) findViewById(R.id.botonRegistro);
        emailInicial = (EditText) findViewById(R.id.MailInicial);
        passwordInicial = (EditText) findViewById(R.id.PasswordInicial);

    }
    //verifica que los datos no esten vacíos
    private boolean isValidData() {
        if (emailInicial.getText().toString().length() > 0 &&
                passwordInicial.getText().toString().length() > 0
                ){
            return true;
        } else{
            return false;
        }
    }
    //verifica que sea un email válido
    private  boolean validar(){
        String correo=emailInicial.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            Toast.makeText(this, "Ingresa un email válido", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void  loginUser(String email, String password){
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email no es compatible, vuelva a ingresar", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "La contraseña no es compatible, vuelva a ingresar", Toast.LENGTH_SHORT).show();
            return;
        }/*
        compositeDisposable.add(iMyService.loginUser(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>(){

                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(Login.this,""+response,Toast.LENGTH_SHORT).show();
                    }
                }));*/
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        // FirebaseUser user = mAuth.getCurrentUser();
        if(user != null) {
            //user is already connected  so we need to redirect him to home page
            updateUI();
        }
    }*/

    private void updateUI() {

        Intent loggy = new Intent(Login.this, Registrar.class);
        startActivity(loggy);
        finish();
        }
    }
