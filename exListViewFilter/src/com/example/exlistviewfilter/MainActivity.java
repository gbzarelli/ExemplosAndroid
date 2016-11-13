package com.example.exlistviewfilter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cria uma lista de 100 objetos para usar no exemplo.
        ArrayList<ListViewObjetos> itens = new ArrayList<ListViewObjetos>();
        for (int i = 0; i < 100; i++) {
            itens.add(new ListViewObjetos(i, "descricao+" + i, "R$" + i + ",00"));
        }

        // constroi o adapter passando os itens.
        adapter = new ListViewAdapter(this, itens);
        ListView lista = (ListView) findViewById(R.id.listView1);
        lista.setAdapter(adapter);

        //Aqui é aonde adicionamos nosso filtro no edittext.
        EditText editText = (EditText) findViewById(R.id.editText1);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                    int count) {
                //quando o texto é alterado chamamos o filtro.
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }
}
