package biff.zarelli;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.File;
import java.io.Serializable;

public class MainActivity extends Activity {
    
    private ProgressDialog progressDialog;
    private Compactador c;
    private WebView webView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Aguarde...");
        
        if (savedInstanceState == null) {
            progressDialog.show();
            c = new Compactador(this);
            c.execute();
        } else {
            c = (Compactador) savedInstanceState.getSerializable("comp");
            c.setActivity(this);
            if (c.getStatus() == AsyncTask.Status.RUNNING) {
                progressDialog.show();
            }
        }
        
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        
    }
    
    private class MyWebViewClient extends WebViewClient {
        
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("comp", c);
    }
    
    public class Compactador extends AsyncTask<Object, Object, Object> implements Serializable {
        
        private transient MainActivity activity;
        
        public Compactador(MainActivity activity) {
            this.activity = activity;
        }
        
        public void setActivity(MainActivity activity) {
            this.activity = activity;
        }
        
        @Override
        protected Object doInBackground(Object... paramss) {
            try {
                File f = activity.getApplicationContext().getDir("zarelliUHU", MODE_PRIVATE);
                if (!f.exists()) {
                    f.mkdir();
                }
                for (File ff : f.listFiles()) {
                    if (ff.getName().endsWith(".zip")) {
                        Zip.descompactar(ff.getAbsoluteFile().toString(), f.getAbsolutePath());
                        ff.delete();
                    } else if (ff.getName().startsWith("index")) {
                        webView.loadUrl("file:///" + ff.getAbsoluteFile().toString());
                    }
                }
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        }
        
        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            activity.progressDialog.dismiss();
        }
    }
}
