package com.gmail.myapplication.util;

import android.widget.Toast;

import com.gmail.myapplication.Vapp;

public class Util {
    public static void toast( String text){
        Toast.makeText(Vapp.getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
