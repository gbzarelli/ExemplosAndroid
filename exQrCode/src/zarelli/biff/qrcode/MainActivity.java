package zarelli.biff.qrcode;

import zarelli.biff.qrcode.zxing.leitura.IntentResult;
import zarelli.biff.qrcode.zxing.leitura.QRCodeIntent;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import zarelli.biff.qrcode.zxing.Contents;
import zarelli.biff.qrcode.zxing.Intents;
import zarelli.biff.qrcode.zxing.encode.QRCodeEncoder;

public class MainActivity extends Activity implements View.OnClickListener {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        QRCodeIntent integrator = new QRCodeIntent(this);
        integrator.initiateScan();
        
        findViewById(R.id.botao).setOnClickListener(this);
        
        
    }
    
    private void gerarQRCode() throws WriterException {
        String texto = ((EditText) findViewById(R.id.texto)).getText().toString();
        if (texto.length() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intents.Encode.ACTION);
            intent.putExtra(Intents.Encode.TYPE, Contents.Type.TEXT);
            intent.putExtra(Intents.Encode.DATA, texto);
            intent.putExtra(Intents.Encode.FORMAT, BarcodeFormat.QR_CODE);

            /*     WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
             Display display = manager.getDefaultDisplay();
             int width = display.getWidth();
             int height = display.getHeight();
             int smallerDimension = width < height ? width : height;
             smallerDimension = smallerDimension * 7 / 8;
             System.out.println("smallerDimension: "+smallerDimension);
             */
            
            QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(this, intent, 300, false);
            ((ImageView) findViewById(R.id.qrcode)).setImageBitmap(qrCodeEncoder.encodeAsBitmap());
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = QRCodeIntent.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            System.out.println(scanResult.toString());
        }
    }
    
    public void onClick(View arg0) {
        try {
            gerarQRCode(); 
        } catch (Exception e) {
            Log.e(MainActivity.class.getSimpleName(), "gerarQRCode", e);
        }
    }
}
