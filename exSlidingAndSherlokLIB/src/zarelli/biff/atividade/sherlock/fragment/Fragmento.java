package zarelli.biff.atividade.sherlock.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;

/**
 *
 * @author Guilherme Biff Zarelli
 */
public abstract class Fragmento extends SherlockFragment {

    private String titulo;

    public Fragmento(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        atividadeCriada(savedInstanceState);
    }

    public abstract void atividadeCriada(Bundle savedInstanceState);

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;

    }

    public Activity getAtividade() {
        return getActivity();
    }
}
