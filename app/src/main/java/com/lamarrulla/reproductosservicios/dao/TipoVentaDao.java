package com.lamarrulla.reproductosservicios.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lamarrulla.reproductosservicios.entity.TipoVenta;

import java.util.List;

@Dao
public interface TipoVentaDao {
    @Insert
    void insert(TipoVenta tipoVenta);
    @Update
    void update(TipoVenta tipoVenta);
    @Delete
    void delete(TipoVenta tipoVenta);
    @Query("DELETE FROM tipoVenta_table")
    void deleteAllTipoVenta();
    @Query("SELECT * FROM tipoVenta_table")
    LiveData<List<TipoVenta>> getAllTipoVenta();
}
