package biff.zarelli;

import biff.zarelli.fragments.AtividadeFragmento;
import biff.zarelli.fragments.adapter.Fragmento;
import biff.zarelli.telas.FragCaixa;
import biff.zarelli.telas.FragVisaoGeral;
import java.util.ArrayList;

public class MainActivity extends AtividadeFragmento {

    private ArrayList<Fragmento> listaFragmentos;

    public MainActivity() {
        super(R.layout.main, R.id.pager);
        listaFragmentos = new ArrayList<Fragmento>();
        listaFragmentos.add(new FragCaixa("Caixa"));
        listaFragmentos.add(new FragVisaoGeral("Visão Geral"));
        setListaFragmentos(listaFragmentos);
    }


}
