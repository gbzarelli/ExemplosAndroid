package br.gui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 *
 * @author guilherme
 */
public class ExemploBindService extends Activity implements ServiceConnection, OnClickListener {

    private static final String CATEGORIA = "livro.servico";
    private Contador contador;
    private ServiceConnection conexao = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_bind);
        findViewById(R.id.bt_count_servico).setOnClickListener(this);
        findViewById(R.id.bt_inicia_servico).setOnClickListener(this);
        findViewById(R.id.bt_para_servico).setOnClickListener(this);
    }

    public void onServiceConnected(ComponentName arg0, IBinder arg1) {
        ServicoComConexao.LocalBinder binder = (ServicoComConexao.LocalBinder) arg1;
        contador = binder.getContador();
    }

    public void onServiceDisconnected(ComponentName arg0) {
        contador = null;
    }

    public void onClick(View arg0) {
        if (arg0 == findViewById(R.id.bt_inicia_servico)) {
            Log.i(CATEGORIA, "Inicia Serviço - bindService");
            startService(new Intent(this, ServicoComConexao.class));
            bindService(new Intent(this, ServicoComConexao.class), conexao, Context.BIND_AUTO_CREATE);
        } else if (arg0 == findViewById(R.id.bt_para_servico)) {
            Log.i(CATEGORIA, "Parar Serviço - unbindService");
            stopService(new Intent(this, ServicoComConexao.class));
            unbindService(conexao);
        } else if (arg0 == findViewById(R.id.bt_count_servico)) {
            int count = contador.count();
            Log.i(CATEGORIA, "Contador: " + count);
            Toast.makeText(this, "Contador: " + count, Toast.LENGTH_LONG).show();
        }
    }
}
