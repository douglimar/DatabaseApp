package br.com.ddmsoftware.databaseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dmoraes on 05/09/2016.
 */
public class CriaBanco extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "banco.db";
    public static final String TABELA= "livros";
    public static final String ID = "_id";
    public static final String TITULO = "titulo";
    public static final String EDITORA = "editora";
    public static final String AUTOR = "autor";
    public static final int VERSION =1;

    public CriaBanco(Context context) {
        //super(context, name, factory, version);
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String  sql = "CREATE TABLE " + TABELA  +"("
                + ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + TITULO + " TEXT, "
                + AUTOR + " TEXT, "
                + EDITORA  + ")";

        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(sqLiteDatabase);
    }

}
