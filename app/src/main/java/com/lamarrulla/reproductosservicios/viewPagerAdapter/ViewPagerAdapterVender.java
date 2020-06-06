package com.lamarrulla.reproductosservicios.viewPagerAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.lamarrulla.reproductosservicios.fragments.AgregarArticuloFragment;
import com.lamarrulla.reproductosservicios.fragments.ArticuloFragment;

public class ViewPagerAdapterVender extends FragmentStatePagerAdapter {

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;


    public ViewPagerAdapterVender(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new ArticuloFragment(); //ChildFragment1 at position 0
            case 1:
                return new AgregarArticuloFragment(); //ChildFragment2 at position 1
        }
        return null; //does not happen
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Object" + (position + 1);
    }
}