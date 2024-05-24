package com.example.application_animation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Check_Internet {
    public  static String getNetwork(Context context){
        String status=null;
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo notework=connectivityManager.getActiveNetworkInfo();
        if(notework!=null){
            status="connected";
            return status;
        }else {
            status="disconnected";
            return status;
        }
    }
}
