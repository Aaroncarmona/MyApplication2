package com.gmail.myapplication.repository.network;

import com.gmail.myapplication.constant.ApiConstant;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BTApiAdapter {
    private static BTApiService INSTANCE;
    private static String currentUrl = "";

    public static BTApiService getApiService( String url ){
        //hacer pruebas si no mata la peticion cuando se envian simultaneas de diferentes
        //seravidores
        if ( INSTANCE == null || !currentUrl.equals(url)){
            currentUrl = url;
            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client( new OkHttpClient.Builder()
                        .connectTimeout(ApiConstant.TIME_OUT , TimeUnit.SECONDS)
                        .readTimeout(ApiConstant.TIME_OUT , TimeUnit.SECONDS)
                        .writeTimeout(ApiConstant.TIME_OUT , TimeUnit.SECONDS)
                        .addInterceptor( new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .build();
            INSTANCE = retrofit.create(BTApiService.class);
        }
        return INSTANCE;
    }
}
