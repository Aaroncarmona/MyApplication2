package com.gmail.myapplication.ui.bitacora;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.gmail.myapplication.repository.database.entity.Bitacora;
import com.gmail.myapplication.repository.database.entity.Pokemon;
import com.gmail.myapplication.repository.repository.BitacoraRepository;
import com.gmail.myapplication.repository.repository.PokemonRepository;

import java.util.List;

public class BitacoraViewModel extends AndroidViewModel {

    LiveData<List<Bitacora>> mBitacoras;
    LiveData<List<Pokemon>> mPokemon;

    private BitacoraRepository mBitacoraRepository;
    private PokemonRepository mPokemonRepository;

    public BitacoraViewModel(@NonNull Application application) {
        super(application);
        mBitacoraRepository = new BitacoraRepository(application);
        mPokemonRepository = new PokemonRepository(application);

        mBitacoras = mBitacoraRepository.findAll();
        mPokemon = mPokemonRepository.findAll();
    }

    public void findAll(){
        this.mBitacoras = mBitacoraRepository.findAll();
    }

    public void findByIdPk(int id ){
        this.mPokemon = mPokemonRepository.findById(id);
    }

    public void insert(Bitacora bitacora){
        mBitacoraRepository.insert(bitacora);
    }

    public void  findAllPk( ){
        this.mPokemon = mPokemonRepository.findAll();
    }
}
