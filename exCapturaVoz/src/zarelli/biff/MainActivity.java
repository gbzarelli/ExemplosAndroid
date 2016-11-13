package zarelli.biff;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.List;

public class MainActivity extends Activity implements RecognizerSpeak.RecognizerSpeakListener {

    private static final int VOICE_RECOGNITION_REQUEST_CODE = 56;
    private RecognizerSpeak recognizeSpeech;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        recognizeSpeech = new RecognizerSpeak(this, this, true);
        recognizeSpeech.iniciaCaptura("TEsteE", VOICE_RECOGNITION_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        recognizeSpeech.setActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void retornoSpeakListener(String action, List retornos, int selecionado, int intent_request) {
        System.out.println("action: " + action);
        System.out.println("retorno: " + retornos.get(selecionado));
    }
}
