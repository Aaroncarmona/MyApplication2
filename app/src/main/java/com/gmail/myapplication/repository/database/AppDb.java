package com.gmail.myapplication.repository.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.gmail.myapplication.constant.ConstantDb;
import com.gmail.myapplication.repository.database.dao.IBitacoraDao;
import com.gmail.myapplication.repository.database.entity.Bitacora;

import java.util.Date;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@Database(
    entities = {
        Bitacora.class
    }, version = 1
)
public abstract class AppDb extends RoomDatabase {
    public abstract IBitacoraDao bitacoraDao();
    private static AppDb INSTANCE;

    public static AppDb getDatabase(Context context){
        if ( INSTANCE == null ){
            synchronized (AppDb.class){
                if ( INSTANCE == null ){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDb.class , ConstantDb.NAME_TABLE)
                        .addCallback(sRoomDatabaseCallback)
                        .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Single.just(1)
                .subscribeOn(Schedulers.newThread())
                .subscribe( success  -> {
                INSTANCE.bitacoraDao().deleteAll();
                Bitacora b = new Bitacora();
                b.setDate(new Date().toString());
                b.setNota("habia un buen de trafico");
                b.setTipo(0);
                INSTANCE.bitacoraDao().insert(b);
                });
        }
    };
}
