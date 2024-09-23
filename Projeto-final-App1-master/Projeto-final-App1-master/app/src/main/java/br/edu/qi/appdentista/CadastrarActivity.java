package br.edu.qi.appdentista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarActivity extends AppCompatActivity {
    private EditText edtNomePaciente;
    private EditText edtDia;
    private EditText edtMes;
    private  EditText edtAno;
    private EditText edtIdade;
    private EditText edtNomeDoutor;
    private EditText edtProcedimento;
    private PacienteDAO objPacienteDAO;
    private Paciente objPaciente =null;
    private Button btnAgendar,btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        getSupportActionBar().hide();

        edtNomePaciente = findViewById(R.id.edtNomePaciente);
        edtDia = findViewById(R.id.edtDia);
        edtMes = findViewById(R.id.edtMes);
        edtAno = findViewById(R.id.edtAno);
        edtIdade = findViewById(R.id.edtIdade);
        edtNomeDoutor = findViewById(R.id.edtNomeDoutor);
        edtProcedimento = findViewById(R.id.edtProcedimento);
        btnAgendar = findViewById(R.id.btnAgendar);
        btnVoltar = findViewById(R.id.btnVoltar);
        edtNomePaciente.requestFocus();
        objPacienteDAO = new PacienteDAO(this);

        Intent i = getIntent();
        if(i.hasExtra("paciente")){
            objPaciente = (Paciente) i.getSerializableExtra("paciente");

            edtNomePaciente.setText(objPaciente.getNome());
            edtDia.setText(String.valueOf(objPaciente.getDia()));
            edtMes.setText(String.valueOf(objPaciente.getMes()));
            edtAno.setText(String.valueOf(objPaciente.getAno()));
            edtIdade.setText(String.valueOf(objPaciente.getIdade()));
            edtNomeDoutor.setText(objPaciente.getDoutor());
            edtProcedimento.setText(objPaciente.getProcedimento());
            btnAgendar.setText("Alterar");

        }
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CadastrarActivity.this,PrincipalActivity.class);
                startActivity(i);
            }
        });

        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos()){
                    Toast.makeText(CadastrarActivity.this,"Preencha todos os campos",Toast.LENGTH_LONG).show();
                }
                else {
                    if (objPaciente == null) {

                        Paciente objPaciente = new Paciente();

                        objPaciente.setNome(edtNomePaciente.getText().toString());
                        objPaciente.setDia(Byte.parseByte(edtDia.getText().toString()));
                        objPaciente.setMes(Byte.parseByte(edtMes.getText().toString()));
                        objPaciente.setAno(Integer.parseInt(edtAno.getText().toString()));
                        objPaciente.setIdade(Integer.parseInt(edtIdade.getText().toString()));
                        objPaciente.setDoutor(edtNomeDoutor.getText().toString());
                        objPaciente.setProcedimento(edtProcedimento.getText().toString());

                        if (objPaciente.validarData() == false){
                            Toast.makeText(CadastrarActivity.this,"Data inválida",Toast.LENGTH_LONG).show();
                        }
                        else if(objPaciente.validarIdade() == false){
                            Toast.makeText(CadastrarActivity.this,"Idade inválida",Toast.LENGTH_LONG).show();
                        }
                        else {
                            objPacienteDAO.cadastrarPacienteBD(objPaciente);
                            Toast.makeText(CadastrarActivity.this, "Consulta agendada", Toast.LENGTH_LONG).show();
                            apagarCampos();
                        }
                    } else {


                        objPaciente.setNome(edtNomePaciente.getText().toString());
                        objPaciente.setDia(Byte.parseByte(edtDia.getText().toString()));
                        objPaciente.setMes(Byte.parseByte(edtMes.getText().toString()));
                        objPaciente.setAno(Integer.parseInt(edtAno.getText().toString()));
                        objPaciente.setIdade(Integer.parseInt(edtIdade.getText().toString()));
                        objPaciente.setDoutor(edtNomeDoutor.getText().toString());
                        objPaciente.setProcedimento(edtProcedimento.getText().toString());

                        if (objPaciente.validarData() == false){
                            Toast.makeText(CadastrarActivity.this,"Data inválida",Toast.LENGTH_LONG).show();
                        }
                        else if(objPaciente.validarIdade() == false){
                            Toast.makeText(CadastrarActivity.this,"Idade inválida",Toast.LENGTH_LONG).show();
                        }
                        else {
                            objPacienteDAO.alterarPacienteBD(objPaciente);
                            Toast.makeText(CadastrarActivity.this, "Consulta alterada", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(CadastrarActivity.this, ListaActivity.class);
                            startActivity(i);
                        }
                    }
                }

            }
            public void apagarCampos(){
                edtNomePaciente.setText("");
                edtDia.setText("");
                edtMes.setText("");
                edtAno.setText("");
                edtIdade.setText("");
                edtNomeDoutor.setText("");
                edtProcedimento.setText("");
                edtNomePaciente.requestFocus();
            }
            public boolean validarCampos(){
                if (edtNomePaciente.getText().toString().isEmpty() || edtDia.getText().toString().isEmpty() ||edtMes.getText().toString().isEmpty() || edtAno.getText().toString().isEmpty() ||edtIdade.getText().toString().isEmpty() || edtNomeDoutor.getText().toString().isEmpty() || edtProcedimento.getText().toString().isEmpty()){
                  return true;
                }
                return false;
            }
        });



    }
}