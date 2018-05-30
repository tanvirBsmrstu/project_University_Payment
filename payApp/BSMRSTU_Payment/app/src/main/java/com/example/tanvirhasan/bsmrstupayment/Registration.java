package com.example.tanvirhasan.bsmrstupayment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText sName,sPass,sEmail,sId,sPhon;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_detail);

        sName=(EditText) findViewById(R.id.sName);
        sEmail=(EditText) findViewById(R.id.sEmail);
        sPhon=(EditText) findViewById(R.id.phoneNumber);
        sId=(EditText) findViewById(R.id.sId);
        sPass=(EditText) findViewById(R.id.rpass);
        submit=(Button) findViewById(R.id.regSubmit);




    }

    public void submitInfo(View v){
        Person personInfo= new Person();
        personInfo.set_name(sName.getText().toString().trim());  //trim() for remove space from begining or end

        personInfo.set_id(sId.getText().toString().trim());
        personInfo.set_mob(sPhon.getText().toString().trim());
        personInfo.set_email(sEmail.getText().toString().trim());
        personInfo.set_pass(sPass.getText().toString().trim());
        personInfo.set_method("registrationKey");
        personInfo.set_toUrl(getResources().getString(R.string.registrationUrl).toString().trim());
        //check if any feild is empty
        if(personInfo.get_name().isEmpty()||personInfo.get_pass().isEmpty()||personInfo.get_mob().isEmpty()||personInfo.get_email().isEmpty()||personInfo.get_id().isEmpty()){
            Toast.makeText(this,"Fill all the feild",Toast.LENGTH_LONG).show();

        }
        else{
            BackGroundTask b =new BackGroundTask(this);
            b.execute(personInfo);

        }

    }

}
