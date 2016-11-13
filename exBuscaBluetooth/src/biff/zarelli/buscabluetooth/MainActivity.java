package biff.zarelli.buscabluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements Bluetooth.BluetoothListener {

    private static final String TAG = "BuscaBluetooth";
    private Bluetooth bluetooth;
    private ArrayAdapter arrayAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            bluetooth = Bluetooth.startFindDevices(this, this);
        } catch (Exception e) {
            Log.e(TAG, "Erro: ", e);
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        ListView listView = (ListView) findViewById(R.idMain.listview);
        listView.setAdapter(arrayAdapter);
    }

    public void action(String action) {
        if (action.compareTo(ACTION_DISCOVERY_STARTED) == 0) {
            arrayAdapter.add("-Busca iniciada");
        } else if (action.compareTo(ACTION_DISCOVERY_FINISHED) == 0) {
            preencherLista();
            arrayAdapter.add("-Fim de busca.");
        }
    }

    private void preencherLista() {
        for (BluetoothDevice device : bluetooth.getDispositivos()) {
            arrayAdapter.add(device.getName() + "\n" + device.getAddress());
        }
    }
}
