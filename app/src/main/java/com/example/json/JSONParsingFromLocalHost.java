package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.Buffer;

public class JSONParsingFromLocalHost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonparsing_from_local_host);
        Jsondata jd = new Jsondata();
    }

    public class Jsondata extends AsyncTask<Void,Void,String> {
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getApplicationContext());
            pd.setTitle("Loading");
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://your_localhost/androidexample/faculty.json");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String data;
                StringBuilder result = new StringBuilder();

                while((data=reader.readLine())!=null){
                    result.append(data);

                }

                JSONObject obj = new JSONObject(result.toString());
                JSONArray marray = obj.getJSONArray("faculty");
                for(int i=0;i<marray.length();i++){
                    JSONObject jinside = marray.getJSONObject(i);
                    String Name = jinside.getString("Name");
                    String Designation = jinside.getString("Designation");
                    Log.d("Name",Name);
                    //Toast.makeText(getApplicationContext(),Name,Toast.LENGTH_SHORT).show();
                    Log.d("Designation",Designation);
                    //Toast.makeText(getApplicationContext(),Designation,Toast.LENGTH_SHORT).show();
                    JSONArray insideArray = jinside.getJSONArray("contact");
                    for(int j=0;j<insideArray.length();j++){
                        JSONObject jjinside = insideArray.getJSONObject(j);
                        String Email = jjinside.getString("Email");
                        Log.d("Email",Email);
                        //Toast.makeText(getApplicationContext(),Email,Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
        }
    }
}
