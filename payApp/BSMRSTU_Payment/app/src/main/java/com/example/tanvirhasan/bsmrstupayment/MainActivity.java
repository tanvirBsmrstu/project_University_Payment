package com.example.tanvirhasan.bsmrstupayment;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.NetPermission;

public class MainActivity extends AppCompatActivity {

    Button signUp,login;
    EditText stuId,stuPass;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        tv= (TextView)findViewById(R.id.textView03);
        signUp= (Button) findViewById(R.id.registrationButton);
        login= (Button) findViewById(R.id.loginButton);
        stuId=(EditText)findViewById(R.id.STU_id);
        stuPass=(EditText)findViewById(R.id.STU_pass);

        //check internet connection
        if(CommonFunctions.isNetConnected(this)) {
            tv.setVisibility(View.INVISIBLE);
        }
        else {
            signUp.setEnabled(false);
            login.setEnabled(false);
            tv.setVisibility(View.VISIBLE);
        }

        ///check is logged in
        if(CommonFunctions.isLoggedIn(this)){
            Intent i= new Intent(this,AfterLogin.class);
            i.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity(i);
            finish();
        }

        //otherwise check for login
    }



    public void registration(View v){  //called on  signUp button clicked

        Intent i= new Intent(this.getApplicationContext(),Registration.class);
        startActivity(i);
    }
    public void loginTry(View v){ //called on login button clicked

        Person personInfo = new Person();
        personInfo.set_id(stuId.getText().toString().trim());   //trim for removeing space from begining or end
        personInfo.set_pass(stuPass.getText().toString().trim());

        if(personInfo.get_id().isEmpty() || personInfo.get_pass().isEmpty()){
            Toast.makeText(this,"Fill all field ",Toast.LENGTH_LONG).show();
            return;
        }
        personInfo.set_method("loginKey");
        personInfo.set_toUrl(getResources().getString(R.string.loginUrl).toString().trim());

        BackGroundTask b=new BackGroundTask(this);
        b.execute(personInfo);
        stuId.setText("");
        stuPass.setText("");

    }
}
