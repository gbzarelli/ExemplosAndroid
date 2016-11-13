package com.example.ksoapteste2.objetos;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class RegistraDispositivoOut implements KvmSerializable {

	private String CodigoRetorno;
	private String MensagemRetorno;
	private String EnderecoAcesso;

	@Override
	public Object getProperty(int arg0) {
		switch (arg0) {
		case 0:
			return CodigoRetorno;

		case 1:
			return MensagemRetorno;

		case 2:
			return EnderecoAcesso;
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
			propertie.name = "CodigoRetorno";
			break;
		case 1:
			propertie.type = PropertyInfo.STRING_CLASS;
			propertie.name = "MensagemRetorno";
			break;
		case 2:
			propertie.type = PropertyInfo.STRING_CLASS;
			propertie.name = "EnderecoAcesso";
			break;
		default:
			break;
		}
	}

	@Override
	public void setProperty(int index, Object propertie) {
		switch (index) {
		case 0:
			CodigoRetorno = propertie.toString();
			break;
		case 1:
			MensagemRetorno = propertie.toString();
			break;
		case 2:
			EnderecoAcesso = propertie.toString();
			break;
		default:
			break;
		}
	}

}
