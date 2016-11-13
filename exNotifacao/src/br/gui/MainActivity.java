package br.gui;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //define a intent responsavel por passar os parametros necessarios para 
        //criar uma notificacao.
        Intent it = new Intent();
        it.putExtra(Notificacao.INTENT_STRING_MSG, "Meu teste de notificacao.");
        it.putExtra(Notificacao.INTENT_STRING_MSG_STATUS, "Olá");
        it.putExtra(Notificacao.INTENT_STRING_TITULO, "Titulo da notificação");
        it.putExtra(Notificacao.INTENT_LONG_QUANDO, System.currentTimeMillis());
        it.putExtra(Notificacao.INTENT_INT_ICONE, android.R.drawable.ic_menu_week);
        //Intente de ação/ou seja/ao clicar na notificação, o sistema irá abrir
        //uma pagina com o seguinte endereco
        String url = "http://www.example.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        //Cria a notificação.
        Notification notf = Notificacao.criarNotificacao(this, it, i, Notificacao.TIPO_ACTIVITY);
        //Manda notificar.
        Notificacao.notificar(this, notf, 550);

    }
}
