/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarelli.biff.objetos;

import java.io.File;
import java.util.Date;

/**
 *
 * @author guilherme
 */
public class ObVideo {

    private String descricao;
    private File dir;
    private Date data;

    public ObVideo() {
    }

    public ObVideo(String descricao, File dir, Date data) {
        this.descricao = descricao;
        this.dir = dir;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public File getDir() {
        return dir;
    }

    public void setDir(File dir) {
        this.dir = dir;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ObVideo{" + "descricao=" + descricao + ", dir=" + dir + ", data=" + data + '}';
    }
}
