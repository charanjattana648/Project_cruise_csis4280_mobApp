package com.example.cruiseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CruiseDetailActivity extends AppCompatActivity implements View.OnClickListener ,OnEventListener<String>{

    ImageView cabin, map ,dining , activity , entertainment;
    Intent intent_activity;
    ParamConcatenation pc=new ParamConcatenation();
    private String field[]={"cruiseName"};
    Intent intent;
    private static String[] value=new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cruise_detail);
        intent=getIntent();
        if(intent!=null)
        {
            String itemname=intent.getStringExtra("name");
            Toast.makeText(this, ""+intent.getStringExtra("name"), Toast.LENGTH_SHORT).show();
           // Log.d("name cdactivity", "onCreate: "+);
            value[0]=itemname;
        }

        cabin = (ImageView) findViewById(R.id.view_cabin);
        map = (ImageView) findViewById(R.id.view_map);
        dining = (ImageView) findViewById(R.id.view_dining);
        activity = (ImageView) findViewById(R.id.view_Activity);
        entertainment = (ImageView) findViewById(R.id.view_entertainment);


        cabin.setOnClickListener(this);
        map.setOnClickListener(this);
        dining.setOnClickListener(this);
        activity.setOnClickListener(this);
        entertainment.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        intent=getIntent();
        if(intent!=null)
        {
            String itemname=intent.getStringExtra("name");
            Toast.makeText(this, ""+intent.getStringExtra("name"), Toast.LENGTH_SHORT).show();
            // Log.d("name cdactivity", "onCreate: "+);
            value[0]=itemname;
        }
        Log.d("hello", "onClick: "+v);
        switch (v.getId())
        {
            case R.id.view_cabin:
                break;
            case R.id.view_map :
                break;
            case R.id.view_dining :
                String site=pc.getIp(CruiseDetailActivity.this);
                site+="diningList";
                String param=pc.putParamsTogether(field,value);
                new DownloadAsync(CruiseDetailActivity.this).execute(site,param);
                break;
            case R.id.view_Activity:
                site=pc.getIp(CruiseDetailActivity.this);
                site+="activitiesList";
                param=pc.putParamsTogether(field,value);
                new DownloadAsync(CruiseDetailActivity.this).execute(site,param);
                break;
            case R.id.view_entertainment:
                site=pc.getIp(CruiseDetailActivity.this);
                site+="entertainmentList";
                param=pc.putParamsTogether(field,value);
                new DownloadAsync(CruiseDetailActivity.this).execute(site,param);
                break;
        }
    }

    @Override
    public void onSuccess(String result) {
        Log.d("res : ", "onSuccess: "+result);
        String res[]=result.split("@:");
        switch (res[0].trim())
        {
            case "Activity":
                startActivityIntent(res[1].split(","),"Activity");
                break;
            case "Entertainment":
                startActivityIntent(res[1].split(","),"Entertainment");
                break;
            case "Dining":
                startActivityIntent(res[1].split(","),"Dining");
                break;
        }
    }

    public void startActivityIntent(String[] res,String name)
    {
        intent_activity=new Intent(CruiseDetailActivity.this,CruiseActivitiesAndCabin.class);
        intent_activity.putExtra("itemname",name);
        intent_activity.putExtra("itemlist",res);
        startActivity(intent_activity);
    }

    @Override
    public void onFailure(String result) {

    }
}
