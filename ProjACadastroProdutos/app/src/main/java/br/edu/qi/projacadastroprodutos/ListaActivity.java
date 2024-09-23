package br.edu.qi.projacadastroprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaActivity extends AppCompatActivity {
    private ListView lstProdutos;
    private ProdutoDao objProdutoDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        getSupportActionBar().hide();

        lstProdutos = findViewById(R.id.lstProdutos);
        objProdutoDao = new ProdutoDao(this);

        ArrayAdapter<Produto> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,objProdutoDao.consultarProdutos());
        lstProdutos.setAdapter(adapter);
    }
}