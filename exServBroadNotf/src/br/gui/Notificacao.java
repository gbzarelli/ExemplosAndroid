package br.gui;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 *
 * @author guilherme
 */
public class Notificacao {

    public static final int TIPO_ACTIVITY = 1;
    public static final int TIPO_BROADCAST = 2;
    public static final int TIPO_SERVICE = 3;
    public static final String INTENT_STRING_MSG_STATUS = "MSGs";
    public static final String INTENT_STRING_MSG = "MSG";
    public static final String INTENT_STRING_TITULO = "titu";
    public static final String INTENT_LONG_QUANDO = "WHEN";
    public static final String INTENT_INT_ICONE = "icone";
    public static final String INTENT_INT_FLAGS = "FLS";

    /**
     * 
     * @param context Atividade
     * @param param Parametros da notificacao
     * @param acao Ação quando é selecionado a notificacao
     * @param tipo Tipo da ação /broadcast/atividade/serviço/
     * @return Notification
     */
    public static Notification criarNotificacao(Context context, Intent param, Intent acao, int tipo) {
        //cria a notificacao
        Notification n = new Notification(param.getIntExtra(INTENT_INT_ICONE, 0),
                param.getStringExtra(INTENT_STRING_MSG_STATUS),
                param.getLongExtra(INTENT_LONG_QUANDO, System.currentTimeMillis()));

        //Responsavel pela acao caso o usuario selecione a notificacao;
        PendingIntent p;
        if (tipo == TIPO_ACTIVITY) {
            p = PendingIntent.getActivity(context, 0, acao, param.getIntExtra(INTENT_INT_FLAGS, 0));
        } else if (tipo == TIPO_BROADCAST) {
            p = PendingIntent.getBroadcast(context, 0, acao, param.getIntExtra(INTENT_INT_FLAGS, 0));
        } else if (tipo == TIPO_SERVICE) {
            p = PendingIntent.getService(context, 0, acao, param.getIntExtra(INTENT_INT_FLAGS, 0));
        } else {
            throw new IllegalArgumentException("tipo indefinido");
        }

        //Vincula o PendingIntent com a notificacao
        n.setLatestEventInfo(context, param.getStringExtra(INTENT_STRING_TITULO), param.getStringExtra(INTENT_STRING_MSG), p);

        //Define valores default para a notificacao ex. som,vibra.
        n.defaults = Notification.DEFAULT_ALL;

        return n;
    }

    /**
     * 
     * @param context Atividade
     * @param notification Notificação a ser exibida
     * @param id Identificador unico da notificacao
     * @return  NotificationManager
     */
    public static NotificationManager notificar(Context context, Notification notification, int id) {
        //pega serviço de notificacao
        NotificationManager nm = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
        //exibe a notificacao
        nm.notify(id, notification);
        return nm;
    }

    /**
     * 
     * @param context
     * @param id
     * @return 
     */
    public static NotificationManager removerNotificacao(Context context, int id) {
        //pega serviço de notificacao
        NotificationManager nm = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
        nm.cancel(id);
        return nm;
    }
}
