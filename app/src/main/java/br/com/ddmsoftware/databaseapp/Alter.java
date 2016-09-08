package br.com.ddmsoftware.databaseapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Alter extends AppCompatActivity {

    EditText livro;
    EditText autor;
    EditText editora;
    Button btnAlterar;
    Button btnDeletar;
    Cursor cursor;
    DatabaseController CRUD;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);

        codigo = this.getIntent().getStringExtra("codigo");

        CRUD = new DatabaseController(getBaseContext());

        livro = (EditText)findViewById(R.id.editText4);
        autor = (EditText)findViewById(R.id.editText5);
        editora = (EditText)findViewById(R.id.editText6);

        btnAlterar = (Button) findViewById(R.id.button2);
        btnDeletar = (Button) findViewById(R.id.button3);
        cursor = CRUD.loadDataById(Integer.parseInt(codigo));
        livro.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TITULO)));
        autor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.AUTOR)));
        editora.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EDITORA)));

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CRUD.alterData(Integer.parseInt(codigo),livro.getText().toString(),autor.getText().toString(),editora.getText().toString());

                Intent intent = new Intent(Alter.this,Consulta.class);
                startActivity(intent);
                finish();
            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CRUD.deleteData(Integer.parseInt(codigo));

                Intent intent = new Intent(Alter.this, Consulta.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
