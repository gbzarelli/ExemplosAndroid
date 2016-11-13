package zarelli.biff;

import android.app.Activity;
import android.os.Bundle;
import java.io.ByteArrayOutputStream;
import org.simpleframework.xml.core.Persister;
import zarelli.biff.objetos.Grupo;
import zarelli.biff.objetos.Pessoa;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            teste2();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void teste1() {
        try {

            Persister persister = new Persister();
            Pessoa pessoa = persister.read(Pessoa.class, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><pessoa><nome>guilherme</nome><sexo>m</sexo></pessoa>");

            System.out.println(pessoa.toString());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            pessoa.setSexo("masculino");
            persister.write(pessoa, baos, "UTF-8");

            System.out.println("---> \n" + new String(baos.toByteArray()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void teste2() throws Exception {

        Pessoa pessoa1 = new Pessoa("p1", "m");
        Pessoa pessoa2 = new Pessoa("p2", "f");
        Grupo grupo = new Grupo();
        grupo.inserir(pessoa1);
        grupo.inserir(pessoa2);

        Persister persister = new Persister();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        persister.write(grupo, baos, "UTF-8");

        System.out.println("---> \n" + new String(baos.toByteArray()));

    }
}
