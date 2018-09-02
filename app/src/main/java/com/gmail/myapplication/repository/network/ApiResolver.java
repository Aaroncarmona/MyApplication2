package com.gmail.myapplication.repository.network;

import com.gmail.myapplication.constant.ApiConstant;

public class ApiResolver {

    public static BTApiService AApiService(){
        //biene del servidor a
            return BTApiAdapter.getApiService(ApiConstant.B_URL_BASE);
    }

    public static BTApiService BApiService(){
        //biene del servidor b
        return BTApiAdapter.getApiService(ApiConstant.B_URL_BASE);
    }

    public static BTApiService PApiService(){
        //biene del servidor de pruebas
        return BTApiAdapter.getApiService(ApiConstant.B_URL_BASE);
    }

    public static BTApiService PKApiService(){
        return BTApiAdapter.getApiService(ApiConstant.PK_URL_BASE);
    }
}