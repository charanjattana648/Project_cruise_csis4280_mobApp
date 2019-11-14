package com.example.cruiseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class CruiseActivitiesAndCabin extends AppCompatActivity {

    CustomActivitiesAdapter customActivitiesAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cruise_activities_and_cabin);
        Intent intent=getIntent();
        recyclerView=findViewById(R.id.activity_recyclerView);
        if(intent!=null)
        {
            String[] res=intent.getStringArrayExtra("itemlist");
            String itemname=intent.getStringExtra("itemname");
            ArrayList<String> itemList=new ArrayList<>(Arrays.asList(res));
            switch (itemname)
            {
                case "Activity":
                    customActivitiesAdapter=new CustomActivitiesAdapter(itemList,R.drawable.act,itemname);
                    break;
                case "Entertainment":
                    customActivitiesAdapter=new CustomActivitiesAdapter(itemList,R.drawable.ent,itemname);
                    break;
                case "Dining":
                    customActivitiesAdapter=new CustomActivitiesAdapter(itemList,R.drawable.din,itemname);
                    break;

            }

            recyclerView.setAdapter(customActivitiesAdapter);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
        }


    }


}
