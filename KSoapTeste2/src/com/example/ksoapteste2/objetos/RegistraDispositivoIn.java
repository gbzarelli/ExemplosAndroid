package com.example.ksoapteste2.objetos;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class RegistraDispositivoIn implements KvmSerializable {

	private String Identificador;

	@Override
	public Object getProperty(int arg0) {
		switch (arg0) {
		case 0:
			return Identificador;
		}
		return null;
	}

	@Override
	public int getPropertyCount() {
		return 1;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable arg1,
			PropertyInfo propertie) {
		switch (index) {
		case 0:
			propertie.type = PropertyInfo.STRING_CLASS;
			propertie.name = "Identificador";
			break;
		default:
			break;
		}
	}

	@Override
	public void setProperty(int index, Object propertie) {
		switch (index) {
		case 0:
			Identificador = propertie.toString();
			break;
		default:
			break;
		}
	}

}
