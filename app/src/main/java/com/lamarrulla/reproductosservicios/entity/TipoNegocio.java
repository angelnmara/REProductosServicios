package com.lamarrulla.reproductosservicios.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tipoNegocio_table")
public class TipoNegocio {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tipoNegocio;
    private Boolean tipoNegocioStatus;

    public TipoNegocio(String tipoNegocio, Boolean tipoNegocioStatus) {
        this.tipoNegocio = tipoNegocio;
        this.tipoNegocioStatus = tipoNegocioStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTipoNegocio() {
        return tipoNegocio;
    }

    public Boolean getTipoNegocioStatus() {
        return tipoNegocioStatus;
    }
}
