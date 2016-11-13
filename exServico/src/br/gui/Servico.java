package br.gui;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Servico extends Service implements Runnable {

    private static final int MAX = 10;
    private static final String CATEGORIA = "livro.servico";
    protected int count;
    private boolean ativo;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(CATEGORIA, "ExemploServico.OnCreate");
        ativo = true;
        new Thread(this).start();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(CATEGORIA, "ExemploServico.OnStart");
    }

    @Override
    public void onDestroy() {
        Log.i(CATEGORIA, "ExemploServico.OnDestroy");
        ativo = false;
        super.onDestroy();
    }

    public void run() {
        while (ativo && count < MAX) {
            fazAlgumaCoisa();
            Log.i(CATEGORIA, "ExemploServico executando... " + count);
            count++;
        }
        stopSelf();
    }

    private void fazAlgumaCoisa() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
}
