package com.wordpress.zarelli;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    private static final String TAG = "com.wordpress.zarelli.MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        new Thread() {
            @Override
            public void run() {
                try {
                    //estou usando o ip 10.0.2.2 pois o emulador acessa sua 
                    //maquina atravez dele.
                    URL url = new URL("http://10.0.2.2:8080/exServlet/exServlet");
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.setRequestMethod("POST");
                    http.addRequestProperty("content-type", "application/binary");
                    http.setDoInput(true);
                    http.setDoOutput(true);
                    http.setConnectTimeout(20000);

                    http.connect();
                    //abre canal de saida
                    ObjectOutputStream oos = new ObjectOutputStream(http.getOutputStream());
                    //envia objeto
                    oos.writeObject(new ObTeste("teste envio"));

                    //abre canal de leitura
                    ObjectInputStream ois = new ObjectInputStream(http.getInputStream());
                    //recebe objeto
                    Serializable obTeste = (Serializable) ois.readObject();
                    //imprime log
                    Log.i(TAG, "RETORNO:> " + obTeste.toString());

                } catch (Exception e) {
                    Log.e(TAG, "MainActivity.onCreate.servlet", e);
                }
            }
        }.start();
    }
}
