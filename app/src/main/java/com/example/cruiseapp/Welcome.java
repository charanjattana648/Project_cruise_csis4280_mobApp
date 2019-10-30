package com.example.cruiseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent i = new Intent(Welcome.this, MainPage.class);
                startActivity(i);
            }
        };
        Timer open = new Timer();
        open.schedule(task, 4000);
    }
}
