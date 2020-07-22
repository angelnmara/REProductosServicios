package com.lamarrulla.reproductosservicios.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lamarrulla.reproductosservicios.entity.UserTipoNSV;

import java.util.List;

@Dao
public interface UserTipoNSVDao {
    @Insert
    void insert(UserTipoNSV userTipoNSV);

    @Update
    void update(UserTipoNSV userTipoNSV);

    @Delete
    void delete(UserTipoNSV userTipoNSV);

    @Query("DELETE FROM userTipoNSV_table")
    void deleteAllUserTipoNSV();

    @Query("SELECT * FROM userTipoNSV_table")
    LiveData<List<UserTipoNSV>> getAllUserTipoNSV();
}
