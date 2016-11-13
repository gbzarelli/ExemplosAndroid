/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarelli.biff;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 *
 * @author guilherme
 */
public class Servico {

    private static final String SOAP_ACTION = "http://webservice/NewWebService/helloRequest";
    private static final String METHOD_NAME = "hello";
    private static final String NAMESPACE = "http://webservice/";
    private static final String URL = "http://10.0.2.2:8080/WebServiceTeste/NewWebService";

    public String Hello(String texto) {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("name", texto);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        try {

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();

            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String Hello2(String texto) {
        SoapObject request = new SoapObject(NAMESPACE, "hello2");
        request.addProperty("name2", texto);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.addMapping("http://localhost:8080/WebServiceTeste/NewWebService?xsd=1", "resposta", new Resposta().getClass());

        try {

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call("http://webservice/NewWebService/hello2Request", envelope);

            SoapObject result = (SoapObject) envelope.getResponse();

            for (int i = 0; i < result.getPropertyCount(); i++) {
                System.out.println("-->" + result.getProperty(i).toString());
            }

            return "";

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
