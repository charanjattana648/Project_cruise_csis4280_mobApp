package com.example.cruiseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class DeckActivity extends AppCompatActivity {

    Spinner deck_spinner;
    ArrayList<String> deckList;
    ArrayAdapter<String> deckAdapter;
    String[] deckImagesList;
    ImageView deckImg;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);
        deck_spinner=findViewById(R.id.spinner_deck);
        deckImg=findViewById(R.id.deck_imgView);
        intent=getIntent();
        if(intent!=null)
        {
            deckImagesList=intent.getStringArrayExtra("itemlist");
        }


        deckList=new ArrayList<>(Arrays.asList("Deck 1","Deck 2","Deck 3"));
        deckAdapter=new ArrayAdapter<String>(DeckActivity.this,android.R.layout.simple_list_item_1,deckList);
        deck_spinner.setAdapter(deckAdapter);
        deck_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(deckImagesList!=null || deckImagesList[position]!=null)
                {
                    Picasso.get().load(deckImagesList[position]).into(deckImg);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
