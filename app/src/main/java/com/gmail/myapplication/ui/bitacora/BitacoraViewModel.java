package com.gmail.myapplication.ui.bitacora;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.gmail.myapplication.repository.database.entity.Bitacora;
import com.gmail.myapplication.repository.BitacoraRepository;

import java.util.List;

public class BitacoraViewModel extends AndroidViewModel {

    LiveData<List<Bitacora>> mBitacoras;
    private BitacoraRepository mBitacoraRepository;

    public BitacoraViewModel(@NonNull Application application) {
        super(application);
        mBitacoraRepository = new BitacoraRepository(application);
        mBitacoras = mBitacoraRepository.findAll();
    }

    public LiveData<List<Bitacora>> findAll(){
        return mBitacoras;
    }

    public void insert(Bitacora bitacora){
        mBitacoraRepository.insert(bitacora);
    }
}
