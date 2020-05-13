package com.lamarrulla.reproductosservicios.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tipoServicio_tabla")
public class TipoServicio {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tipoServicio;
    private Boolean tipoServicioStatus;

    public TipoServicio(String tipoServicio, Boolean tipoServicioStatus) {
        this.tipoServicio = tipoServicio;
        this.tipoServicioStatus = tipoServicioStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public Boolean getTipoServicioStatus() {
        return tipoServicioStatus;
    }
}
