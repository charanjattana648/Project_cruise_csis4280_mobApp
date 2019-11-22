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
import java.util.LinkedHashSet;
import java.util.Set;

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
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black);
//        getActionBar().setHomeButtonEnabled(true);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
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
               // Set<String> cruiseNamelist = new LinkedHashSet<String>();
                //filterNames
                String site=pc.getIp(MainActivity.this);
                site+="filterNames";
                String field_str="";
                String fieldValues_str="";
                int i=0;
                if(cruise_spinner.getSelectedItemPosition()!=0)
                {
                    field_str+="cruiseName";
                    fieldValues_str+=cruise_spinner.getSelectedItem().toString();
                    Log.d("ffi ", "cruise_spinner: "+cruise_spinner.getSelectedItem().toString());
                    i++;
                }
                if(day_spinner.getSelectedItemPosition()!=0)
                {
                    if(i>0)
                    {field_str+=",";fieldValues_str+=",";}
                    field_str+="numOfDays";
                    fieldValues_str+=day_spinner.getSelectedItem().toString();
                    Log.d("ffi ", "day_spinner: "+day_spinner.getSelectedItem().toString());
                    i++;
                }
                if(destination_spinner.getSelectedItemPosition()!=0)
                {
                    if(i>0)
                    {field_str+=",";fieldValues_str+=",";}
                    field_str+="cruiseDestination";
                    fieldValues_str+=destination_spinner.getSelectedItem().toString();
                    Log.d("ffi ", "onClick: destination_spinner : "+destination_spinner.getSelectedItem().toString());
                    i++;
                }

//                list3.addAll(Arrays.asList(array1));
//                list3.addAll(Arrays.asList(array2));
//                String array3[] = list3.toArray(new String[list3.size()]);
                String[] fa=field_str.split(",");
                String[] fb=fieldValues_str.split(",");
                Log.d("test..a", "onClick: "+Arrays.toString(fa));
                Log.d("test..b", "onClick: "+Arrays.toString(fb));
                if(i==0)
                {
                    new DownloadAsync(MainActivity.this).execute(site,"");
                }else{
                    String params=pc.putParamsTogether(field_str.split(","),fieldValues_str.split(","));
                    Log.d("site param data..", "onClick: "+params);
                    new DownloadAsync(MainActivity.this).execute(site,params);
                }
                //intent=new Intent(MainActivity.this,CruiseRVListActivity.class);

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
                    data=res[1].split(",");
                    setSpinnerAdapter(data,"Select CruiseName",cruise_spinner);
                    break;
                case "DestinationsList":
                    data=res[1].split(",");
                    setSpinnerAdapter(data,"Select Destination",destination_spinner);
                    break;
                case "DaysList":
                    data=res[1].split(",");
                    setSpinnerAdapter(data,"Select Number of Days",day_spinner);
                    break;
                case "FilteredNames":
                    Log.d("ffil --", "onSuccess: "+res[1]);
                    String site=pc.getIp(MainActivity.this);
                    site+="cruiseList";
                    String r=res[1].trim();
                    String[] field={"cruiseName"};
                    String[] value={r};
                    String params=pc.putParamsTogether(field,value);
                    intent=new Intent(MainActivity.this,CruiseRVListActivity.class);
                    new DownloadAsync(MainActivity.this).execute(site,params);
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
