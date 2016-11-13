package zarelli.ex;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MultiAutoCompleteTextView autoComplete = (MultiAutoCompleteTextView) findViewById(R.id.autocomplete);

        String[] listaItens = new String[]{"Carro", "Helicóptero", "Avião", "Caminhão", "Bicicleta", "Motocicleta", "Trem"};
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item, listaItens);
        autoComplete.setAdapter(adapter);
        autoComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
