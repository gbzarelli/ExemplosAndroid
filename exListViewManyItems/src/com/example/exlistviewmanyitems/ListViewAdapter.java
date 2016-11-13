package com.example.exlistviewmanyitems;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	private static final int SIZE_ITENS_MAX = 300;
	private List<ListViewObjetos> itens_exibicao;
	private List<ListViewObjetos> itens;
	private LayoutInflater layoutInflater;
	
	public ListViewAdapter(Context context, List<ListViewObjetos> itens) {
		this.itens = itens;
		this.itens_exibicao = itens;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return itens_exibicao.size()>SIZE_ITENS_MAX?SIZE_ITENS_MAX:itens_exibicao.size();
	}

	@Override
	public Object getItem(int arg0) {
		return itens_exibicao.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return itens_exibicao.get(arg0).getId();
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ItemHelper itemHelper = new ItemHelper();
		ListViewObjetos objeto = itens_exibicao.get(arg0);
		
		if(arg1==null){
			arg1 =layoutInflater.inflate(R.layout.listview_item,null);
			itemHelper.descricao = (TextView) arg1.findViewById(R.id.textView1);
			itemHelper.valor = (TextView) arg1.findViewById(R.id.textView2);
			arg1.setTag(itemHelper);
		}else{
			itemHelper = (ItemHelper) arg1.getTag();
		}
		
		itemHelper.descricao.setText("-> "+objeto.getId()+" - "+objeto.getDescricao());
		itemHelper.valor.setText(objeto.getValor());
		
		return arg1;
	}
	
	private class ItemHelper{
		TextView descricao,valor;
	}
	
    public Filter getFilter() {
        Filter filter = new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                itens_exibicao = (List<ListViewObjetos>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence filtro) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                List<ListViewObjetos> itens_filtrados = new ArrayList<ListViewObjetos>();

                if (filtro == null || filtro.length() < 2) {
                	for (int i = 0; i < SIZE_ITENS_MAX; i++) {
                            itens_filtrados.add(itens.get(i));
                    }
                    // set the Filtered result to return
                    results.count = SIZE_ITENS_MAX;
                    results.values = itens_filtrados;  
                  //  results.count = itens.size();
                    //results.values = itens;
                } else {
                    filtro = filtro.toString().toLowerCase();
                    for (int i = 0; i < itens.size(); i++) {
                    	ListViewObjetos data = itens.get(i);
                        if (data.getDescricao().toLowerCase().contains(filtro.toString())) {
                            itens_filtrados.add(data);
                        }
                    }
                    // set the Filtered result to return
                    results.count = itens_filtrados.size();
                    results.values = itens_filtrados;
                }
                return results;
            }
        };
        return filter;
    }

}
