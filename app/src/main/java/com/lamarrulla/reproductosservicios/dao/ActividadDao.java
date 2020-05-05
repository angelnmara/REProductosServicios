package com.lamarrulla.reproductosservicios.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lamarrulla.reproductosservicios.entity.Actividad;

import java.util.List;

@Dao
public interface ActividadDao {
    @Insert
    void insert(Actividad actividad);
    @Update
    void update(Actividad actividad);
    @Delete
    void delete(Actividad actividad);

    @Query("DELETE FROM actividad_table")
    void deleteAllActividades();

    @Query("SELECT * FROM actividad_table")
    LiveData<List<Actividad>> getAllActividades();
}
