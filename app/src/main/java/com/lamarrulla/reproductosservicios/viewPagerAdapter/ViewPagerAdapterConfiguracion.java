package com.lamarrulla.reproductosservicios.viewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.lamarrulla.reproductosservicios.entity.FragmentMenu;

import java.util.List;

public class ViewPagerAdapterConfiguracion extends FragmentStatePagerAdapter {

    public static final int NUM_PAGES = 2;
    private static List<FragmentMenu> fragmentmenuStatic;

    public ViewPagerAdapterConfiguracion(FragmentManager fm, List<FragmentMenu> fragmentMenus){
        super(fm);
        fragmentmenuStatic = fragmentMenus;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) Class.forName("com.lamarrulla.reproductosservicios.fragments." + fragmentmenuStatic.get(position).getFragmentName()).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentmenuStatic.get(position).getMenuName();
    }
}
