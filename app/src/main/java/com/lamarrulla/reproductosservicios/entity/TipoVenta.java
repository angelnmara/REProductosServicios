package com.lamarrulla.reproductosservicios.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tipoVenta_table")
public class TipoVenta {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tipoVenta;
    private Boolean tipoVentaStatus;

    public TipoVenta(String tipoVenta, Boolean tipoVentaStatus) {
        this.tipoVenta = tipoVenta;
        this.tipoVentaStatus = tipoVentaStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public Boolean getTipoVentaStatus() {
        return tipoVentaStatus;
    }
}
