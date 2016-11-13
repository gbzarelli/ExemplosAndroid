/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarelli.biff.telas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import zarelli.biff.R;
import zarelli.biff.atividade.sherlock.fragment.Fragmento;

/**
 *
 * @author guilherme
 */
public class Tela_um extends Fragmento {
    
    public Tela_um() {
        super("tela_um");
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tela_1, null);
    }
    
    @Override
    public void atividadeCriada(Bundle savedInstanceState) {
    }
}
