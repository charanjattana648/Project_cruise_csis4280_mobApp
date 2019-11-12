package com.example.cruiseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CruiseRVListActivity extends AppCompatActivity implements OnEventListener<String> {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter=new CustomAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cruise_rvlist);

        recyclerView=findViewById(R.id.list_recycleView);
        Intent intent=getIntent();
        ArrayList<Cruise> cruise_arrayList=new ArrayList<>();
        if(intent!=null)
        {
            Log.d("json string ", "onCreate: "+intent.getStringExtra("cruiseData"));
            String[] res=intent.getStringExtra("cruiseData").split("@:");
            String result=res[0];

            Log.d("json data ", "onCreate: "+result);
            if(res.length==2)
            {
                result=res[1];
            }
            Log.d("json data ", "onCreate: "+result);
            cruise_arrayList=getCruiseList(result);
            customAdapter=new CustomAdapter(cruise_arrayList);
            recyclerView.setAdapter(customAdapter);

            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

            recyclerView.setLayoutManager(linearLayoutManager);

        }


        //adapter



    }
    public String[] jsonArraytoStringArray(JSONArray jsonArray,int length)
    {
        String[] simple_array=new String[length];
        for(int i=0;i<length;i++)
        {
            try {
                simple_array[i]=jsonArray.get(i).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return simple_array;
    }
    public ArrayList<Cruise> getCruiseList(String result)
    {
        JSONArray cruiseArray=null;
        JSONObject cruiseObject=null;
        ArrayList<Cruise> cList=new ArrayList<>();

        try {
            cruiseArray=new JSONArray(result);
            String[] departureArray;
            String[] destinationArray;
            for(int i=0;i<cruiseArray.length();i++)
            {
                Cruise c=new Cruise();
                cruiseObject = cruiseArray.getJSONObject(i);
                c.setCruiseName(cruiseObject.getString("CruiseName"));
                c.setCompanyName(cruiseObject.getString("CompanyName"));
                c.setCrew(cruiseObject.getInt("Crew"));
                c.setGuests(cruiseObject.getInt("Guests"));
                c.setCruiseImage(cruiseObject.getString("CruiseImage"));
                c.setLaunched(cruiseObject.getInt("Launched"));
                JSONArray jsonArray=cruiseObject.getJSONArray("Departs_from");
                departureArray=jsonArraytoStringArray(jsonArray,jsonArray.length());

                jsonArray=cruiseObject.getJSONArray("Sails_to");
                destinationArray=jsonArraytoStringArray(jsonArray,jsonArray.length());
                c.setDeparts_from(departureArray);
                c.setSails_to(destinationArray);
                cList.add(c);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cList;
    }

    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onFailure(String result) {

    }
}
