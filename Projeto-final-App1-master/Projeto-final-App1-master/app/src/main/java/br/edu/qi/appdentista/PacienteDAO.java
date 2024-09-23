package br.edu.qi.appdentista;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    private SQLiteDatabase bd_empresa;
    private ConexaoBD conexaoBD;

    public PacienteDAO (Context context){
        this.conexaoBD = new ConexaoBD(context);
        this.bd_empresa = conexaoBD.getWritableDatabase();
    }

   public ContentValues gerarValores(Paciente objPaciente){
        ContentValues values = new ContentValues();

        values.put("nome" , objPaciente.getNome());
        values.put("idade", objPaciente.getIdade());
        values.put("dia", objPaciente.getDia());
        values.put("mes",objPaciente.getMes());
        values.put("ano",objPaciente.getAno());
        values.put("procedimento", objPaciente.getProcedimento());
        values.put("doutor", objPaciente.getDoutor());

        return values;
    }

    public void cadastrarPacienteBD(Paciente objPaciente){

        this.bd_empresa.insert("tb_paciente", null, this.gerarValores(objPaciente));
    }
    public void alterarPacienteBD(Paciente objPaciente){
        this.bd_empresa.update("tb_paciente",
                this.gerarValores(objPaciente), "id = ?",
                new String[]{String.valueOf(objPaciente.getId())});
    }


    public List<Paciente> consultarPacienteBD(){
        List <Paciente> listaDePacientes = new ArrayList<>();

        Cursor registros = this.bd_empresa.query("tb_paciente", new String[]{"id", "nome", "idade",
                "dia","mes","ano", "procedimento", "doutor"},
                null , null,null,null,null);
        while (registros.moveToNext()){
            Paciente objPaciente = new Paciente();

            objPaciente.setId(registros.getInt(0));
            objPaciente.setNome(registros.getString(1));
            objPaciente.setIdade(registros.getInt(2));
            objPaciente.setDia((byte) registros.getInt(3));
            objPaciente.setMes((byte) registros.getInt(4));
            objPaciente.setAno(registros.getInt(5));
            objPaciente.setProcedimento(registros.getString(6));
            objPaciente.setDoutor(registros.getString(7));

            listaDePacientes.add(objPaciente);
        }
        return listaDePacientes;
    }


    public  void excluirPacienteBD(Paciente objPaciente){
       this.bd_empresa.delete("tb_paciente","id = ?", new String[]{String.valueOf(objPaciente.getId())});
    }
}
