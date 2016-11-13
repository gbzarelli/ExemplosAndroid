/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biff.zarelli.telas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import biff.zarelli.fragments.adapter.Fragmento;
import biff.zarelli.R;

/**
 *
 * @author guilherme
 */
public class FragVisaoGeral extends Fragmento {

    public FragVisaoGeral(String titulo) {
        super(titulo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visao_geral, null);
    }

    @Override
    public void atividadeCriada(Bundle savedInstanceState) {
    }
}
