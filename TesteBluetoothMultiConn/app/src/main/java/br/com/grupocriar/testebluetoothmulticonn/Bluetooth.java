package br.com.grupocriar.testebluetoothmulticonn;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

/**
 * <uses-permission android:name="android.permission.BLUETOOTH"/>
 * <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
 *
 * @author Guilherme Biff Zarelli
 */
public class Bluetooth {


    /**
     * Método responsável por "pegar" os dispositivos pareados com o aparelho
     *
     * @param applicationContext
     * @return
     * @throws java.io.IOException
     */
    public static Bluetooth getBondedDevices(Context applicationContext) throws IOException {
        Bluetooth bluetooth = new Bluetooth();

        // Pega o dispositivo
        bluetooth.dispositivo = BluetoothAdapter.getDefaultAdapter();

        if (!bluetooth.dispositivo.isEnabled()) {
            bluetooth.dispositivo.enable();
        }

        // Pega a lista de dispositivos pareados
        Set<BluetoothDevice> pairedDevices = bluetooth.dispositivo.getBondedDevices();

        // Adiciono na lista e depois retorno a mesma.
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                bluetooth.lista.add(device);
            }
        }

        return bluetooth;
    }


    private ArrayList<BluetoothDevice> lista;
    private BluetoothAdapter dispositivo;

    public BluetoothAdapter getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(BluetoothAdapter dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Bluetooth() {
        lista = new ArrayList<>();
    }

    public void desabilitarAdapter() {
        BluetoothAdapter.getDefaultAdapter().disable();
    }

    public void unpairDevice(BluetoothDevice blDevice) throws Exception, Throwable {
        Method m = blDevice.getClass().getMethod("removeBond", (Class[]) null);
        m.invoke(blDevice, (Object[]) null);
    }

    public boolean cancelDiscovery() {
        return dispositivo.cancelDiscovery();
    }

    public ArrayList<BluetoothDevice> getDispositivos() {
        return lista;
    }


}
