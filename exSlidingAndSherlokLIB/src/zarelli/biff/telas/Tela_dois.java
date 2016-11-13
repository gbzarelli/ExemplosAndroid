/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarelli.biff.telas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import zarelli.biff.R;
import zarelli.biff.atividade.sherlock.fragment.Fragmento;

/**
 *
 * @author guilherme
 */
public class Tela_dois extends Fragmento {

    public Tela_dois() {
        super("Tela_dois");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tela_2, null);
    }

    @Override
    public void atividadeCriada(Bundle savedInstanceState) {
        getActivity().findViewById(R.id_tela_1.botao).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Toast.makeText(getActivity(), "abaahoihaog", Toast.LENGTH_LONG).show();
            }
        });
    }
}
