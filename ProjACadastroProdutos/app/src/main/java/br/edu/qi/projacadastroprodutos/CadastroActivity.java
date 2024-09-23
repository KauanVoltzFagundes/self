package br.edu.qi.projacadastroprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CadastroActivity extends AppCompatActivity {
    private TextView edtNome,edtCategoria,edtValor;
    private Button btnCadastrar,btnListar;
    private ProdutoDao objProdutoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        edtNome = findViewById(R.id.edtNome);
        edtCategoria = findViewById(R.id.edtcategoria);
        edtValor = findViewById(R.id.edtValor);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnListar = findViewById(R.id.btnListar);
        objProdutoDao = new ProdutoDao(this);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produto objProduto = new Produto();
                objProduto.setNome(edtNome.getText().toString());
                objProduto.setCategoria(edtCategoria.getText().toString());
                objProduto.setValor(Float.parseFloat(edtValor.getText().toString()));
                objProdutoDao.cadastrarProduto(objProduto);
            }
        });
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroActivity.this ,ListaActivity.class );
                startActivity(intent);
            }
        });
    }
}