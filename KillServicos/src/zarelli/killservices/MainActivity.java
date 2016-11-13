package zarelli.killservices;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

public class MainActivity extends Activity {

    private String TAG = "killservice";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            List<RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);

            //activityManager.killBackgroundProcesses(services.get(0).service.getPackageName());


            Log.d(TAG, "Procurando Serviços");
            for (int i = 0; i < services.size(); i++) {
                Log.d(TAG, "Service Nr. " + i + "\n class name : " + services.get(i).service.getClassName());
                if (services.get(i).service.getClassName().contains("facebook")) {
                    Log.e(TAG, "fac*****");
                    android.os.Process.killProcess(services.get(i).pid);
                }
            }

            /*   PackageManager pm = getPackageManager();
             final List<PackageInfo> packs = pm.getInstalledPackages(PackageManager.GET_RECEIVERS);
             for (final PackageInfo p : packs) {
             ActivityInfo[] receivers = p.receivers;
             if (receivers != null) {
             for (ActivityInfo ai : receivers) {
             Log.i(TAG, "receiver: " + ai.name);
             }
             }
             }
             */


            /*
             for (ResolveInfo resolveInfo : activities) {
             ActivityInfo activityInfo = resolveInfo.activityInfo;
             Log.i(TAG, "resolveInfo: " +resolveInfo.activityInfo.name);
             if (activityInfo != null) {
             startupApps.add(activityInfo.name);
             }
             }*/
        } catch (Exception ex) {
            Log.e(TAG, "ex: ", ex);
        }

    }
}
