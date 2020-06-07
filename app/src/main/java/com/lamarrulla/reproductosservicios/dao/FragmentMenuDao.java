package com.lamarrulla.reproductosservicios.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lamarrulla.reproductosservicios.entity.FragmentMenu;

import java.util.List;

@Dao
public interface FragmentMenuDao {
    @Insert
    void insert(FragmentMenu fragmentMenu);
    @Update
    void update(FragmentMenu fragmentMenu);
    @Delete
    void delete(FragmentMenu fragmentMenu);

    @Query("DELETE FROM fragmentMenu_table")
    void deleteAllFragmentMenu();

    @Query("SELECT * FROM fragmentMenu_table")
    LiveData<List<FragmentMenu>> getAllFragmentMenu();
}
