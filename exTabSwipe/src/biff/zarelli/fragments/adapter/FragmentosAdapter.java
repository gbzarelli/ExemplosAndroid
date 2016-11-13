/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biff.zarelli.fragments.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class FragmentosAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragmento> fragments;

    public FragmentosAdapter(FragmentManager fm, ArrayList<Fragmento> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitulo();
    }
}
