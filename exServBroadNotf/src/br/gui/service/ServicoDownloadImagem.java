package br.gui.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import br.gui.Notificacao;
import br.gui.R;
import br.gui.VisualizarImagem;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author guilherme
 */
public class ServicoDownloadImagem extends Service implements Runnable {

    private static final String LOG = "livro.brSerNot";

    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(LOG, "ServicoDownloadImagem Service = onBind");
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(LOG, "ServicoDownloadImagem Service = onCreate");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(LOG, "ServicoDownloadImagem Service = onStart");
        super.onStart(intent, startId);
        new Thread(this).start();
    }

    @Override
    public void onDestroy() {
        Log.i(LOG, "ServicoDownloadImagem Service = onDestroy");
        super.onDestroy();
    }

    public void run() {
        try {
            URL url = new URL("http://www.labjor.unicamp.br/comciencia/img/vazio/rp_caroljusto/img1.jpg");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.connect();

            InputStream in = connection.getInputStream();
            byte[] imagem = readBytes(in);
            connection.disconnect();
            in.close();

            Intent param = new Intent();
            param.putExtra(Notificacao.INTENT_STRING_TITULO, "Download terminado.");
            param.putExtra(Notificacao.INTENT_STRING_MSG, "A imagem foi baixada com sucesso.");
            param.putExtra(Notificacao.INTENT_STRING_MSG_STATUS, "Download terminado.");
            param.putExtra(Notificacao.INTENT_INT_ICONE, android.R.drawable.ic_dialog_alert);

            Intent acao = new Intent(this, VisualizarImagem.class);
            acao.putExtra("imagem", imagem);

            Notification notification = Notificacao.criarNotificacao(this, param, acao, Notificacao.TIPO_ACTIVITY);
            Notificacao.notificar(this, notification, R.string.app_name);

            stopSelf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] readBytes(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } finally {
            bos.close();
        }
    }
}
