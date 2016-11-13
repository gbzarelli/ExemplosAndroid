package br.com.helpdev.exverificaconexao;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView status, tipo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		status = (TextView) findViewById(R.id.tv_status_conexao);
		tipo = (TextView) findViewById(R.id.tv_tipo);
		verificaStatus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		verificaStatus();
		return super.onMenuItemSelected(featureId, item);
	}

	public boolean isConectado() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();

		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	public void verificaTipo() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null) {
			switch (netInfo.getType()) {
			case ConnectivityManager.TYPE_WIFI:
				tipo.setText("TYPE WIFI");
				break;
			case ConnectivityManager.TYPE_MOBILE:
				tipo.setText("TYPE MOBILE");
				break;
			}
		} else {
			tipo.setText(" - ");
		}
	}

	public void verificaStatus() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null) {
			switch (netInfo.getState()) {
			case CONNECTED:
				status.setText("CONNECTED");
				break;
			case CONNECTING:
				status.setText("CONNECTING");
				break;
			case DISCONNECTED:
				status.setText("DISCONNECTED");
				break;
			case DISCONNECTING:
				status.setText("DISCONNECTING");
				break;
			case SUSPENDED:
				status.setText("SUSPENDED");
				break;
			case UNKNOWN:
				status.setText("UNKNOWN");
				break;
			}
		verificaTipo();
			
		} else {
			status.setText("STATUS INDISPONIVEL");
			tipo.setText(" - ");
		}
	}
}
