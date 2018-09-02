package com.gmail.myapplication.repository.network;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;

import com.gmail.myapplication.repository.network.model.Response;
import com.gmail.myapplication.repository.network.model.ResponsePokemon;

import java.lang.reflect.Type;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class HelperRequest {
    //Se pueden crear los helpers de diversos tipos ( PRUEBAS , PRODUCCION , BETA )

    /*
    * HACER CONSULTAS A VARIOS VARIOS SERVIDORES DE MANERA MAS ORDENADA
    * EL HELPER NOS ESTARA PROPORCIONANDO MEDIANTE EL TYPE A QUE SERVIDORES HACER LA CONSULTA
    * EL APIRESOLVER CAMBIARA LA URL CADA VEZ QUE SE NECESITE
    * EL APIADAPTER EN CASO DE QUE LE LLEGE UNA URL DIFERENTE CAMBIARA LA URL BASE A DONDE SE ESTA
    *   CONECTADO
    * */

    public static final String TAG = HelperRequest.class.getSimpleName();

    public enum TYPE{
        A, //servidor 1
        B, //servidor 2
        P, //servidor 3
        SW
    }

    @SuppressLint("CheckResult")
    public static Single<Response> getResponse ( TYPE type ){
        switch (type){
            case A:
                return ApiResolver.AApiService().getResponse()
                        .subscribeOn(Schedulers.newThread());
            case B:
                return ApiResolver.BApiService().getResponse()
                        .subscribeOn(Schedulers.newThread());
            case P:
                return ApiResolver.PApiService().getResponse()
                        .subscribeOn(Schedulers.newThread());
        }
        return null;
    }

    public static Single<ResponsePokemon> getPokemon( int id ){
        return ApiResolver.PKApiService().getPokemon(id)
                .subscribeOn(Schedulers.newThread());
    }
}