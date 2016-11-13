package zarelli.biff;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import java.util.ArrayList;
import zarelli.biff.atividade.sherlock.fragment.Fragmento;
import zarelli.biff.atividade.sherlock.fragment.FragmentosAdapter;
import zarelli.biff.telas.Tela_dois;
import zarelli.biff.telas.Tela_um;

/**
 *
 * @author guilherme
 */
public class Base extends SlidingFragmentActivity {

    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        setBehindContentView(getLayoutInflater().inflate(R.layout.menu_lateral, null));

        setSlidingActionBarEnabled(true);
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        getSlidingMenu().setShadowWidthRes(R.dimen.shadow_width);
        //getSlidingMenu().setShadowDrawable(R.drawable.shadow);
        getSlidingMenu().setBehindOffsetRes(R.dimen.slidingmenu_offset);
        getSlidingMenu().setFadeDegree(0.35f);


        ArrayList<Fragmento> listaFragmentos = new ArrayList<Fragmento>();
        listaFragmentos.add(new Tela_um());
        listaFragmentos.add(new Tela_dois());

        FragmentosAdapter adapter = new FragmentosAdapter(getSupportFragmentManager(), listaFragmentos);

        viewPager = (ViewPager) findViewById(R.id.id_view_pager);
        viewPager.setAdapter(adapter);
        viewPager.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });

        findViewById(R.menu_lateral.item_tela_2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                mudarConteudo(1);

            }
        });
        findViewById(R.menu_lateral.item_tela_1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                mudarConteudo(0);
            }
        });
    }

    private void mudarConteudo(int index) {
        viewPager.setCurrentItem(index);
        getSlidingMenu().showContent();
    }
}
