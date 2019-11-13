package com.example.cruiseapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements OnEventListener<String>
{
    ParamConcatenation pc=new ParamConcatenation();
    Button btn_find_Cruise;
    Intent intent;
    String data[];
    Spinner cruise_spinner,day_spinner,destination_spinner;
    ArrayAdapter<String> cruiseName_adapter,cruiseDest_adapter,cruiseDays_adapter;
    ArrayList<String> CruiseNameList,CruiseDestList,CruiseDayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_m);
        btn_find_Cruise=findViewById(R.id.findCruise_btn);
        cruise_spinner=findViewById(R.id.selectCruise_spinner);
        day_spinner=findViewById(R.id.selectDay_spinner);
        destination_spinner=findViewById(R.id.selectDest_spinner);
        pc=new ParamConcatenation();
        String site=pc.getIp(MainActivity.this);
        site+="cruiseNameList";
        new DownloadAsync(MainActivity.this).execute(site,"");
        site=pc.getIp(MainActivity.this);
        site+="cruiseDestList";
        String[] field={"cruiseName"};
        String[] value={""};
        String params=pc.putParamsTogether(field,value);
        new DownloadAsync(MainActivity.this).execute(site,params);

        site=pc.getIp(MainActivity.this);
        site+="daysList";
        String[] field_day={"cruiseDestination"};
        String[] value_day={""};
        params=pc.putParamsTogether(field_day,value_day);
        new DownloadAsync(MainActivity.this).execute(site,params);

        cruise_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
//                    String site = pc.getIp(MainActivity.this);
//                    site += "cruiseDestList";
//                    String[] field = {"cruiseName"};
//                    String[] value = {parent.getItemAtPosition(position).toString()};
//                    String params = pc.putParamsTogether(field, value);
//                    new DownloadAsync(MainActivity.this).execute(site, params);
                    changeSpinnerAdapter( "cruiseDestList", "cruiseName", parent.getItemAtPosition(position).toString().trim());
                   // changeSpinnerAdapter( "daysList", "cruiseDestination", parent.getItemAtPosition(position).toString());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        destination_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
//                    String site = pc.getIp(MainActivity.this);
//                    site+="daysList";
//                    String[] field={"cruiseDestination"};
//                    String[] value = {parent.getItemAtPosition(position).toString()};
//                    String params = pc.putParamsTogether(field, value);
//                    new DownloadAsync(MainActivity.this).execute(site, params);

                    changeSpinnerAdapter( "daysList", "cruiseDestination", parent.getItemAtPosition(position).toString().trim());
                    Log.d("test da", "onClick: entering");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





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

    public void changeSpinnerAdapter(String siteFilter,String fieldName,String filtername)
    {
        String site = pc.getIp(MainActivity.this);
        site+=siteFilter;
        String[] field={fieldName};
        String[] value = {filtername};
        String params = pc.putParamsTogether(field, value);
        new DownloadAsync(MainActivity.this).execute(site, params);
    }

    @Override
    public void onSuccess(String result) {
        Log.d("test data...", "onSuccess: "+result);

            String[] res=result.split("@:");

            switch (res[0].trim())
            {
                case "CruiseList":
                    intent.putExtra("cruiseData",res[1]);
                    startActivity(intent);
                    break;
                case "CruiseNames":
//                    String[] names=res[1].split(",");
//                    CruiseNameList=new ArrayList<>(Arrays.asList(names));
//                    CruiseNameList.add(0,"Select CruiseName");
//                    cruiseName_adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,CruiseNameList);
//                    cruise_spinner.setAdapter(cruiseName_adapter);

                    data=res[1].split(",");
                    setSpinnerAdapter(data,"Select CruiseName",cruise_spinner);
                    break;
                case "DestinationsList":
//                    String[] destinations=res[1].split(",");
//                    CruiseDestList=new ArrayList<>(Arrays.asList(destinations));
//                    CruiseDestList.add(0,"Select Destination");
//                    cruiseDest_adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,CruiseDestList);
//                    destination_spinner.setAdapter(cruiseDest_adapter);

                    data=res[1].split(",");
                    setSpinnerAdapter(data,"Select Destination",destination_spinner);
                    break;
                case "DaysList":
                    data=res[1].split(",");
                    setSpinnerAdapter(data,"Select Number of Days",day_spinner);
                    break;
            }
    }

    public void setSpinnerAdapter(String[] res,String title,Spinner spinner)
    {
        CruiseDayList=new ArrayList<>(Arrays.asList(res));
        CruiseDayList.add(0,title);
        cruiseDays_adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,CruiseDayList);
        spinner.setAdapter(cruiseDays_adapter);
    }

    @Override
    public void onFailure(String result) {

    }
}
