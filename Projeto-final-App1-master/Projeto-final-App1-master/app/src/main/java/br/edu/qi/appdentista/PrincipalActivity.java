package br.edu.qi.appdentista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrincipalActivity extends AppCompatActivity {
    public View btnLista;
    public View btnAgendar;
    public View btnInfo;
    public View btnTelaInicial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        getSupportActionBar().hide();

        btnLista = findViewById(R.id.btnLista);
        btnAgendar = findViewById(R.id.btnAgendar);
        btnInfo = findViewById(R.id.btnInfo);
        btnTelaInicial = findViewById(R.id.btnTelaInicial);

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this, CadastrarActivity.class);
                startActivity(intent);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PrincipalActivity.this, InformacoesActivity.class);
                startActivity(i);
            }
        });

        btnTelaInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PrincipalActivity.this, inicialActivity.class);
                startActivity(i);
            }
        });
    }
}