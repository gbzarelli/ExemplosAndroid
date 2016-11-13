package br.gui.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import br.gui.service.ServicoDownloadImagem;
import java.util.Calendar;

/**
 *
 * @author guilherme
 */
public class IniciarDownload extends BroadcastReceiver {

    private static final String LOG = "livro.brSerNot";

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        Log.i(LOG, "IniciarDownload BroadcastReceiver = onReceive");
        agendar(arg0, 30);
    }

    private void agendar(Context contexto, int segundos) {
        Intent it = new Intent("DOWNLOAD_IMAGEM");
        PendingIntent pd = PendingIntent.getService(contexto, 0, it, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, segundos);

        AlarmManager alarmManager = (AlarmManager) contexto.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pd);
    }
}
