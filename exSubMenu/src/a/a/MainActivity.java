package a.a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.a:
                Toast.makeText(this, "Teste A", Toast.LENGTH_SHORT).show();

                //CHAMANDO OUTRA TELA:
                startActivity(new Intent(this, OutraTela.class));
                //

                break;
            case R.id.b:
                Toast.makeText(this, "Teste B", Toast.LENGTH_SHORT).show();
                break;
            case R.id.c:
                Toast.makeText(this, "Teste C", Toast.LENGTH_SHORT).show();
                break;
            case R.id.c_a:
                Toast.makeText(this, "Teste C - A", Toast.LENGTH_SHORT).show();
                break;
            case R.id.c_b:
                Toast.makeText(this, "Teste C - B", Toast.LENGTH_SHORT).show();
                break;
            case R.id.c_c:
                Toast.makeText(this, "Teste C - C", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item); //To change body of generated methods, choose Tools | Templates.
    }
}
