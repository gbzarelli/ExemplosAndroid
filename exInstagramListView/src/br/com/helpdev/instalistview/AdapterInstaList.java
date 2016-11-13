package br.com.helpdev.instalistview;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterInstaList extends BaseAdapter {
	private ArrayList<ObItemInstaList> itens;
	private LayoutInflater layoutInflater;

	public AdapterInstaList(Context context, ArrayList<ObItemInstaList> itens) {
		this.itens = itens;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return itens.size();
	}

	@Override
	public ObItemInstaList getItem(int arg0) {
		return itens.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ItemHelper itemHelper;
		if (arg1 == null) {
			itemHelper = new ItemHelper();
			arg1 = layoutInflater.inflate(R.layout.item_listview, null);
			TextView title = (TextView) arg1.findViewById(R.id.title);
			TextView content = (TextView) arg1.findViewById(R.id.content);
			itemHelper.title = title;
			itemHelper.content = content;
			arg1.setTag(itemHelper);
		} else {
			itemHelper = (ItemHelper) arg1.getTag();
		}
		ObItemInstaList ob = getItem(arg0);

		itemHelper.title.setText(ob.getTitle());
		itemHelper.content.setText(ob.getContent());

		return arg1;
	}

	private class ItemHelper {
		TextView title, content;
	}

}
