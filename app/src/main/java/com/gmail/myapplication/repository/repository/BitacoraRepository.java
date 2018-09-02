package com.gmail.myapplication.repository.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.gmail.myapplication.repository.database.dao.IBitacoraDao;
import com.gmail.myapplication.repository.database.AppDb;
import com.gmail.myapplication.repository.database.entity.Bitacora;
import com.gmail.myapplication.repository.network.HelperRequest;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class BitacoraRepository {
    //AQUI VOY A CONTROLAR LAS LLAMADAS CON LOS WS Y MI PERSISTENCIA LOCAL
    private static final String TAG = BitacoraRepository.class.getSimpleName();

    private IBitacoraDao mBitacoraDao;
    private LiveData<List<Bitacora>> mBitacoras;

    public BitacoraRepository(Application application){
        AppDb db = AppDb.getDatabase(application);
        this.mBitacoraDao = db.bitacoraDao();
        this.mBitacoras = this.mBitacoraDao.findAll();
    }

    public LiveData<List<Bitacora>> findAll(){
        //EN CASO DE CONSULTAR EN EL WS Y NO ENCONTRAR TRAER LO QUE HAY EN LA PERSISTENCIA
        return this.mBitacoras;
    }

    @SuppressLint("CheckResult")
    public void insert(Bitacora bitacora ){
        //AL MOMENTO DE ENVIARLO Y NO PODER GUARDARLO EN LA PERSISTENCIA

        Single.just(bitacora)
        .subscribeOn(Schedulers.newThread())
        .subscribe( value -> this.mBitacoraDao.insert(value) , err -> Log.e(TAG , "no se inserto") );
    }
}
