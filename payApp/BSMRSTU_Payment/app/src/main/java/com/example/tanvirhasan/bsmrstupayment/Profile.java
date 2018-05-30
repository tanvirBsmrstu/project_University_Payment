package com.example.tanvirhasan.bsmrstupayment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profile);

        tv= (TextView) findViewById(R.id.profileTv);
        SharedPreferences sharedPreferences= getSharedPreferences("userInfo",MODE_PRIVATE);
        String s=sharedPreferences.getString("name",null)+" \n"+
                sharedPreferences.getString("id",null)+"\n"+sharedPreferences.getString("mob",null)+"\n"+
                sharedPreferences.getString("email",null)+" \n";
        tv.setText(s);

    }

}
