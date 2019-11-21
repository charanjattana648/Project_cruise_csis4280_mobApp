package com.example.cruiseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckoutTrip extends AppCompatActivity implements OnEventListener<String>{

    Intent intent,paymentIntent;
    String cruiseName,cabinType;
    double price_per_day;
    TextView txtCruiseName,txtPrice,txtTotalPrice,txtDays,txtDestination,txtCabinType;
    EditText etnumPeople,etnumRooms;
    Spinner spinnerDeck,spinnerPackage;
    SpinnerAdapter deckAdapter;
    Button btnCheckout;
    ParamConcatenation pc=new ParamConcatenation();
    final int[] DECK_MIN={1000,2000,3000};
    final int[] DECK_MAX={1600,2600,3600};
    int roomNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_trip);
        txtCruiseName=findViewById(R.id.txt_cruiseName);
        txtPrice=findViewById(R.id.txt_Price);

        txtDays=findViewById(R.id.txt_days);
        txtDestination=findViewById(R.id.txt_dest);
        txtCabinType=findViewById(R.id.txt_cabin_type);
        etnumPeople=findViewById(R.id.et_num_people);
        spinnerDeck=findViewById(R.id.spinner_deck);
        spinnerPackage=findViewById(R.id.spinner_package);
        btnCheckout=findViewById(R.id.button_continue_payment);
        String[] decks={"Deck 1","Deck 2","Deck 3"};
        deckAdapter=new ArrayAdapter<String>(CheckoutTrip.this,android.R.layout.simple_list_item_1,Arrays.asList(decks));
        spinnerDeck.setAdapter(deckAdapter);
        intent=getIntent();
        if(intent!=null)
        {
            cruiseName=intent.getStringExtra("cruisename");
            cabinType=intent.getStringExtra("ctype");
            price_per_day=Double.parseDouble(intent.getStringExtra("cprice"));
            Toast.makeText(this, cruiseName+" .. "+price_per_day+" .. "+cabinType, Toast.LENGTH_LONG).show();
            if(cruiseName!="")
            {
                changeSpinnerAdapter("getPackages","cruiseName",cruiseName);
            }

        }
        txtCruiseName.setText(cruiseName);
        txtPrice.setText("$"+price_per_day);
        txtCabinType.setText(cabinType);

        spinnerPackage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeSpinnerAdapter("cruiseDestList","packageName",spinnerPackage.getSelectedItem().toString());
                changeSpinnerAdapter("daysList","packageName",spinnerPackage.getSelectedItem().toString());

                Toast.makeText(CheckoutTrip.this, ""+spinnerPackage.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRooms();
            }
        });



    }
    public void checkRooms()
    {
        int i=spinnerDeck.getSelectedItemPosition();
        roomNumber= (int) ((Math.random()*(DECK_MAX[i]-DECK_MIN[i]))+DECK_MIN[i]);
        String[] field={"packageName","roomNumber"};
        String[] value = {spinnerPackage.getSelectedItem().toString(),String.valueOf(roomNumber)};
        String site = pc.getIp(CheckoutTrip.this);
        site+="checkRoomAval";
        String params = pc.putParamsTogether(field, value);
        new DownloadAsync(CheckoutTrip.this).execute(site, params);
    }
    public void changeSpinnerAdapter(String siteFilter,String fieldName,String filtername)
    {
        String site = pc.getIp(CheckoutTrip.this);
        site+=siteFilter;
        String[] field={fieldName};
        String[] value = {filtername};
        String params = pc.putParamsTogether(field, value);
        new DownloadAsync(CheckoutTrip.this).execute(site, params);
    }

    @Override
    public void onSuccess(String result) {

        String[] res=result.split("@:");
        switch (res[0])
        {
            case "PackagesList":
                ArrayList<String> packageList=new ArrayList<>(Arrays.asList(res[1].split(",")));
                deckAdapter=new ArrayAdapter<String>(CheckoutTrip.this,android.R.layout.simple_list_item_1,packageList);
                spinnerPackage.setAdapter(deckAdapter);
                Toast.makeText(this, ""+res[1], Toast.LENGTH_SHORT).show();
                break;
            case "DestinationsList":
                txtDestination.setText(res[1]);
                break;
            case "DaysList":
                txtDays.setText(res[1]);
                break;
            case "checkRoomResult":
                if(Integer.parseInt(res[1].trim())==0){
                    setData();
                    paymentIntent.putExtra("roomNumber",roomNumber+"");
                    startActivity(paymentIntent);
                }else{
                    checkRooms();
                }
                break;
        }
    }

    public void setData(){
        double totalPrice=Double.parseDouble(txtPrice.getText().toString().substring(1));
        totalPrice*=Double.parseDouble(etnumPeople.getText().toString());
        totalPrice*=Double.parseDouble(txtDays.getText().toString());
        paymentIntent=new Intent(CheckoutTrip.this,PaymentActivity.class);
        paymentIntent.putExtra("cruiseName",txtCruiseName.getText().toString());
        paymentIntent.putExtra("cruisePackage",spinnerPackage.getSelectedItem().toString());
        paymentIntent.putExtra("cabinType",txtCabinType.getText().toString());
        paymentIntent.putExtra("numPeople",etnumPeople.getText().toString());
        paymentIntent.putExtra("totalPrice",totalPrice+"");
    }

    @Override
    public void onFailure(String result) {

    }
}
