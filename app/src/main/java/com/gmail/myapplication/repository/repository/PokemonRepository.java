package com.gmail.myapplication.repository.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.gmail.myapplication.repository.database.AppDb;
import com.gmail.myapplication.repository.database.dao.IPokemonDao;
import com.gmail.myapplication.repository.database.entity.Pokemon;
import com.gmail.myapplication.repository.network.HelperRequest;
import com.gmail.myapplication.repository.network.model.ResponsePokemon;
import com.gmail.myapplication.util.Util;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class PokemonRepository {
    public static final String TAG = PokemonRepository.class.getSimpleName();

    public enum SOURCE{
        ONLINE,
        OFFLINE
    }

    private IPokemonDao mPokemonDao;
    private LiveData<List<Pokemon>> mPokemon;

    //private BTApiService apiService;

    public PokemonRepository(Application application){
        AppDb db = AppDb.getDatabase(application);
        this.mPokemonDao = db.pokemonDao();
        this.mPokemon = mPokemonDao.findAll();
        //this.apiService = ApiResolver.PKApiService();
    }

    public LiveData<List<Pokemon>> findAll(){
        this.mPokemon = this.mPokemonDao.findAll();
        return mPokemon;
    }

    @SuppressLint("CheckResult")
    public LiveData<List<Pokemon>> findById(int id ){
        if ( Util.isInternet() ) {
            HelperRequest.getPokemon(id).subscribe(
                res -> {
                    Log.d(TAG , "success");
                    insert(new Pokemon(id , res.getName()));
                    mPokemon = mPokemonDao.findAll();
                }, ( err ) -> {
                    Log.e(TAG , err.getMessage());
                    mPokemon = mPokemonDao.findAll();
                }
            );
        }else {
            Log.d(TAG , "no internet");
            mPokemon = mPokemonDao.findAll();
        }
        return mPokemon;
    }

    @SuppressLint("CheckResult")
    public void insert(Pokemon pokemon){
        Single.just(pokemon)
        .subscribeOn(Schedulers.newThread())
        .subscribe( value -> this.mPokemonDao.insert(value) );
    }

}
