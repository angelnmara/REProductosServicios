package com.lamarrulla.reproductosservicios.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fragmentMenu_table")
public class FragmentMenu {

    public FragmentMenu(int idMenu, int idFragment, String menuName, String fragmentName, boolean fragmentMenuStatus) {
        this.idMenu = idMenu;
        this.idFragment = idFragment;
        this.menuName = menuName;
        this.fragmentName = fragmentName;
        this.fragmentMenuStatus = fragmentMenuStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public int getIdFragment() {
        return idFragment;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public boolean isFragmentMenuStatus() {
        return fragmentMenuStatus;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int idMenu;
    private int idFragment;
    private String menuName;
    private String fragmentName;
    private boolean fragmentMenuStatus;

}
