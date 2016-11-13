package zarelli.biff;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.widget.TextView;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String html = "<p><div>Corre&ccedil;&otilde;es Monitor 1.0.0.43:</div>";
        ((TextView) findViewById(R.id.html)).setText(html);

        String texto = Html.fromHtml(html).toString();
        ((TextView) findViewById(R.id.string)).setText(texto);

        String texto2 = Html.toHtml(new SpannableString(texto));
    }
}
