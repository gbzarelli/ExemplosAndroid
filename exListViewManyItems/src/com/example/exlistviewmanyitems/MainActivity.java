package com.example.exlistviewmanyitems;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<ListViewObjetos> itens = new ArrayList<ListViewObjetos>();
		for (int i = 0; i < 100000; i++) {
			itens.add(new ListViewObjetos(i, "descricao+" + i, "R$" + i + ",00"));
		}

		adapter = new ListViewAdapter(this, itens);
		ListView lista = (ListView) findViewById(R.id.listView1);
		lista.setAdapter(adapter);

		EditText editText = (EditText) findViewById(R.id.editText1);
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				System.out.println("Start: "+start);
				System.out.println("before: "+start);
				System.out.println("count: "+count);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
