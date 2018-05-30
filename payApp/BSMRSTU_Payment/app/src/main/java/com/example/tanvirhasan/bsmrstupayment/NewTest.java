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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewTest extends AppCompatActivity {

    String id;
    ListView lv;
    Test paycodeAdaptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_lay);


        Intent ii= getIntent();
        String jsonString=ii.getStringExtra("jsonString");

        lv=(ListView)findViewById(R.id.testLv);
        //paycodeAdaptar=new Test(this,R.layout.custom_paycode_row);
        //lv.setAdapter(paycodeAdaptar);

        Toast.makeText(this, jsonString, Toast.LENGTH_SHORT).show();
        try {
            Log.d("jsonpp",Integer.toString(150));
            JSONObject jsonObjec= new JSONObject(jsonString),jsonObject;

            JSONArray jsonArray= jsonObjec.getJSONArray("paycode_detail");
            Log.d("jsonpp",Integer.toString(jsonArray.length()));
            // here 0 object array index,because server returned 1 object as jsonArray
            for(int i=0;i<jsonArray.length();i++){
                jsonObject= jsonArray.getJSONObject(i);
                PayCodeItem  sti=new PayCodeItem();
                sti.set_dept(jsonObject.getString("dept"));
                sti.set_amount(jsonObject.getString("amount"));
                sti.set_year(jsonObject.getString("year"));
                sti.set_semister(jsonObject.getString("semister"));
                sti.set_paycode(jsonObject.getString("pay_code"));
               // paycodeAdaptar.add(sti);
                Log.d("js",Integer.toString(i));

            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("jeror","JSONException");

        }
    }
}
