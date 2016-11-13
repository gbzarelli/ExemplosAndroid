package br.gui;

import android.app.Activity;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class VisualizarImagem extends Activity {

    private static final String LOG = "livro.brSerNot";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG, "VisualizarImagem = onCreate");
        setContentView(R.layout.main);
        byte[] img = getIntent().getByteArrayExtra("imagem");

        ImageView im = (ImageView) findViewById(R.id.imagem);
        im.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));

        NotificationManager nm = (NotificationManager) getSystemService(Activity.NOTIFICATION_SERVICE);
        nm.cancel(R.string.app_name);
    }
}
