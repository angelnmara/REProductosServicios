package com.lamarrulla.reproductosservicios.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lamarrulla.reproductosservicios.entity.TipoServicio;

import java.util.List;

@Dao
public interface TipoServicioDao {
    @Insert
    void insert(TipoServicio tipoServicio);
    @Update
    void update(TipoServicio tipoServicio);
    @Delete
    void delete(TipoServicio tipoServicio);
    @Query("DELETE FROM tipoServicio_tabla")
    void deleteAllTipoServicio();
    @Query("SELECT * FROM tipoServicio_tabla ORDER BY tipoServicio ASC")
    LiveData<List<TipoServicio>> selectAllTiposServicios();
}
