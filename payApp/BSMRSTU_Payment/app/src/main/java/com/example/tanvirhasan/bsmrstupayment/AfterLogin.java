package com.example.tanvirhasan.bsmrstupayment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AfterLogin extends AppCompatActivity {

    Button logout;
    String id,name;
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
        id=sharedPreferences.getString("id",null);
        String s="Welcome to payment world  "+id +"\n";  // here null is default value
        tv.setText(s);
        setTitle("Welcome " + sharedPreferences.getString("name",null)); //set activity title
    }

    public void seeProfile(View v){
        Intent i=new Intent(AfterLogin.this,Profile.class);
        startActivity(i);
    }

    public void seePaymentMethod(View v){


        Person person=new Person();
        person.set_method("get_pay_code");
        person.set_toUrl(getResources().getString(R.string.pay_codeUrl).toString().trim());
        BackGroundTask b=new BackGroundTask(this);
        b.execute(person);
    }

    public void seePaymentStatus(View v){
        Person person=new Person();
        person.set_method("payment_status");
        person.set_id(id);
        person.set_toUrl(getResources().getString(R.string.payment_statusUrl).toString().trim());
        BackGroundTask b=new BackGroundTask(this);
        b.execute(person);

    }

    public void logoutTry(View v){
        CommonFunctions.doLogout(this);
        // finish();
    }
}
