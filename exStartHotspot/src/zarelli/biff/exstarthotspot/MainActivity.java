package zarelli.biff.exstarthotspot;

import android.app.Activity;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends Activity {

    private WifiManager wifi_manager;
    private static final String TAG = "zarelli.biff.exstarthotspot";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            WifiAP wap = new WifiAP(this);
            int i = wap.getWifiApState();
            Toast.makeText(this, "getwifiapstate: " + i, Toast.LENGTH_LONG).show();
            //            WifiConfiguration netConfig = new WifiConfiguration();
//            netConfig.SSID = "\"EVE01\"";
//            netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
//            netConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
//            netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
//            netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
//
//            wap.setWifiApConfiguration(netConfig, true);

        } catch (Exception e) {
            Log.e(TAG, "ativar hotspot", e);
        }



    }
}
