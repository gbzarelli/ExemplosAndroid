package zarelli.biff;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import zarelli.biff.objetos.ObVideo;
import zarelli.biff.utils.ThumbnailVideo;

public class MainActivity extends Activity implements View.OnClickListener {

    private ArrayList<ObVideo> listaItens;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listaItens = criaObjetosExemplo();

//        ImageView iv = (ImageView) findViewById(R.id.imagem);
//        iv.setTag("/mnt/sdcard/Movies/exemplo_1.mp4");
//        Bitmap btm = ThumbnailVideo.getThumbnailFromVideo(new File("/mnt/sdcard/Movies/exemplo_1.mp4"));
//        iv.setImageBitmap(btm);
//        iv.setOnClickListener(this);
findViewById(R.id.imagem).setOnClickListener(this);
    }

    private ArrayList<ObVideo> criaObjetosExemplo() {
        ArrayList<ObVideo> itens = new ArrayList<ObVideo>();
        ObVideo video_1 = new ObVideo("Exemplo_1", new File("/mnt/sdcard/Movies/exemplo_1.mp4"), new Date(2013, 10, 22));
        ObVideo video_2 = new ObVideo("Exemplo_2", new File("/mnt/sdcard/Movies/exemplo_2.wmv"), new Date(2012, 10, 22));
        ObVideo video_3 = new ObVideo("Exemplo_3", new File("/mnt/sdcard/Movies/exemplo_3.mp4"), new Date(2012, 10, 30));
        itens.add(video_1);
        itens.add(video_2);
        itens.add(video_3);
        return itens;
    }

    public void onClick(View arg0) {
        String file = "http://www.camisetasaz.com.br/android/cms_noticias/videos/web.mp4";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(file));
        intent.setDataAndType(Uri.parse(file), "video/*");
        startActivity(intent);
    }
}
