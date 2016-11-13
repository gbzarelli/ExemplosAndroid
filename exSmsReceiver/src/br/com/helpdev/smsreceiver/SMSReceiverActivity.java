/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpdev.smsreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Guilherme Biff Zarelli
 */
public class SMSReceiverActivity extends Activity {

    private BroadcastReceiver broadcastReceiver;
    private ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView listView = (ListView) findViewById(R.id.lista);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //Pega os dados enviados pelo SMSReceiver.
                Bundle msg = intent.getBundleExtra("msg");
                String sender = msg.getString("sender");
                String body = msg.getString("body");
                long timestamp = msg.getLong("timestamp");

                //formatando data para exibição.
                Date date = new Date(timestamp);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm - dd/MM/yyyy");

                //gera uma string com as informações.
                String mensagem = sdf.format(date) + "\nDe: " + sender + "\nMensagem: " + body;

                //adiciona e atualiza o adapter.
                adapter.add(mensagem);
                adapter.notifyDataSetChanged();
            }
        };

        //Cria um filtro baseado na ação configurada em nosso SMSReceiver.
        IntentFilter intentFilter = new IntentFilter("br.com.helpdev.smsreceiver.SMSRECEIVER");
        //Registra o broadcast.
        this.registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(this.broadcastReceiver);
    }
}
