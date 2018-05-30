package com.example.tanvirhasan.bsmrstupayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Payment_status extends AppCompatActivity {

    ListView listView;
    StatusListViewAdapter statusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_payment_status);

        listView=(ListView)findViewById(R.id.PaymentStatusListView);
        statusAdapter= new StatusListViewAdapter(this,R.layout.custom_row);
        listView.setAdapter(statusAdapter);
        Intent i= getIntent();
        String jsonString=i.getStringExtra("jsonString");
        //Toast.makeText(this, show_status(jsonString), Toast.LENGTH_LONG).show();
        show_status(jsonString);


    }

    String show_status(String jsonString){

        try {
            JSONObject jsonObject= new JSONObject(jsonString);
            JSONArray jsonArray= jsonObject.getJSONArray("status_detail");
            statusAdapter.add(new StatusItem("TnxId","dept","year","semister","amount"));

            for(int i=0;i<jsonArray.length();i++){
                jsonObject= jsonArray.getJSONObject(i);
                StatusItem sti=new StatusItem();
                sti.set_tnx(jsonObject.getString("tnx"));
                sti.set_amount(jsonObject.getString("amount"));
                sti.set__year(jsonObject.getString("year"));
                sti.set_semister(jsonObject.getString("semister"));

                statusAdapter.add(sti);

            }
            return null;

        } catch (JSONException e) {
            e.printStackTrace();
            return "JSONException";
        }
    }

}
