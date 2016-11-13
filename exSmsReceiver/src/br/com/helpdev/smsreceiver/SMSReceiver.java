package br.com.helpdev.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * 
 * @author Guilherme Biff Zarelli
 */
public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extras = intent.getExtras();
		if (extras == null) {
			return;
		}

		Toast.makeText(context, "SMS recebido", Toast.LENGTH_LONG).show();

		Object[] pdus = (Object[]) extras.get("pdus");

		for (int i = 0; i < pdus.length; i++) {
			// Mensagem interceptada
			SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

			// Criando um Bundle para passar as informações por broadcast.
			Bundle msg = new Bundle();
			// quem enviou.
			msg.putString("sender", smsMessage.getOriginatingAddress());
			// mensagem.
			msg.putString("body", smsMessage.getMessageBody().toString());
			// data e hora da mensagem.
			msg.putLong("timestamp", smsMessage.getTimestampMillis());

			if (smsMessage.getOriginatingAddress().contains("me ligue")) {
				try {

					Intent it = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
							+ smsMessage.getOriginatingAddress()));
					it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(it);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			/**
			 * Crio um intent, a String passada é uma ação, no caso, quem for
			 * receber essa mensagem, deve recebe-la pela mesma ação enviada.
			 * 
			 * Você pode definir a ação que quiser. Mas lembre-se de manter um
			 * padrão.
			 * 
			 */
			Intent it = new Intent("br.com.helpdev.smsreceiver.SMSRECEIVER");
			// armazeno o Bundle na ação.
			it.putExtra("msg", msg);

			// Envio um broadcast com a ação e dados do Intent que configuramos.
			context.sendBroadcast(it);
		}
	}
}