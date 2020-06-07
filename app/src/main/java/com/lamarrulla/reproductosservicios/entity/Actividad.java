package com.lamarrulla.reproductosservicios.entity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "actividad_table")
public class Actividad {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String actividadTipo;
    private boolean actividadStatus;

    public Actividad(String actividadTipo, boolean actividadStatus) {
        this.actividadTipo = actividadTipo;
        this.actividadStatus = actividadStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getActividadTipo() {
        return actividadTipo;
    }

    public boolean isActividadStatus() {
        return actividadStatus;
    }

}
