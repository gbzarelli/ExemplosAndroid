package criar.verificaServicos;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "VerificaServicos";
    private final Intent it = new Intent("android.intent.action.START_MOBILE_TRACKER");
    private TextView info;
    private Button bt_start;
    private Button bt_stop;
    private Button bt_verifica;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        info = (TextView) findViewById(R.id.text);
        bt_start = (Button) findViewById(R.id.br_start);
        bt_start.setOnClickListener(this);
        bt_stop = (Button) findViewById(R.id.bt_stop);
        bt_stop.setOnClickListener(this);
        bt_verifica = (Button) findViewById(R.id.bt_verifica);
        bt_verifica.setOnClickListener(this);
        ListView log = (ListView) findViewById(R.id.list_log);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.item_text);
        log.setAdapter(arrayAdapter);
        verifica();

    }

    private void verifica() {
        if (isServiceRunning()) {
            info.setText("RODANDO");
            bt_start.setEnabled(false);
            bt_stop.setEnabled(true);
        } else {
            info.setText("PARADO");
            bt_start.setEnabled(true);
            bt_stop.setEnabled(false);
        }
    }

    private void addTextLog(String log) {
        Date date = new Date();
        date.setTime(System.currentTimeMillis());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        String dataHora = sdf.format(date);

        arrayAdapter.add(dataHora + " - " + log);
        arrayAdapter.notifyDataSetChanged();
    }

    public boolean isServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);
        addTextLog("Procurando Serviços");
        for (int i = 0; i < services.size(); i++) {
            addTextLog("Service Nr. " + i + " class name : " + services.get(i).service.getClassName()+
                    "\n Process: "+services.get(i).process);
            if ("criar.mobiletracker.service.ServicoGPS".equals(services.get(i).service.getClassName())) {
                addTextLog("Servico encontrado");
                return true;
            }
        }
        return false;
    }

    public void onClick(View arg0) {
        if (arg0.equals(findViewById(R.id.bt_verifica))) {
            verifica();
        } else if (arg0.equals(findViewById(R.id.bt_stop))) {
            addTextLog("Parando serviço");
            stopService(it);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            }
            verifica();
        } else if (arg0.equals(findViewById(R.id.br_start))) {
            addTextLog("Iniciando serviço");
            startService(it);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            }
            verifica();
        }
    }
}
