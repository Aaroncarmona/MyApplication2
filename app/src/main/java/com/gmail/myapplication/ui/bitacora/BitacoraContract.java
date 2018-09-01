package com.gmail.myapplication.ui.bitacora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class BitacoraContract {
    protected interface Presenter{
        void back();
        void clickEntrada(AppCompatActivity appCompatActivity,boolean isLong);
        void clickSalida(AppCompatActivity appCompatActivity, boolean isLong);
        void onActivityResult(int requestCode , int resultCode , Intent data);
        void backRealize();

    }

    protected interface View{
        void back();
        void showMessage(String text);
    }
}
