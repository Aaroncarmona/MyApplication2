package com.gmail.myapplication.repository.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.gmail.myapplication.constant.ConstantDb;

@Entity(tableName = ConstantDb.NAME_TABLE_POKEMON)
public class Pokemon {

    public Pokemon(int id , String name ){
        this.id = id;
        this.name = name;
    }

    @PrimaryKey
    public int id;
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
