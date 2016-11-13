package br.gui;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 *
 * @author guilherme
 */
public class ServicoComConexao extends Servico implements Contador {

    private final IBinder conexao = new LocalBinder();

    public class LocalBinder extends Binder {

        public Contador getContador() {
            return ServicoComConexao.this;
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return conexao;
    }

    public int count() {
        return count;
    }
}
