package com.example.cruiseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class CruiseActivitiesAndCabin extends AppCompatActivity {

    CustomActivitiesAdapter customActivitiesAdapter;
    CustomMapAdapter customMapAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> itemList=new ArrayList<>();
        setContentView(R.layout.activity_cruise_activities_and_cabin);
        Intent intent=getIntent();
        recyclerView=findViewById(R.id.activity_recyclerView);
        if(intent!=null)
        {
            String[] res=intent.getStringArrayExtra("itemlist");
            String itemname=intent.getStringExtra("itemname");
           String packageList=intent.getStringExtra("package");
           String cabin_str=intent.getStringExtra("cabin");
           if(res!=null)
           {
               itemList=new ArrayList<>(Arrays.asList(res));
           }
            switch (itemname)
            {
                case "Activity":
                    customActivitiesAdapter=new CustomActivitiesAdapter(itemList,R.drawable.act,itemname);
                    recyclerView.setAdapter(customActivitiesAdapter);
                    break;
                case "Entertainment":
                    customActivitiesAdapter=new CustomActivitiesAdapter(itemList,R.drawable.ent,itemname);
                    recyclerView.setAdapter(customActivitiesAdapter);
                    break;
                case "Dining":
                    customActivitiesAdapter=new CustomActivitiesAdapter(itemList,R.drawable.din,itemname);
                    recyclerView.setAdapter(customActivitiesAdapter);
                    break;
                case "RouteDetails":
                    ArrayList<CruisePackage> packagesList=getPackageList(packageList);
                    customMapAdapter=new CustomMapAdapter(packagesList);
                    recyclerView.setAdapter(customMapAdapter);
                    break;
                case "CabinDetails":
                   // ArrayList<CruiseCabin> cabinList=getCabinList(cabin_str);
                    //customMapAdapter=new CustomMapAdapter(packagesList);
                    //recyclerView.setAdapter(customMapAdapter);
                    break;

            }


            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
        }


    }

    private ArrayList<CruiseCabin> getCabinList(String result) {
        JSONArray cruiseArray=null;
        JSONObject cruiseObject=null;
        ArrayList<CruiseCabin> cpList=new ArrayList<>();

        try {
            Log.d("res..", "getPackageList: "+result);
            cruiseArray=new JSONArray(result);
            for(int i=0;i<cruiseArray.length();i++)
            {
                CruiseCabin c=new CruiseCabin();
                cruiseObject = cruiseArray.getJSONObject(i);
                c.setType(cruiseObject.getString("Type"));
                c.setCabinSize(cruiseObject.getString("CabinSize"));
                c.setConnectedRooms(cruiseObject.getString("ConnectedRooms"));
                c.setAccessibleRooms(cruiseObject.getString("AccessibleRooms"));
                c.setPrice(cruiseObject.getDouble("Price"));
                c.setImage(cruiseObject.getString("Image"));
                c.setMaximumPassengers(cruiseObject.getString("MaximumPassengers"));
                c.setTotalCabins(cruiseObject.getString("TotalCabins"));

                cpList.add(c);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cpList;
    }

    public ArrayList<CruisePackage> getPackageList(String result)
    {
        JSONArray cruiseArray=null;
        JSONObject cruiseObject=null;
        ArrayList<CruisePackage> cpList=new ArrayList<>();

        try {
            Log.d("res..", "getPackageList: "+result);
            cruiseArray=new JSONArray(result);
            for(int i=0;i<cruiseArray.length();i++)
            {
                CruisePackage c=new CruisePackage();
                cruiseObject = cruiseArray.getJSONObject(i);
                c.setCruiseName(cruiseObject.getString("CruiseName"));
                c.setPackageName(cruiseObject.getString("PackageName"));
                c.setNum_Days(cruiseObject.getInt("Num_Days"));
                c.setDeparts_from(cruiseObject.getString("Departs_from"));
                c.setDeptDate(cruiseObject.getString("DeptDate"));
                c.setSails_to(cruiseObject.getString("Sails_to"));
                c.setRouteMap(cruiseObject.getString("RouteMap"));

                cpList.add(c);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cpList;
    }


}
