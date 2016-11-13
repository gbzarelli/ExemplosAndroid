/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarelli.biff;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

/**
 *
 * @author guilherme
 */
public class Resposta extends SoapObject {

    private String nome;

    public Object getProperty(int i) {
        Object retorno = null;
        switch (i) {
            case 0:
                retorno = nome;
                break;
        }
        return retorno;
    }

    public int getPropertyCount() {
        return 1;
    }

    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                nome = (String) o;
                break;
        }
    }

    public void getPropertyInfo(int i, Hashtable hshtbl, PropertyInfo pi) {
        switch (i) {
            case 0:
                pi.type = PropertyInfo.STRING_CLASS;
                pi.name = "nome";
                break;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
