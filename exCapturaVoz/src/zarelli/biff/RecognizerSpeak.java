package zarelli.biff;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.speech.RecognizerIntent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme Biff Zarelli
 */
public class RecognizerSpeak {

    public interface RecognizerSpeakListener {

        public static final String ACTION_SUCESSO = "sucesso";
        public static final String ACTION_NAO_SUPORTADO = "nao_suportado";

        public void retornoSpeakListener(String action, List retornos, int selecionado, int intent_request);
    }
    private List retorno_speech;
    private int resultado_selecionado, intent_request;
    private Activity activity;
    private RecognizerSpeak.RecognizerSpeakListener listener;
    private boolean alertas;

    public RecognizerSpeak(Activity activity, RecognizerSpeak.RecognizerSpeakListener listener, boolean alertas) {
        this.activity = activity;
        this.listener = listener;
        this.alertas = alertas;
    }

    public void iniciaCaptura(String descricao, int intent_request) {
        this.intent_request = intent_request;
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, descricao);
        if (isIntentAvailable()) {
            activity.startActivityForResult(intent, intent_request);
        } else {
            if (alertas) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setTitle("Falha");
                builder.setMessage("Seu dispositivo não suporta este recurso.");
                builder.setPositiveButton("OK", null);
                AlertDialog alerta = builder.create();
                alerta.show();
            }
            if (listener != null) {
                List lista = new ArrayList<String>();
                lista.add("Seu dispositivo não suporta este recurso.");
                listener.retornoSpeakListener(RecognizerSpeak.RecognizerSpeakListener.ACTION_NAO_SUPORTADO,
                        lista, 0, intent_request);
            }
        }
    }

    private void retornoRecognizeSpeech() {
        CharSequence[] lista = new CharSequence[retorno_speech.size()];
        for (int i = 0; i < retorno_speech.size(); i++) {
            lista[i] = retorno_speech.get(i).toString();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("O que você quis dizer?");
        builder.setSingleChoiceItems(lista, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                resultado_selecionado = arg1;
            }
        });
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                if (listener != null) {
                    listener.retornoSpeakListener(RecognizerSpeak.RecognizerSpeakListener.ACTION_SUCESSO, retorno_speech, resultado_selecionado, intent_request);
                }
            }
        });

        builder.create().show();
    }

    public void setActivityResult(int request_code, int result_code, Intent data) {
        if (request_code == intent_request && result_code == Activity.RESULT_OK) {
            retorno_speech = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            if (alertas) {
                retornoRecognizeSpeech();
            } else {
                if (listener != null) {
                    listener.retornoSpeakListener(RecognizerSpeak.RecognizerSpeakListener.ACTION_SUCESSO, retorno_speech, 0, intent_request);
                }
            }
        }
    }

    private boolean isIntentAvailable() {
        return activity.getPackageManager()
                .queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH),
                PackageManager.MATCH_DEFAULT_ONLY)
                .size() > 0;
    }
}
