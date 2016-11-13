/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biff.zarelli.telas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import biff.zarelli.fragments.adapter.Fragmento;
import biff.zarelli.R;

/**
 *
 * @author guilherme
 */
public class FragCaixa extends Fragmento {

    public FragCaixa(String titulo) {
        super(titulo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_caixa, null);
    }

    @Override
    public void atividadeCriada(Bundle savedInstanceState) {
        getView().findViewById(R.idCaixa.botao).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "teste", Toast.LENGTH_LONG).show();
            }
        });
    }
}
