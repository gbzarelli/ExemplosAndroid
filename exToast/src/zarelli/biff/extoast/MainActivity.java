package zarelli.biff.extoast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String MENSAGEM = "Exemplo Toast DevMedia! - GbZarelli";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.bt_ex1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                ex_toast_1(MENSAGEM);
            }
        });
        findViewById(R.id.bt_ex2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                ex_toast_2(MENSAGEM);
            }
        });
        findViewById(R.id.bt_ex3).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                ex_toast_3(MENSAGEM);
            }
        });

    }

    private void ex_toast_1(String texto) {
        Toast toast = Toast.makeText(this, texto, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    private void ex_toast_2(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    private void ex_toast_3(String texto) {
        //recurso para inflar o layout
        LayoutInflater layoutInflater = getLayoutInflater();

        //nosso layout do toast
        int layout = R.layout.toast;
        //inflamos nosso layout para ter acesso aos seus componentes
        View view = layoutInflater.inflate(layout, null);
        
        //pegamos um textView do layout inflado e definimos um texto a ele
        TextView tv_texto = (TextView) view.findViewById(R.id.texto);
        tv_texto.setText(texto);

        //criamos o toast e definimos a view inflada como a view do toast
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
}
