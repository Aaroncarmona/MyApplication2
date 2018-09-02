package com.gmail.myapplication.repository.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.gmail.myapplication.constant.ConstantDb;

@Entity(tableName = ConstantDb.NAME_TABLE_BITACORA)
public class Bitacora {

    public Bitacora(String date, String nota, int tipo) {
        this.date = date;
        this.nota = nota;
        this.tipo = tipo;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String nota;
    private int tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
