/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarelli.biff.adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import zarelli.biff.objetos.ObVideo;

/**
 *
 * @author guilherme
 */
public class AdapterLista extends BaseAdapter {

    private Context context;
    private ArrayList<ObVideo> itens;

    public AdapterLista(Context context, ArrayList<ObVideo> itens) {
        this.context = context;
        this.itens = itens;
    }

    public int getCount() {
        return itens.size();
    }

    public Object getItem(int arg0) {
        return itens.get(arg0);
    }

    public long getItemId(int arg0) {
        return arg0;
    }

    public View getView(int arg0, View arg1, ViewGroup arg2) {
        return arg1;
    }
}
