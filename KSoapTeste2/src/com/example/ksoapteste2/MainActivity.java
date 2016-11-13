package com.example.ksoapteste2;

import java.io.FileDescriptor;

import com.example.ksoapteste2.objetos.DispositivoIn;
import com.example.ksoapteste2.objetos.Service1;
import com.example.ksoapteste2.objetos2.Field;
import com.example.ksoapteste2.objetos2.IWsdl2CodeEvents;
import com.example.ksoapteste2.objetos2.Service2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity implements IWsdl2CodeEvents{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Service2 service2 = new Service2(this, "http://treinamento.ecargoasp.com.br/ecargoMobile/Service.svc", 30);
		
		try {
			Field field = new Field();
			field.grupo="conhecimento";
			field.campo="id";
			field.indice="1";
			field.valor="123456";
					
		
			service2.ConsultarTrackingAsync(field);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void Wsdl2CodeStartedRequest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Wsdl2CodeFinished(String methodName, Object Data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Wsdl2CodeFinishedWithException(Exception ex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Wsdl2CodeEndedRequest() {
		// TODO Auto-generated method stub
		
	}

}
