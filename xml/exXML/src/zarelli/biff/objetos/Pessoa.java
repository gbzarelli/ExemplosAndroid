/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarelli.biff.objetos;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author guilherme
 */
@Root(name = "pessoa")
public class Pessoa {

    @Element(name = "nome")
    private String nome;
    @Element(name = "sexo")
    private String sexo;

    public Pessoa() {
    }

    public Pessoa(String nome, String sexo) {
        this.nome = nome;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", sexo=" + sexo + '}';
    }
}
