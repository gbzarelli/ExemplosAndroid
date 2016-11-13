package br.com.grupocriar.excodigobarraszxing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, ZxingReader.ZxingReaderCallback {

    private ZxingReader zxingReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_read).setOnClickListener(this);
        zxingReader = new ZxingReader(this, this);
    }


    @Override
    public void onClick(View v) {
        zxingReader.read(20, ZxingReader.TIPE_CODEBAR);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        zxingReader.onActivityResult(requestCode, resultCode, intent);
    }


    @Override
    public void result(boolean sucesso, String scanResult, String scanResultFormat) {

    }
}
