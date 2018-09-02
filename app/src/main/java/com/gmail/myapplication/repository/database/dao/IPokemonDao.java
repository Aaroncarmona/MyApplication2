package com.gmail.myapplication.repository.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gmail.myapplication.repository.database.entity.Pokemon;

import java.util.List;

@Dao
public interface IPokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Pokemon pokemon);

    @Query("DELETE FROM pokemones")
    void deleteAll();

    @Query("DELETE FROM pokemones WHERE id = :id")
    void deleteById(int id);

    @Delete
    void delete(Pokemon pokemon);

    @Query("SELECT * FROM pokemones")
    LiveData<List<Pokemon>> findAll();

    @Query("SELECT * FROM pokemones WHERE id = :id")
    LiveData<List<Pokemon>> findById(int id);

}
