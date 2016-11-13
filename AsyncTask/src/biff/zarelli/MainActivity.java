package biff.zarelli;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class MainActivity extends Activity {

    private Processo processo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Esse if/else serve para tratar a orientação.
         */
        if (savedInstanceState == null) {
            processo = new Processo(this);
            processo.execute(3000, 2000, 2000, 2000, 2000, 2000, 2000, 2000);
        } else {
            processo = (Processo) savedInstanceState.get("asyncTask");
            processo.setContext(this);
        }

        setContentView(R.layout.main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("asyncTask", processo);
        super.onSaveInstanceState(outState);
    }

    public class Processo extends AsyncTask<Integer, String, Integer> implements Parcelable {

        private ProgressDialog progress;
        private Context context;
        private String texto;

        public Processo(Context context) {
            this.context = context;
        }

        public void setContext(Context context) {
            this.context = context;
            showProgress(texto);
        }

        @Override
        protected void onPreExecute() {
            showProgress("Aguarde...");
        }

        private void showProgress(String texto) {
            progress = new ProgressDialog(context);
            progress.setMessage(texto);
            progress.show();
        }

        @Override
        protected Integer doInBackground(Integer... paramss) {
            for (int i = 0; i < paramss.length; i++) {
                try {
                    //Simula processo...
                    Thread.sleep(paramss[i]);
                    //Atualiza a interface através do onProgressUpdate
                    publishProgress(i + "...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer result) {
            //Cancela progressDialogo
            progress.dismiss();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //Atualiza mensagem
            texto = values[0];
            progress.setMessage(texto);
        }

        public int describeContents() {
            //estudar
            return 0;
        }

        public void writeToParcel(Parcel arg0, int arg1) {
            //estudar
        }
    }
}
