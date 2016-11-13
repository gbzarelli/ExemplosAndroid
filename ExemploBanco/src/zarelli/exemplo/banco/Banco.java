/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarelli.exemplo.banco;

import android.database.sqlite.SQLiteDatabase;

/**
 *
 * @author guilherme
 */
public class Banco {

    private BancoManager bancoManager;
    private SQLiteDatabase sqld;

    public Banco(BancoManager bancoManager) {
        this.bancoManager = bancoManager;
    }

    public void open() {
        sqld = bancoManager.getWritableDatabase();
    }

    public SQLiteDatabase get() {
        if (sqld != null && sqld.isOpen()) {
            return sqld;
        }
        return null;
    }

    public void close() {
        bancoManager.close();
    }
}
