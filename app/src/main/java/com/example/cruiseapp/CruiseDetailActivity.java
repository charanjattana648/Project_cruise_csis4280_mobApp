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

    ImageView cabin, map ,dining , activity , entertainment,deck;
    Intent intent_activity;
    ParamConcatenation pc=new ParamConcatenation();
    static String cruiseName="";
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
            cruiseName=itemname;
            value[0]=itemname;
        }

        cabin = (ImageView) findViewById(R.id.view_cabin);
        map = (ImageView) findViewById(R.id.view_map);
        dining = (ImageView) findViewById(R.id.view_dining);
        activity = (ImageView) findViewById(R.id.view_Activity);
        entertainment = (ImageView) findViewById(R.id.view_entertainment);
        deck=findViewById(R.id.view_deck);

        cabin.setOnClickListener(this);
        map.setOnClickListener(this);
        dining.setOnClickListener(this);
        activity.setOnClickListener(this);
        entertainment.setOnClickListener(this);
        deck.setOnClickListener(this);

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
                String site=pc.getIp(CruiseDetailActivity.this);
                site+="cabins";
                String param=pc.putParamsTogether(field,value);
                new DownloadAsync(CruiseDetailActivity.this).execute(site,param);
                break;
            case R.id.view_map :
                site=pc.getIp(CruiseDetailActivity.this);
                site+="routeDetails";
                param=pc.putParamsTogether(field,value);
                new DownloadAsync(CruiseDetailActivity.this).execute(site,param);
                break;
            case R.id.view_dining :
                site=pc.getIp(CruiseDetailActivity.this);
                site+="diningList";
                param=pc.putParamsTogether(field,value);
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
            case R.id.view_deck:
                site=pc.getIp(CruiseDetailActivity.this);
                site+="decks";
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
                startActivityIntent(res[1],"itemlist","Activity",cruiseName);
                break;
            case "Entertainment":
                startActivityIntent(res[1],"itemlist","Entertainment",cruiseName);
                break;
            case "Dining":
                startActivityIntent(res[1],"itemlist","Dining",cruiseName);
                break;
            case "RouteDetails":
                startActivityIntent(res[1],"package","RouteDetails",cruiseName);
                break;
            case "Cabins":
                Log.d("res....", "on cabins : "+res[1]);
                startActivityIntent(res[1],"cabin","CabinDetails",cruiseName);
                break;
            case "Decks":
                Log.d("res....", "on decks : "+res[1]);
                intent_activity=new Intent(CruiseDetailActivity.this,DeckActivity.class);
                intent_activity.putExtra("itemlist",res[1].split(","));
                intent_activity.putExtra("cruisename",res[1].split(","));
                startActivity(intent_activity);
                break;
        }
    }

    public void startActivityIntent(String res,String listname,String name,String cname)
    {
        intent_activity=new Intent(CruiseDetailActivity.this,CruiseActivitiesAndCabin.class);
        intent_activity.putExtra("itemname",name);
        intent_activity.putExtra("cruiseName",cname);
        if(listname.equalsIgnoreCase("itemlist"))
        {
            intent_activity.putExtra(listname,res.split(","));
        }else{
            intent_activity.putExtra(listname,res);
        }
        startActivity(intent_activity);
    }

    @Override
    public void onFailure(String result) {

    }

    public void open_drawer(View view) {
    }
}
