/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordpress.zarelli.bootreceiver;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 *
 * @author guilherme
 */
public class Service extends android.app.Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate(); //To change body of generated methods, choose Tools | Templates.
        Log.i(MainActivity.TAG, "Serviço iniciado");
    }
}
