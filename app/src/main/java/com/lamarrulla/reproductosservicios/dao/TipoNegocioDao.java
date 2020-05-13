package com.lamarrulla.reproductosservicios.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lamarrulla.reproductosservicios.entity.TipoNegocio;

import java.util.List;

@Dao
public interface TipoNegocioDao {
    @Insert
    void insert(TipoNegocio tipoNegocio);
    @Update
    void update(TipoNegocio tipoNegocio);
    @Delete
    void delete(TipoNegocio tipoNegocio);

    @Query("DELETE FROM TIPONEGOCIO_TABLE")
    void deleteAllTipoNegocio();

    @Query("SELECT * FROM tipoNegocio_table ORDER BY tipoNegocio ASC")
    LiveData<List<TipoNegocio>> getAllTipoNegocios();
}
