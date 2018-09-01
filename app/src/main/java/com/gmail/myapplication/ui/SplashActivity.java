package com.gmail.myapplication.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gmail.myapplication.R;
import com.gmail.myapplication.ui.bitacora.BitacoraActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SplashActivity extends AppCompatActivity {

    public static final String TAG = SplashActivity.class.getSimpleName();

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Observable.timer( 2 , TimeUnit.SECONDS , Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe( (Long aLong) -> {
            startActivity( new Intent( this , BitacoraActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NEW_TASK));
        }, ( err ) -> Log.e(TAG , err.getMessage()) );


    }
}
