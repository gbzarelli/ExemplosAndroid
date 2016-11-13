/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grupocriar.chavecontrachave;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import java.util.HashMap;

/**
 *
 * @author guilherme
 */
public class ChaveSistema {

    private static HashMap<String, Integer> mapaLetras;

    public static String getContraChave(String chave) throws Exception {
        ChaveSistema chaveSistema = new ChaveSistema();
        return chaveSistema.gerarContraChave(chave);
    }

    public static String getChave(Activity activity) throws Exception {
        StringBuilder preChave = new StringBuilder();

        TelephonyManager tManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);

        preChave.append(tManager.getDeviceId());
        preChave.append(Build.BOARD.length() % 10);
        preChave.append(Build.BRAND.length() % 10);
        preChave.append(Build.DEVICE.length() % 10);
        preChave.append(Build.DISPLAY.length() % 10);
        preChave.append(Build.HOST.length() % 10);
        preChave.append(Build.ID.length() % 10);
        preChave.append(Build.MODEL.length() % 10);
        preChave.append(Build.PRODUCT.length() % 10);
        preChave.append(Build.TAGS.length() % 10);
        preChave.append(Build.TYPE.length() % 10);
        preChave.append(Build.USER.length() % 10);

        StringBuilder chave = new StringBuilder();
        chave.append(MD5.toMD5(preChave.toString()));

        int tamanho = chave.length() / 2;

        chave.delete(0, 1);
        chave.delete(tamanho, tamanho + 1);

        return chave.toString().toUpperCase();
    }

    private ChaveSistema() {
        mapaLetras = new HashMap<String, Integer>();
        mapaLetras.put("A", 8);
        mapaLetras.put("B", 7);
        mapaLetras.put("C", 6);
        mapaLetras.put("D", 5);
        mapaLetras.put("E", 4);
        mapaLetras.put("F", 3);
        mapaLetras.put("G", 2);
        mapaLetras.put("H", 1);
        mapaLetras.put("I", 9);
        mapaLetras.put("J", 8);
        mapaLetras.put("K", 7);
        mapaLetras.put("L", 6);
        mapaLetras.put("M", 5);
        mapaLetras.put("N", 4);
        mapaLetras.put("O", 3);
        mapaLetras.put("P", 2);
        mapaLetras.put("Q", 1);
        mapaLetras.put("R", 9);
        mapaLetras.put("S", 8);
        mapaLetras.put("T", 7);
        mapaLetras.put("U", 6);
        mapaLetras.put("V", 5);
        mapaLetras.put("X", 4);
        mapaLetras.put("W", 3);
        mapaLetras.put("Y", 2);
        mapaLetras.put("Z", 1);
    }

    private String gerarContraChave(String chave) throws Exception {
        StringBuilder contraChave = new StringBuilder();
        contraChave.append(contraChaveProcesso(chave.substring(0, 5)));
        contraChave.append(contraChaveProcesso(chave.substring(5, 10)));
        contraChave.append(contraChaveProcesso(chave.substring(10, 15)));
        contraChave.append(contraChaveProcesso(chave.substring(15, 20)));
        contraChave.append(contraChaveProcesso(chave.substring(20, 25)));
        contraChave.append(contraChaveProcesso(chave.substring(25, 30)));

        return contraChave.toString();
    }

    private String contraChaveProcesso(String comp) {
        int v = 0;
        int res1 = 0;
        double t;
        double valor = 0;

        int op = 0; //0 = soma; 1 = multiplicacao
        int opOld = 0;//0 = soma; 1 = multiplicacao

        for (int i = 0; i < comp.length(); i++) {
            //Separa valor e operação
            if (Character.isDigit(comp.charAt(i))) {
                v = Integer.parseInt(comp.substring(i, i + 1));
                op = 0;
            } else if (Character.isLetter(comp.charAt(i))) {
                v = mapaLetras.get(comp.substring(i, i + 1));
                op = 1;
            }

            //Realiza Conta
            if (i == 0) {
                res1 = v;
            } else {
                if (opOld == 0) {
                    res1 += v;
                } else {
                    res1 = res1 * v;
                }
            }
            opOld = op;
        }

        comp = String.valueOf(res1);
        t = comp.length();

        for (int i = 0; i < t; i++) {
            valor += Integer.parseInt(comp.substring(i, i + 1));
        }

        double valor1 = valor / t;

        String str = Double.toString(valor1);
        String res = str.substring(0, str.indexOf("."));

        return res;
    }
}
