package br.com.helpdev.searchview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;

public class MainActivity extends ActionBarActivity implements
		OnQueryTextListener, OnFocusChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.search_view, menu);

		SearchView mSearchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();
		mSearchView.setQueryHint("teste");

		// exemplos de utilização:
		mSearchView.setOnQueryTextListener(this);

		return true;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		System.out.println(newText);
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		System.out.println(query);
		return false;
	}

	@Override
	public void onFocusChange(View arg0, boolean arg1) {
		System.out.println(arg1);
	}

}
