package com.lamarrulla.reproductosservicios.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserTipoNSV {
    @Insert
    void insert(UserTipoNSV userTipoNSV);

    @Update
    void update(UserTipoNSV userTipoNSV);

    @Delete
    void delete(UserTipoNSV userTipoNSV);

    @Query("DELETE FROM userTipoNSV_table")
    void deleteAllUsers();

    @Query("SELECT * FROM userTipoNSV_table")
    LiveData<List<UserTipoNSV>> getAllUsers();
}
