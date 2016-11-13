/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package criar.SMSReceiver.audio;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import java.io.IOException;

/**
 *
 * @author guilherme
 */
public class Player {

    public static final int TYPE_AVALIABLE = 12;

    /**
     *
     * @param context
     * @param tipo Ver tipos do sistema em RingtoneManager.TYPE_****. Para
     * qualquer ver use TYPE_AVALIABLE em Player
     * @param loop
     * @throws IOException
     */
    public static MediaPlayer tocarAlarme(Context context, int tipo, boolean loop) throws IOException {
        Uri alert;
        if (tipo == TYPE_AVALIABLE) {
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            if (alert == null) {
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                if (alert == null) {
                    alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                }
            }
        } else {
            alert = RingtoneManager.getDefaultUri(tipo);
        }
        MediaPlayer mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setDataSource(context, alert);
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_ALARM, 100, AudioManager.FLAG_ALLOW_RINGER_MODES);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
        mMediaPlayer.setLooping(loop);
        mMediaPlayer.prepare();
        mMediaPlayer.start();

        return mMediaPlayer;
    }

    public static void tocarAudioRid() {













    }
}
