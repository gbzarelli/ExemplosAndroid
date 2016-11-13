package br.com.helpdev.instalistview;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnScrollListener {

	private ListView listView;
	private int mScrollState = SCROLL_STATE_IDLE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<ObItemInstaList> list = new ArrayList<ObItemInstaList>();
		list.add(new ObItemInstaList("Título 1", " conteúdo item 1 "));
		list.add(new ObItemInstaList("Título 2", " conteúdo item 2 "));
		list.add(new ObItemInstaList("Título 3", " conteúdo item 3 "));
		list.add(new ObItemInstaList("Título 4", " conteúdo item 4 "));
		list.add(new ObItemInstaList("Título 5", " conteúdo item 5 "));
		list.add(new ObItemInstaList("Título 6", " conteúdo item 6 "));
		list.add(new ObItemInstaList("Título 7", " conteúdo item 7 "));
		list.add(new ObItemInstaList("Título 8", " conteúdo item 8 "));
		list.add(new ObItemInstaList("Título 9", " conteúdo item 9 "));

		AdapterInstaList adapter = new AdapterInstaList(this, list);
		listView = (ListView) findViewById(R.id.lista);
		listView.setAdapter(adapter);

		listView.setOnScrollListener(this);
	}

	@Override
	public void onScroll(AbsListView list, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// se a lista não estiver se movimentando.
		if (mScrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			return;
		}
		// percorre todos os itens visíveis.
		for (int i = 0; i < visibleItemCount; i++) {
			View listItem = list.getChildAt(i);
			if (listItem == null) {
				break;
			}

			TextView title = (TextView) listItem.findViewById(R.id.title);

			int topMargin = 0;

			// Primeiro item da lista.
			if (i == 0) {
				int top = listItem.getTop();
				int height = listItem.getHeight();

				// Se for negativo a lista foi rolada para cima.
				if (top < 0) {
					// se o título está dentro parte visível do
					// recipiente, coloque a margem superior para ser a
					// (inverso) quantidade rolada do recipiente.
					if (title.getHeight() < (top + height)) {
						topMargin = -top;
					} else {
						// define a margem superior com a diferença
						// entre as alturas.
						topMargin = height - title.getHeight();
					}
				}
			}

			// Define a marginTop do titulo.
			((ViewGroup.MarginLayoutParams) title.getLayoutParams()).topMargin = topMargin;
			listItem.requestLayout();
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		mScrollState = scrollState;
	}
}
