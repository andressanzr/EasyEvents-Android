package es.easyevents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private Button login;
    private Button registro;
    private EditText emailInicial;
    private EditText passwordInicial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        References();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loggy = new Intent(Login.this, CrearEvento.class);
                if(isValidData()&&validar()){
                    startActivity(loggy);
                    Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Login.this, "ingrese datos", Toast.LENGTH_LONG).show();

                }



            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loggy = new Intent(Login.this, Registrar.class);
                startActivity(loggy);

            }
        });
    }
    private void References() {
        login = (Button) findViewById(R.id.botonLogin);
        registro = (Button) findViewById(R.id.botonRegistro);
        emailInicial = (EditText) findViewById(R.id.MailInicial);
        passwordInicial = (EditText) findViewById(R.id.PasswordInicial);

    }
    private boolean isValidData() {
        if (emailInicial.getText().toString().length() > 0 &&
                passwordInicial.getText().toString().length() > 0
                ){
            return true;
        } else{
            return false;
        }
    }
    private  boolean validar(){
        String correo=emailInicial.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            Toast.makeText(this, "Ingresa un email válido", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
