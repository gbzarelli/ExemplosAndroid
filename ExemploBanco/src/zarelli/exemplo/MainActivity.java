package zarelli.exemplo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import zarelli.exemplo.banco.Banco;
import zarelli.exemplo.banco.BancoUsuarios;

public class MainActivity extends Activity {

    public static final String TAG_LOG = "ExemploBanco";
    private Banco banco;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inicia e abre banco
        banco = new Banco(new BancoUsuarios(this));
        banco.open();
        
        //inicia o exemplo (insert,select,update,delete)
//        Log.d(TAG_LOG, "\nINSERT");
//        insert("gbzarelli");//insiro o usuario
//        insert("registro_" + System.currentTimeMillis());//insiro o usuario
        select();//busco

//        Log.d(TAG_LOG, "\nUPDATE");
//        String update = "gbzarelli_" + System.currentTimeMillis();
//        update("gbzarelli", update);//atualizo o nome
//        select();//busco
//
//        Log.d(TAG_LOG, "\nDELETE");
//        delete(update);//deleto o registro
//        select();//busco

        banco.close();//fecha o banco
    }
    
    private void insert(String usuario) {
        //inserir dados
        ContentValues cv = new ContentValues();
        cv.put("usuario", usuario);
        cv.put("senha", "147147");
        cv.put("nome_completo", "Guilherme Biff Zarelli");
        long l = banco.get().insert("usuarios_tbl", null, cv);
        Log.i(TAG_LOG, "id insert: " + l);
    }

    private void select() {
        //O cursor no android é similar ao ResultSet do Java
        Cursor cursor = banco.get().rawQuery("select * from usuarios_tbl limit ?", new String[]{"5"});
        while (cursor.moveToNext()) {
            Log.i(TAG_LOG, "id: " + cursor.getInt(cursor.getColumnIndex("id_usuarios")));
            Log.i(TAG_LOG, "usuario: " + cursor.getString(cursor.getColumnIndex("usuario")));
            Log.i(TAG_LOG, "senha: " + cursor.getString(cursor.getColumnIndex("senha")));
            Log.i(TAG_LOG, "nomeCompleto: " + cursor.getString(cursor.getColumnIndex("nome_completo")));
            Log.i(TAG_LOG, "-------");
        }
        //Nunca esqueça de feixar o cursor.
        cursor.close();
    }

    private void update(String usuario, String novo) {
        ContentValues cv = new ContentValues();
        cv.put("usuario", novo);
        banco.get().update("usuarios_tbl", cv, "usuario==?", new String[]{usuario});
    }

    private void delete(String usuario) {
        banco.get().delete("usuarios_tbl", "usuario==?", new String[]{usuario});
    }
}
