package zarelli.biff;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /* Servico servico = new Servico();
         System.out.println(servico.Hello("teste"));
         System.out.println(servico.Hello2("teste2--555"));
         */
        ServicoCEP servicoCEP = new ServicoCEP();
        servicoCEP.consultaCEP();
    }
}
