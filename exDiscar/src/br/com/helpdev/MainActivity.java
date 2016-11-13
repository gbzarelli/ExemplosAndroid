package br.com.helpdev;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText editText;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        editText = (EditText) findViewById(R.id.numero);
        findViewById(R.id.discar).setOnClickListener(this);
    }

    public void onClick(View arg0) {
        String telefone = editText.getText().toString();

        if (telefone.trim().length() > 0) {
            Uri uri = Uri.parse("tel:" + telefone);
            Intent intencao = new Intent(Intent.ACTION_CALL, uri);
            startActivity(intencao);
        } else {
            Toast.makeText(this, "Informe um número.", Toast.LENGTH_LONG).show();
        }

    }
}
