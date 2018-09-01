package com.gmail.myapplication;

import android.app.Application;
import android.content.Context;

public class Vapp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
    }

    public static Context getContext(){
        return context;
    }
}
