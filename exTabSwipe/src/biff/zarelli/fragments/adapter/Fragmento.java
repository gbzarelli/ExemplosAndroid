/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biff.zarelli.fragments.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * @author guilherme
 */
public abstract class Fragmento extends Fragment {

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
}
