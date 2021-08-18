package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            JSONObject obj = new JSONObject(loadJSON());
            JSONArray marray = obj.getJSONArray("faculty");
            for(int i=0;i<marray.length();i++){
                JSONObject jinside = marray.getJSONObject(i);
                String Name = jinside.getString("Name");
                String Designation = jinside.getString("Designation");
                Toast.makeText(getApplicationContext(),Name,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),Designation,Toast.LENGTH_SHORT).show();
                JSONArray insideArray = jinside.getJSONArray("contact");
                for(int j=0;j<insideArray.length();j++){
                    JSONObject jjinside = insideArray.getJSONObject(j);
                    String Email = jjinside.getString("Email");
                    Toast.makeText(getApplicationContext(),Email,Toast.LENGTH_SHORT).show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String loadJSON(){
        String json;
        try{
            InputStream is = getApplicationContext().getAssets().open("faculty.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer,"utf-8");

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
