/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarelli.biff.objetos;

import java.util.ArrayList;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author guilherme
 */
@Root(name = "grupo")
public class Grupo {

    @ElementList
    private ArrayList<Pessoa> pessoas;

    public Grupo() {
        pessoas = new ArrayList<Pessoa>();
    }

    public void inserir(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public String toString() {
        return "Grupo{" + "pessoas=" + pessoas + '}';
    }
}
