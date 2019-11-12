package com.example.cruiseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class CruiseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cruise_detail);
        Intent intent=getIntent();
        if(intent!=null)
        {
            Toast.makeText(this, ""+intent.getStringExtra("name"), Toast.LENGTH_SHORT).show();
            Log.d("name cdactivity", "onCreate: "+intent.getStringExtra("name"));
        }
    }
}
