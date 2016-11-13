package zarelli.biff.alerta;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private AlertDialog alerta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.bt_ex1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                exemplo_layout();
            }
        });
        findViewById(R.id.bt_ex2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                exemplo_simples();
            }
        });
        findViewById(R.id.bt_ex3).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                exemplo_lista_single();
            }
        });
        findViewById(R.id.bt_ex4).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                exemplo_lista_multi();
            }
        });
    }

    private void exemplo_layout() {
        //LayoutInflater é utilizado para inflar nosso layout em uma view.
        //-pegamos nossa instancia da classe
        LayoutInflater li = getLayoutInflater();
        
        //inflamos o layout alerta.xml na view
        View view = li.inflate(R.layout.alerta, null);
        //definimos para o botão do layout um clickListener
        view.findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //exibe um Toast informativo.
                Toast.makeText(MainActivity.this, "alerta.dismiss()", Toast.LENGTH_SHORT).show();
                //desfaz o alerta.
                alerta.dismiss();
            }
        });
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Titulo");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }

    private void exemplo_simples() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Titulo");
        //define a mensagem
        builder.setMessage("Qualifique este software");
        //define um botão como positivo
        builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(MainActivity.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(MainActivity.this, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    private void exemplo_lista_single() {
        //Lista de itens
        ArrayList<String> itens = new ArrayList<String>();
        itens.add("Ruim");
        itens.add("Mediano");
        itens.add("Bom");
        itens.add("Ótimo");
        
        //adapter utilizando um layout customizado (TextView)
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item_alerta, itens);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Qualifique este software:");
        //define o diálogo como uma lista, passa o adapter.
        builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(MainActivity.this, "posição selecionada=" + arg1, Toast.LENGTH_SHORT).show();
                alerta.dismiss();
            }
        });

        alerta = builder.create();
        alerta.show();
    }

    private void exemplo_lista_multi() {
        CharSequence[] charSequences = new CharSequence[]{"Filmes", "Dormir","Sair"};
        final boolean[] checados = new boolean[charSequences.length];

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("O que você gosta?");
        builder.setMultiChoiceItems(charSequences, checados, new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(DialogInterface arg0, int arg1, boolean arg2) {
                checados[arg1] = arg2;
            }
        });

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                StringBuilder texto = new StringBuilder("Checados: ");
                for (boolean ch : checados) {
                    texto.append(ch).append("; ");
                }
                Toast.makeText(MainActivity.this, texto.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        alerta = builder.create();
        alerta.show();
    }
}
