/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package criar.SMSReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 *
 * @author guilherme
 */
public class InCallReceiver extends BroadcastReceiver {

    private static final String TAG = "receiverPhone";

    @Override
    public void onReceive(Context arg0, Intent intent) {
        Bundle extras = intent.getExtras();
        String phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        String state = extras.getString(TelephonyManager.EXTRA_STATE);
        String ringing = extras.getString(TelephonyManager.EXTRA_STATE_RINGING);
        String offhook = extras.getString(TelephonyManager.EXTRA_STATE_OFFHOOK);
        Log.i(TAG, "Phone number: " + phoneNumber);
        Log.i(TAG, "Phone state: " + state);
        Log.i(TAG, "Phone ringing: " + ringing);
        Log.i(TAG, "Phone offhook: " + offhook);


    }
}
