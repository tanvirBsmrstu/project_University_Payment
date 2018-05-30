package com.example.tanvirhasan.alpha_cap;

import android.app.ActionBar;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.view.ActionMode;
import android.view.ActionProvider;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by tanvir hasan on 2/2/2016.
 */
public class BackGroundTask extends AsyncTask<Person,String,String> {


    Context context;
    String title="Connecting with server"; //progressFialog title
    ProgressDialog p;
    NotificationCompat.Builder nBuilder;
    Person personInfo;

    String requestData(){

        try {
            URL url= new URL(personInfo.get_toUrl()); // get url is it reg or login?
            HttpURLConnection htpUC= (HttpURLConnection) url.openConnection();  //open conntection
            htpUC.setRequestMethod("POST");   // set request method post or get
            htpUC.setDoOutput(true);   // we will use to output & input so its true
            htpUC.setDoInput(true);
            htpUC.setConnectTimeout(1500);
            htpUC.setReadTimeout(1500);

            OutputStream os= htpUC.getOutputStream(); //get output stream from server to write
            BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(os,"utf-8"));

            String data=null;
            if(personInfo.get_method()=="registrationKey") {

                data = URLEncoder.encode("name", "utf-8") + "=" + URLEncoder.encode(personInfo.get_name(), "utf-8") + "&" +         // encode data for passing
                        URLEncoder.encode("userId", "utf-8") + "=" + URLEncoder.encode(personInfo.get_id(), "utf-8") + "&" +  //in a id base indexing formate
                        URLEncoder.encode("pass", "utf-8") + "=" + URLEncoder.encode(personInfo.get_pass(), "utf-8") + "&" +  //no space at equal sign
                        URLEncoder.encode("mob", "utf-8") + "=" + URLEncoder.encode(personInfo.get_mob(), "utf-8") + "&" +
                        URLEncoder.encode("email", "utf-8") + "=" + URLEncoder.encode(personInfo.get_email(), "utf-8");
            }
            else if(personInfo.get_method()=="loginKey"){

                data = URLEncoder.encode("userId", "utf-8") + "=" + URLEncoder.encode(personInfo.get_id(), "utf-8") + "&" +  //in a id base indexing formate
                        URLEncoder.encode("pass", "utf-8") + "=" + URLEncoder.encode(personInfo.get_pass(), "utf-8");  //no space at equal sign
            }
            bw.write(data); //write encoded data to outputstream for send to server

            bw.flush();
            bw.close();
            os.close();

            // get quick response to know those echo that in php file
            InputStream IS= htpUC.getInputStream();  //get data or response
            BufferedReader br= new BufferedReader(new InputStreamReader(IS));
            StringBuilder  respons=new StringBuilder();
            String strLine=null;
            while((strLine=br.readLine())!=null){
                 respons.append(strLine);
            }
            IS.close();
            br.close();

            return  respons.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return  "MalformedURLException";
        } catch (IOException e) {
            e.printStackTrace();
            return "IOException for httpurlconnection";
        }

    }


    public BackGroundTask(Context context) {
        this.context=context;
        personInfo= new Person();
    }

    @Override
    protected void onPreExecute() {

        p=new ProgressDialog(context,ProgressDialog.STYLE_SPINNER);
        p=ProgressDialog.show(context,title,"Athunticating");
        nBuilder= new NotificationCompat.Builder(context);
        nBuilder.setSmallIcon(R.drawable.bsmrstu_logo);
        nBuilder.setContentTitle("BSMRSTU Online Payment");

        super.onPreExecute();
    }



    @Override
    protected void onProgressUpdate(String... values) {

        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(Person... params) {
            personInfo=params[0];
            return requestData();
    }

    @Override
    protected void onPostExecute(String s) {

        p.dismiss();

        if(personInfo.get_method()=="registrationKey") {

            if (s.contains("Exception")||s.contains("error")) {
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
                nBuilder.setContentText("sorry Connection error!!!");

            }
            else if(s.contains("exists")){
                Toast.makeText(context, "This id already exists", Toast.LENGTH_LONG).show();
                nBuilder.setContentText("Sorry you are trying for duplicate registration ");
            }
            else {
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
                nBuilder.setContentText("Registration with id "+personInfo.get_id()+" successfull");

            }

            ///show notification
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, nBuilder.build()); //here 1 is notificationId

            //after complete registration task sucessfully or unsuccessfully back to the login screen
            Intent i= new Intent(context,MainActivity.class);
            i.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
            context.startActivity(i);
        }
        else if(personInfo.get_method()=="loginKey"){


                if(s.contains("wrong")){  //failed in login
                    Toast.makeText(context, "Sorry wrong id or password ", Toast.LENGTH_LONG).show();

                }
                else  if (s.contains("Exception")){  ///had exception
                    Toast.makeText(context, s, Toast.LENGTH_LONG).show();
                }
                else {
                    s= json_parse(s); // now personInfo contain the details of loged in user
                    Toast.makeText(context, s, Toast.LENGTH_LONG).show();

                    //go to new activity
                    Intent i=new Intent(this.context,AfterLogin.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK); // remove this activity from stack
                    context.startActivity(i);

                }
        }

        super.onPostExecute(s);
    }

    String json_parse(String jsonString){
            ///get info from server as json
        try {
            JSONObject jsonObject= new JSONObject(jsonString);
            JSONArray jsonArray= jsonObject.getJSONArray("basicInfo");
            jsonObject= jsonArray.getJSONObject(0); // here 0 object array index,because server returned 1 object as jsonArray

            personInfo.set_name(jsonObject.getString("name"));
            personInfo.set_id(jsonObject.getString("id"));
            personInfo.set_email(jsonObject.getString("email"));
            personInfo.set_mob(jsonObject.getString("mob"));
            personInfo.set_pass(jsonObject.getString("pass"));
            personInfo.set_method("");
            personInfo.set_toUrl("");
            // save data to sharedPreferences
            SharedPreferences sharedPreferences= context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putString("name",personInfo.get_name());
            editor.putString("id",personInfo.get_id());
            editor.putString("pass",personInfo.get_pass());
            editor.putString("mob",personInfo.get_mob());
            editor.putString("email",personInfo.get_email());
            editor.commit();

            return "login & json sucessful";

        } catch (JSONException e) {
            e.printStackTrace();
            return "JSONException";
        }
    }
}
