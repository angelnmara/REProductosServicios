package com.lamarrulla.reproductosservicios.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
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

}
