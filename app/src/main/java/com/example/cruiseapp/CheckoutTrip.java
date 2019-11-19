package com.example.cruiseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CheckoutTrip extends AppCompatActivity {

    Intent intent;
    String cruiseName,cabinType;
    double price_per_day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_trip);
        intent=getIntent();
        if(intent!=null)
        {
            cruiseName=intent.getStringExtra("cruisename");
            cabinType=intent.getStringExtra("ctype");
            price_per_day=intent.getIntExtra("cruisename",0);
        }



    }
}
