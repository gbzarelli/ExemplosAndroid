package br.com.grupocriar.excodigobarraszxing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

/**
 * Created by Guilherme Biff Zarelli on 04/03/15.
 */
public class ZxingReader {
    public interface ZxingReaderCallback {
        public void result(boolean sucesso, String scanResult, String scanResultFormat);
    }

    public static final String TIPE_CODEBAR = "CODABAR";
    public static final String TIPE_QR_CODE = "QR_CODE_MODE";
    private static final String PACKAGE = "com.google.zxing.client.android";
    private int idForResult;
    private Activity activity;
    private ZxingReaderCallback zxingReaderCallback;

    public ZxingReader(Activity activity, ZxingReaderCallback zxingReaderCallback) {
        this.activity = activity;
        this.zxingReaderCallback = zxingReaderCallback;
    }

    public void setActivity(Activity activity,ZxingReaderCallback zxingReaderCallback) {
        this.activity = activity;
        this.zxingReaderCallback=zxingReaderCallback;
    }

    public void read(int idForResult, String type) {
        Intent intent = new Intent(PACKAGE + ".SCAN");
        intent.putExtra("SCAN_MODE", type);
        if (isPackageInstalled(PACKAGE)) {
            this.idForResult = idForResult;
            activity.startActivityForResult(intent, idForResult);
        } else {
            showDownloadDialog("Instalar Barcode Scanner?", "Este aplicativo requer o Barcode Scanner. Gostaria de instalá-lo?", "Sim", "Não");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == idForResult) {
            String scanResult = null;
            String scanResultFormat = null;
            if (resultCode == activity.RESULT_OK) {
                scanResult = intent.getStringExtra("SCAN_RESULT");
                scanResultFormat = intent.getStringExtra("SCAN_RESULT_FORMAT");
            }
            if (zxingReaderCallback != null) {
                zxingReaderCallback.result(resultCode == activity.RESULT_OK, scanResult, scanResultFormat);
            }
        }
    }

    private boolean isPackageInstalled(String packagename) {
        PackageManager pm = activity.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private AlertDialog showDownloadDialog(
            CharSequence stringTitle,
            CharSequence stringMessage,
            CharSequence stringButtonYes,
            CharSequence stringButtonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(activity);
        downloadDialog.setTitle(stringTitle);
        downloadDialog.setMessage(stringMessage);
        downloadDialog.setPositiveButton(stringButtonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + PACKAGE);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    activity.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {
                    Log.w("TESTE", "Android Market is not installed; cannot install Barcode Scanner");
                }
            }
        });
        downloadDialog.setNegativeButton(stringButtonNo, null);
        return downloadDialog.show();
    }
}
