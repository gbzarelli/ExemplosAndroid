/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarelli.biff;

import java.util.Vector;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 *
 * @author guilherme
 */
public class ServicoCEP {

    public void consultaCEP() {
        //namespace - name message
        SoapObject request = new SoapObject("urn:http://www.byjg.com.br", "obterCEP");
        request.addProperty("logradouro", "rua oswaldo ortolan");
        request.addProperty("localidade", "Ribeirão Preto");
        request.addProperty("UF", "SP");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        try {
            //url
            HttpTransportSE androidHttpTransport = new HttpTransportSE("http://www.byjg.com.br/site/webservice.php/ws/cep");
            //action
            androidHttpTransport.call("urn:CEPServiceAction", envelope);
            Vector result = (Vector) envelope.getResponse();

            result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
}
