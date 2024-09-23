package br.edu.qi.projacadastroprodutos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexaoBD extends SQLiteOpenHelper {
    private static final String name = "bd_loja";
    private static final int version = 1;

    public ConexaoBD(@Nullable Context context) {
        super(context, name,null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase bd_loja) {
        bd_loja.execSQL("create table tb_produto(id Integer not null primary key autoincrement," +
               "nome varchar(100),categoria varchar(100),valor float(10,2) )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
