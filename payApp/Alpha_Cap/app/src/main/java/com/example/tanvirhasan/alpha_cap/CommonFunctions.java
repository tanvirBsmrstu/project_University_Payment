package com.example.tanvirhasan.alpha_cap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by tanvir hasan on 2/23/2016.
 */
public class CommonFunctions {

    public static boolean isLoggedIn(Context context){
        SharedPreferences shp=context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
        if(shp.getString("id",null)!=null && shp.getString("pass",null)!=null){
            return true;
        }
        return false;
    }

    public static boolean isNetConnected(Context context){

        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static void doLogout(Context context){
        SharedPreferences shp=context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
        SharedPreferences.Editor edt=shp.edit();
        edt.clear();
        edt.commit();
        Intent i= new Intent(context,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
