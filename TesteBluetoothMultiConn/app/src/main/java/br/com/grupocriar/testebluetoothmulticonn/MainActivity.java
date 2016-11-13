package br.com.grupocriar.testebluetoothmulticonn;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.UUID;


public class MainActivity extends ActionBarActivity {
    boolean execucao = true;
    private ArrayAdapter<String> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(adapter.getCount() - 1);
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teste1("00:12:02:22:03:24");
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teste1("20:13:06:09:30:35");
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executandoThread = true;
                teste2("00:12:02:22:03:24", "20:13:06:09:30:35");
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executandoThread = false;
            }
        });
    }

    boolean executandoThread = true;

    public void teste2(final String... macs) {
        new Thread() {
            @Override
            public void run() {
                try {
                    int pos = 0;
                    while (executandoThread) {
                        if (execucao) {
                            if (pos == macs.length) {
                                pos = 0;
                            }
                            Bluetooth bluetooth = Bluetooth.getBondedDevices(MainActivity.this);
                            for (BluetoothDevice device : bluetooth.getDispositivos()) {
                                if (device.getAddress().equals(macs[pos])) {
                                    pos++;
                                    execucao = false;
                                    MyThread myThread = new MyThread(device, new MyThreadCallback() {
                                        @Override
                                        public void end() {
                                            execucao = true;
                                        }
                                    });
                                    myThread.start();
                                    break;
                                }
                            }
                        } else {
                            Thread.sleep(100);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    public void teste1(final String mac) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Bluetooth bluetooth = Bluetooth.getBondedDevices(MainActivity.this);
                    for (BluetoothDevice device : bluetooth.getDispositivos()) {
                        if (device.getAddress().equals(mac)) {
                            if (execucao) {
                                execucao = false;
                                MyThread myThread = new MyThread(device, new MyThreadCallback() {
                                    @Override
                                    public void end() {
                                        execucao = true;
                                    }
                                });
                                myThread.start();
                            } else {
                                Thread.sleep(1000);
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showToast("EXCESSAO1");
                }
            }
        }.start();
    }

    public interface MyThreadCallback {
        public void end();
    }

    public class MyThread extends Thread {

        private BluetoothDevice device;
        private MyThreadCallback callback;

        public MyThread(BluetoothDevice device, MyThreadCallback callback) {
            this.device = device;
            this.callback = callback;
        }

        @Override
        public void run() {
            try {
                BluetoothSocket socket = device.createInsecureRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                showToast("Conectando: " + device.getName());
                socket.connect();
                showToast("Conectado " + device.getName());
                socket.getOutputStream().write("AT".getBytes());
                socket.getOutputStream().flush();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int read;
                do {
                    read = socket.getInputStream().read(buffer);
                    baos.write(buffer, 0, read);
                    Thread.sleep(200);
                } while (socket.getInputStream().available() > 0);

                showToast(device.getName() + "-RETORNO: " + new String(baos.toByteArray()));

                socket.getOutputStream().close();
                socket.getInputStream().close();
                socket.close();
                showToast("Desconectando " + device.getName());
                while (socket.isConnected()) {
                    Thread.sleep(100);
                }
                socket = null;
                showToast("Desconectado " + device.getName());
            } catch (Exception ex) {
                ex.printStackTrace();
                showToast("EXCESSAO2");
            } finally {
                callback.end();
            }

        }
    }

    public void showToast(final String msg) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.add(new Date().toString() + ": " + msg);
            }
        });
    }
}
