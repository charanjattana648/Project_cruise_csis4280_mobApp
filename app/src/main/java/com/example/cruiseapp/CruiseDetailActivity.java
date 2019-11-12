package com.example.cruiseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CruiseDetailActivity extends AppCompatActivity {

    ImageView cabin, map ,dining , activity , entertainment;

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

        cabin = (ImageView) findViewById(R.id.view_cabin);
        map = (ImageView) findViewById(R.id.view_map);
        dining = (ImageView) findViewById(R.id.view_dining);
        activity = (ImageView) findViewById(R.id.view_Activity);
        entertainment = (ImageView) findViewById(R.id.view_entertainment);

        cabin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CruiseDetailActivity.this, "HELLO", Toast.LENGTH_SHORT).show();

            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
