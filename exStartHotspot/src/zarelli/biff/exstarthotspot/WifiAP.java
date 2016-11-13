/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarelli.biff.exstarthotspot;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import java.lang.reflect.Method;

/**
 *
 * @author guilherme
 */
public class WifiAP {

    private WifiManager wifi_manager;
    public static int WIFI_AP_STATE_DISABLED, WIFI_AP_STATE_ENABLING,
            WIFI_AP_STATE_ENABLED, WIFI_AP_STATE_FAILED, WIFI_AP_STATE_DISABLING;

    /**
     * Permissões necessarias:
     *
     * <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
     * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
     *
     * @param context
     */
    public WifiAP(Context context) throws Exception {
        wifi_manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WIFI_AP_STATE_DISABLED = (Integer) WifiManager.class.getDeclaredField("WIFI_AP_STATE_DISABLED").get(Integer.class);
        WIFI_AP_STATE_ENABLING = (Integer) WifiManager.class.getDeclaredField("WIFI_AP_STATE_ENABLING").get(Integer.class);
        WIFI_AP_STATE_ENABLED = (Integer) WifiManager.class.getDeclaredField("WIFI_AP_STATE_ENABLED").get(Integer.class);
        WIFI_AP_STATE_FAILED = (Integer) WifiManager.class.getDeclaredField("WIFI_AP_STATE_FAILED").get(Integer.class);
        WIFI_AP_STATE_DISABLING = (Integer) WifiManager.class.getDeclaredField("WIFI_AP_STATE_DISABLING").get(Integer.class);
    }

    public WifiConfiguration getWifiApConfiguration() throws Exception {
        Method wifiApConfigurationMethod = wifi_manager.getClass().getMethod("getWifiApConfiguration");
        return (WifiConfiguration) wifiApConfigurationMethod.invoke(wifi_manager);
    }

    /**
     * Exemplo:
     * <br>WifiConfiguration netConfig = new WifiConfiguration();
     * <br>netConfig.SSID = "EVE03";
     * <br>netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
     * <br>netConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
     * <br>netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
     * <br>netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
     *
     * @param wifiConfiguration passar null para manter as configurações atuais
     * @param enable habilitar ou nao o hotspot/wifi ap
     * @throws Exception
     */
    public void setWifiApEnable(WifiConfiguration wifiConfiguration, boolean enable) throws Exception {
        Method wifiApConfigurationMethod = wifi_manager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
        wifiApConfigurationMethod.invoke(wifi_manager, wifiConfiguration, enable);
    }

    /**
     *
     * @return Retorna o status do wifi ap.
     * @throws Exception
     */
    public int getWifiApState() throws Exception {
        Method wifiApState = wifi_manager.getClass().getMethod("getWifiApState");
        return (Integer) wifiApState.invoke(wifi_manager);
    }
}
