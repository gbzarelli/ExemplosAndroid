/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.zarelli;

import java.io.Serializable;

/**
 *
 * @author guilherme
 */
public class ObTeste implements Serializable {

    public static final long serialVersionUID = 20120523113610L;
    private String teste;

    public ObTeste() {
        this("");
    }

    public ObTeste(String teste) {
        this.teste = teste;
    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }

    @Override
    public String toString() {
        return "ObTeste{" + "teste=" + teste + '}';
    }
}
