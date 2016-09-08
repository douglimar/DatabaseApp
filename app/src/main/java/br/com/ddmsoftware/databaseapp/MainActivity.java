package br.com.ddmsoftware.databaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseController CRUD = new DatabaseController(getBaseContext());
                EditText titulo = (EditText)findViewById(R.id.editText);
                EditText autor = (EditText)findViewById(R.id.editText2);
                EditText editora = (EditText)findViewById(R.id.editText3);

                String strTitutlo = titulo.getText().toString();
                String strAutor = autor.getText().toString();
                String strEditora = editora.getText().toString();

                String resultado;

                resultado = CRUD.insertData(strTitutlo, strAutor, strEditora);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, Consulta.class);

                startActivity(intent);


            }
        });
    }
}
