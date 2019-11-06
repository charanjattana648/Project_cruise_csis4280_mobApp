package com.example.cruiseapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnEventListener<String>
{
    ParamConcatenation pc=new ParamConcatenation();
    Button btn_find_Cruise;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_m);
        btn_find_Cruise=findViewById(R.id.findCruise_btn);


        btn_find_Cruise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String site=pc.getIp(MainActivity.this);
                site+="cruiseList";
                String field[]={};
                String fieldValues[]={};
                String params=pc.putParamsTogether(field,fieldValues);
                intent=new Intent(MainActivity.this,CruiseRVListActivity.class);
                new DownloadAsync(MainActivity.this).execute(site,params);
                Log.d("test da", "onClick: entering");
            }
        });

    }


    @Override
    public void onSuccess(String result) {
        Log.d("test data...", "onSuccess: "+result);

            intent.putExtra("cruiseData",result);
            startActivity(intent);
    }

    @Override
    public void onFailure(String result) {

    }
}
