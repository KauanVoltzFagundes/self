package br.edu.qi.appdentista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class InformacoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes);
        getSupportActionBar().hide();
    }
}