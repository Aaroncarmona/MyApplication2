package com.gmail.myapplication.repository.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gmail.myapplication.constant.ConstantDb;
import com.gmail.myapplication.repository.database.entity.Bitacora;

import java.util.List;

@Dao
public interface IBitacoraDao {

    @Query("SELECT * FROM " + ConstantDb.NAME_TABLE_BITACORA)
    LiveData<List<Bitacora>> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Bitacora llegada);

    @Delete
    void delete(Bitacora bitacora);

    @Query("DELETE FROM bitacoras")
    void deleteAll();

    @Update
    void update(Bitacora llegada);

}
