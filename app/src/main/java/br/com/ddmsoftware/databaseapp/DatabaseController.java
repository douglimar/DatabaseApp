package br.com.ddmsoftware.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by dmoraes on 05/09/2016.
 */
public class DatabaseController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public DatabaseController(Context context){

        banco = new CriaBanco(context);
    }

    public String insertData(String titulo, String autor, String editora) {

        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CriaBanco.TITULO, titulo );
        valores.put(CriaBanco.AUTOR, autor);
        valores.put(CriaBanco.EDITORA, editora);

        resultado = db.insert(CriaBanco.TABELA, null, valores);

        db.close();

        if(resultado==-1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro Inserido com sucesso.";

        }
    }

    public Cursor loadData() {

        Cursor cursor;
        String[] campos = {banco.ID, banco.TITULO};

        db = banco.getReadableDatabase();

        cursor = db.query(banco.TABELA, campos, null,null,null,null,null,null);

        if (cursor!=null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor loadDataById(int id){

        Cursor cursor;

        String[] campos = {banco.ID, banco.TITULO, banco.AUTOR, banco.EDITORA};
        String where = CriaBanco.ID + " = " + id;

        db = banco.getReadableDatabase();

        cursor = db.query(CriaBanco.TABELA, campos, where, null,null,null,null,null);

        if (cursor!=null){
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public void alterData(int id, String titulo, String autor, String editora){

        ContentValues values;

        String where;

        db = banco.getWritableDatabase();

        where = CriaBanco.ID + "=" + id;

        values = new ContentValues();
        values.put(CriaBanco.TITULO, titulo);
        values.put(CriaBanco.AUTOR, autor);
        values.put(CriaBanco.EDITORA,editora);

        db.update(CriaBanco.TABELA, values,where, null);
        db.close();

    }


    public void deleteData(int id){

        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.TABELA,where, null);
        db.close();

    }
}
