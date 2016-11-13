/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package criar.SMSReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 *
 * @author guilherme
 */
public class OutCallReceiver extends BroadcastReceiver {
    
    private static final String TAG = "Call_Receiver";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        System.out.println(bundle.get(Intent.EXTRA_PHONE_NUMBER));
    }
}
