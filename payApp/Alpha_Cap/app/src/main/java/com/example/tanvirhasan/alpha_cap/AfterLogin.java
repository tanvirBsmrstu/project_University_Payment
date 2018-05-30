package com.example.tanvirhasan.alpha_cap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AfterLogin extends AppCompatActivity{

    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_after_login);

        if(!CommonFunctions.isNetConnected(this)){
            Toast.makeText(this,"Sorry connection Lost ",Toast.LENGTH_LONG).show();
            CommonFunctions.doLogout(this);
        }

        logout= (Button) findViewById(R.id.logout);

        TextView tv= (TextView) findViewById(R.id.welComeTextView);
        SharedPreferences sharedPreferences= getSharedPreferences("userInfo",MODE_PRIVATE);
        String s="Welcome to Alpha Cap  \n"+ sharedPreferences.getString("name",null)+" \n"+"Your id is  "+
                sharedPreferences.getString("id",null)+" \n"+"Your mobile "+sharedPreferences.getString("mob",null)+" \n"+
                "Your email   "+sharedPreferences.getString("email",null)+" \n";  // here null is default value
        tv.setText(s);
    }

    public void logoutTry(View v){
       CommonFunctions.doLogout(this);
       // finish();
    }

}
