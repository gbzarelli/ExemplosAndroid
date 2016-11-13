package grupocriar.chavecontrachave;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            String chave = ChaveSistema.getChave(this);
            ((TextView) findViewById(R.id.chave)).setText(chave);
            ((TextView) findViewById(R.id.contra_chave)).setText(ChaveSistema.getContraChave(chave));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
