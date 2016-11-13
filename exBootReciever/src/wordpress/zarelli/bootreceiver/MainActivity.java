package wordpress.zarelli.bootreceiver;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    public static final String TAG = "exBootReceiver";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
