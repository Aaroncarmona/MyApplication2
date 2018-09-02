package com.gmail.myapplication.repository.network;

import com.gmail.myapplication.constant.ApiConstant;
import com.gmail.myapplication.repository.network.model.ResponsePokemon;
import com.gmail.myapplication.repository.network.model.Response;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BTApiService {
    //tener cuidado ya que
    @GET("")
    Single<Response> getResponse();


    @GET(ApiConstant.PK_NAME_POKEMON)
    Single<ResponsePokemon> getPokemon(@Path(value = "id") int id);

}
