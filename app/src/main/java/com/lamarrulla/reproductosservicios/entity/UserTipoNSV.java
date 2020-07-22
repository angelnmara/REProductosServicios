package com.lamarrulla.reproductosservicios.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(tableName = "userTipoNSV_table")
public class UserTipoNSV {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
    private String tipoNegocio;
    private String tipoServicio;
    private String tipoVenta;

    public UserTipoNSV(
            String email,
            String tipoNegocio,
            String tipoServicio,
            String tipoVenta
    ){
        this.email = email;
        this.tipoNegocio = tipoNegocio;
        this.tipoServicio = tipoServicio;
        this.tipoVenta = tipoVenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoNegocio() {
        return tipoNegocio;
    }

    public void setTipoNegocio(String tipoNegocio) {
        this.tipoNegocio = tipoNegocio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }
}
