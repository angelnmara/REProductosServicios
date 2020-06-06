package com.lamarrulla.reproductosservicios.viewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.lamarrulla.reproductosservicios.fragments.DatosPersonalesFragment;
import com.lamarrulla.reproductosservicios.fragments.AgregarArticuloFragment;

public class ViewPagerAdapterConfiguracion extends FragmentStatePagerAdapter {

    public static final int NUM_PAGES = 2;

    public ViewPagerAdapterConfiguracion(FragmentManager fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new DatosPersonalesFragment(); //ChildFragment1 at position 0
            case 1:
                return new AgregarArticuloFragment(); //ChildFragment2 at position 1
        }
        return null; //does not happen
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Object" + (position + 1);
    }
}
