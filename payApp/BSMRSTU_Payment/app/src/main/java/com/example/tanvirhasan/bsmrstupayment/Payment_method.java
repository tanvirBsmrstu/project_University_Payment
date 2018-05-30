package com.example.tanvirhasan.bsmrstupayment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Payment_method extends AppCompatActivity {
    TextView tv;
    EditText et;
    Button bt;
    String id;
    ListView lv;
    Test paycodeAdaptar;
    List<PayCodeItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_payment_method);
        list=new ArrayList<PayCodeItem>();

        tv=(TextView)findViewById(R.id.payDetailTv);
        et=(EditText)findViewById(R.id.getTnxEt);
        bt=(Button) findViewById(R.id.submitTnx);
        lv=(ListView) findViewById(R.id.paycodeLv);

        list.add(new PayCodeItem("pay_code","dept","year","semister","Fee"));

        tv.setText(R.string.payment_details);
        Intent ii= getIntent();
        String jsonString=ii.getStringExtra("jsonString");
        json_par_pay_code(jsonString);
        ListAdapter listAdapter= new CustomAdaptar(getBaseContext(),list );
       lv.setAdapter(listAdapter);


    }

    void json_par_pay_code(String jsonString){

        //Toast.makeText(this, jsonString, Toast.LENGTH_SHORT).show();
        try {

            JSONObject jsonObjec= new JSONObject(jsonString),jsonObject;

            JSONArray jsonArray= jsonObjec.getJSONArray("paycode_detail");
            for(int i=0;i<jsonArray.length();i++){

                PayCodeItem pi=new PayCodeItem();
                jsonObject= jsonArray.getJSONObject(i);

                // Toast.makeText(this,i,Toast.LENGTH_SHORT).show();

                String dept=jsonObject.getString("dept");
                String amount=jsonObject.getString("fee");
                String year=jsonObject.getString("year");
                String semister=jsonObject.getString("sem");
                String pay_code=jsonObject.getString("pay_code");
                PayCodeItem payCodeItem= new PayCodeItem(pay_code, dept, year, semister, amount);
                list.add(payCodeItem);
                // paycodeAdaptar.add(sti);


            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this,"JSONException",Toast.LENGTH_SHORT).show();


        }
    }








    public void submitTnxId(View v){
        String s= et.getText().toString().trim();
        Person person=new Person();
        SharedPreferences sharedPreferences= getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        person.set_id(sharedPreferences.getString("id",null));
        person.set_toUrl(getResources().getString(R.string.submitTnxNoUrl).toString().trim());
        person.set_method("submitTnx");
        person.set_data(s);

        BackGroundTask b=new BackGroundTask(this);
        b.execute(person);
    }
}
