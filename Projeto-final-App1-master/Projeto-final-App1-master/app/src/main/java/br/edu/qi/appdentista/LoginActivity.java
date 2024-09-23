package br.edu.qi.appdentista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsuarioLogin;
  private EditText edtSenhaLogin;
  private Button btnLogin;
  private Login objLogin = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuarioLogin = findViewById(R.id.edtUsuarioLogin);
        edtSenhaLogin = findViewById(R.id.edtSenhaLogin);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtUsuarioLogin.getText().toString().equals("admin") && (edtSenhaLogin.getText().toString().equals("123"))){
                    Intent i = new Intent(LoginActivity.this, ListaActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Permissão negada", Toast.LENGTH_LONG).show();
                }
            }
        });
        }
    }

