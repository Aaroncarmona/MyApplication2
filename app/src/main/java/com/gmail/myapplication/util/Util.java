package com.gmail.myapplication.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Toast;

import com.gmail.myapplication.Vapp;

import java.util.Random;

public class Util {
    public static void toast( String text){
        Toast.makeText(Vapp.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static int random( int min , int max ) {
        Random rn = new Random();
        return rn.nextInt(max - min + 1) + min;
    }

    public static boolean isInternet( ){

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
            (ConnectivityManager) Vapp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
            connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
            connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
            connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;
        } else if (
            connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
            connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            return false;
        }
        return false;
    }
}
