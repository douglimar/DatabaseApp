package br.com.ddmsoftware.databaseapp;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Consulta extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        DatabaseController CRUD = new DatabaseController(this);

        final Cursor cursor = CRUD.loadData();

        String[] nomeCampos = new String[] {CriaBanco.ID, CriaBanco.TITULO};
        int[] idViews = new int[]{R.id.idLivro, R.id.nomeLivro};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),R.layout.activity_consulta,cursor, nomeCampos, idViews,0);
        lista = (ListView)findViewById(R.id.listView);

        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);

                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));

                Intent intent = new Intent(Consulta.this,Alter.class);

                intent.putExtra("codigo", codigo);
                startActivity(intent);

                finish();
            }
        });
    }
}
