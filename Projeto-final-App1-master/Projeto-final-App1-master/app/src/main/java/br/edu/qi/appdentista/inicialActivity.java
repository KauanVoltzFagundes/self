package br.edu.qi.appdentista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class inicialActivity extends AppCompatActivity {

    public Button btnInicial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        getSupportActionBar().hide();
        btnInicial = findViewById(R.id.btnInicial);
        btnInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inicialActivity.this, PrincipalActivity.class);
                startActivity(intent);

            }
        });
    }
}