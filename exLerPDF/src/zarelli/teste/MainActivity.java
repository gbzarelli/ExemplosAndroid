package zarelli.teste;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {

    private static final String googleDocsUrl = "http://docs.google.com/viewer?url=";
    private String url = "http://www.crvlagoa.com.br/images/upload/atlantico_cat2012.pdf";

    ;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        //exemplo3();
        exemplo5();
    }

    public void exemplo1() {
        String url = "http://www.crvlagoa.com.br/images/upload/atlantico_cat2012.pdf";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(googleDocsUrl + url), "text/html");
        startActivity(intent);
    }

    public void exemplo5() {
        String path = "<iframe =fasf=babhaubhuashuab  http://www.youtube.com/embed/k4fzyjbAc-o?rel=0 aagasgf >";
        Pattern p = Pattern.compile("(http\\://{1}\\S+)");
        Matcher m = p.matcher(path);
        m.find();
        System.out.println(m.group(1));

        //Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse());
        //startActivity(it);



    }

    public void exemplo2() {
        File file = new File("/sdcard/atlantico_cat2012.pdf");

        if (file.exists()) {
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                AlertDialog.Builder alerBuilder = new AlertDialog.Builder(this);
                alerBuilder.setTitle(R.string.app_name);
                alerBuilder.setMessage("Seu dispositivo não tem um leitor de PDF.");
                alerBuilder.setPositiveButton("Play Store", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String link = "https://play.google.com/store/apps/details?id=com.adobe.reader";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                        startActivity(intent);
                    }
                });
                alerBuilder.setNegativeButton("Cancelar", null);
                alerBuilder.show();
            }
        }
    }

    public void exemplo3() {
        WebView mWebView = new WebView(this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + url);
        setContentView(mWebView);
    }
}
