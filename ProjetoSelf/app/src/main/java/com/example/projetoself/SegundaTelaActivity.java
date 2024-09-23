package com.example.projetoself;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SegundaTelaActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela);

        TextView textView = findViewById(R.id.textViewOlaMundo);
        textView.setText("dsfsdfds Mundo!");
    }
}
