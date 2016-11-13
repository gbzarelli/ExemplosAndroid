/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biff.zarelli.fragments;

import biff.zarelli.fragments.adapter.Fragmento;
import biff.zarelli.fragments.adapter.FragmentosAdapter;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class AtividadeFragmento extends FragmentActivity implements ActionBar.TabListener {

    private FragmentosAdapter adapter;
    private ViewPager mViewPager;
    private ArrayList<Fragmento> listaFragmentos;
    private int idMain, idViewPager;

    public AtividadeFragmento(int idMain, int idViewPager) {
        this.idMain = idMain;
        this.idViewPager = idViewPager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(idMain);

        if (listaFragmentos == null) {
            throw new InvalidParameterException("Lista de fragmentos não definida");
        }

        adapter = new FragmentosAdapter(getSupportFragmentManager(), listaFragmentos);

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mViewPager = (ViewPager) findViewById(idViewPager);
        mViewPager.setAdapter(adapter);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < adapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                    .setText(adapter.getPageTitle(i))
                    .setTabListener(this));
        }

    }

    public void setListaFragmentos(ArrayList<Fragmento> listaFragmentos) {
        this.listaFragmentos = listaFragmentos;
    }

    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
}
