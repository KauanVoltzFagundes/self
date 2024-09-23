package br.edu.qi.appdentista;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {
    private ListView lstPacientes;
    private PacienteDAO objPacienteDAO;
    private SearchView icConsultar;
    private List<Paciente> todosPacientes;
    private List<Paciente> pacientesFiltrados = new ArrayList<>();
    private Button btnVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lstPacientes = findViewById(R.id.lstPacientes);
        btnVoltar = findViewById(R.id.btnVoltar);
        objPacienteDAO = new PacienteDAO(this);

        todosPacientes = objPacienteDAO.consultarPacienteBD();
        pacientesFiltrados.addAll(todosPacientes);

        ArrayAdapter  adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pacientesFiltrados);
        lstPacientes.setAdapter(adaptador);

        registerForContextMenu(lstPacientes);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaActivity.this,PrincipalActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_principal,menu);
        icConsultar = (SearchView) menu.findItem(R.id.icConsultar).getActionView();

        icConsultar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procurarPacientePorNome(s);
                return false;
            }
        });

        return true;
    }


    public void procurarPacientePorNome(String nome){
        pacientesFiltrados.clear();
        for (int i = 0; i < todosPacientes.size(); i++){
            if(todosPacientes.get(i).getNome().toLowerCase().contains(nome.toLowerCase())){
                pacientesFiltrados.add(todosPacientes.get(i));
            }
        }
        lstPacientes.invalidateViews();
    }
    public void abrirTelaCadastro(MenuItem item){
        Intent intent = new Intent(ListaActivity.this, CadastrarActivity.class);
        startActivity(intent);
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_item,menu);
    }
    public void excluirPaciente(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Paciente objPacienteDeletado = pacientesFiltrados.get(menuInfo.position);


        AlertDialog confirmacao = new AlertDialog
                .Builder(this)
                .setIcon(R.drawable.ic_atencao)
                .setTitle("Atenção!!")
                .setMessage("Deseja desmarcar a consulta?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pacientesFiltrados.remove(objPacienteDeletado);
                        todosPacientes.remove(objPacienteDeletado);
                        objPacienteDAO.excluirPacienteBD(objPacienteDeletado);
                        lstPacientes.invalidateViews();

                    }
                }).create();
                confirmacao.show();
    }




    public void alterarPaciente(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
       Paciente objpacienteAlterado = pacientesFiltrados.get(menuInfo.position);

        Intent i = new Intent(ListaActivity.this, CadastrarActivity.class);
        i.putExtra("paciente", objpacienteAlterado);
        startActivity(i);
    }
}