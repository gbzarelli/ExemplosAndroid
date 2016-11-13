package br.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

/**
 *
 * @author guilherme
 */
public class Main extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button bt = new Button(this);
        bt.setText("Iniciar");
        bt.setOnClickListener(new OnClickListener() {
            
            public void onClick(View arg0) {
                sendBroadcast(new Intent("android.intent.action.BOOT_COMPLETED2"));
            }
        });
        addContentView(bt, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }
}
