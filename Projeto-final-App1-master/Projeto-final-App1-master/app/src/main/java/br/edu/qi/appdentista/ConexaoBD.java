package br.edu.qi.appdentista;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexaoBD extends SQLiteOpenHelper {
    private static final String name = "bd_empresa";
    private static final int version =1;

    public ConexaoBD (@Nullable Context context) {super(context, name, null,version);}

    @Override
    public void onCreate(SQLiteDatabase bd_empresa) {
        bd_empresa.execSQL("CREATE TABLE tb_paciente(id Integer not null primary key autoincrement," +
                "nome varchar(100) not null, idade int(4), dia int(2), mes int(2),ano int(5), procedimento varchar (100), doutor varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd_empresa, int i, int i1) {

    }
}
