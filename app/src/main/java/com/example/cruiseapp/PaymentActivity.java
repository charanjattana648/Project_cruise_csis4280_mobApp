package com.example.cruiseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity  implements OnEventListener<String>{
    Intent intent;
    TextView txtCruiseName,txtTotalPrice,txtRoomNum;
    String cruiseName,packageName,cabinType,numPeople,totalPrice,cardNumber,cardHolderName,cvv,email;
    EditText etCardNumber,etCardHolderName,etCvv,etEmail;
    Button btnPayment;
    String regexVisa = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
            "(?<mastercard>5[1-5][0-9]{14})|" +
            "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
            "(?<amex>3[47][0-9]{13})|" +
            "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
            "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";
    String regexCvv="^[0-9]{3,4}$";
    ParamConcatenation pc=new ParamConcatenation();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        txtTotalPrice=findViewById(R.id.txt_total_price);
        etCardNumber=findViewById(R.id.et_cardNumber);
        etCardHolderName=findViewById(R.id.et_cardHolder);
        etCvv=findViewById(R.id.et_cvc);
        etEmail=findViewById(R.id.et_email);
        txtRoomNum=findViewById(R.id.txt_room_numbers);
        btnPayment=findViewById(R.id.button_checkout);
        intent=getIntent();
        if(intent!=null) {
            txtRoomNum.setText(intent.getStringExtra("roomNumber"));
            txtTotalPrice.setText("$" + intent.getStringExtra("totalPrice"));
            cruiseName = intent.getStringExtra("cruiseName");
            packageName = intent.getStringExtra("cruisePackage");
            cabinType = intent.getStringExtra("cabinType");
            numPeople = intent.getStringExtra("numPeople");
            totalPrice = intent.getStringExtra("totalPrice");
        }
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            cardNumber=etCardNumber.getText().toString();
            cardHolderName=etCardHolderName.getText().toString();
            email=etEmail.getText().toString();
            cvv=etCvv.getText().toString();
            if(!cardNumber.matches(regexVisa))
            {
                Toast.makeText(PaymentActivity.this, "Please Enter valid Card Number", Toast.LENGTH_SHORT).show();
            }else if(!cvv.matches(regexCvv)){
                Toast.makeText(PaymentActivity.this, "Please Enter valid Cvv number", Toast.LENGTH_SHORT).show();
            }else{

                String field[]={"packageName","roomNumber","userName","numPeople","cabinType","totalPrice"};
                String values[]={packageName,txtRoomNum.getText().toString(),email,numPeople,cabinType,totalPrice};
                bookTicket(field,values);
            }
            }
        });
    }
    public void bookTicket(String[] field,String[] value)
    {
        String site = pc.getIp(PaymentActivity.this);
        site+="bookCruiseTickets";
        String params = pc.putParamsTogether(field, value);
        new DownloadAsync(PaymentActivity.this).execute(site, params);
    }

    @Override
    public void onSuccess(String result) {
        String res[]=result.split("@:");
        switch (res[0])
        {
            case "SeatsBooked":
                Toast.makeText(this, ""+res[1], Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onFailure(String result) {

    }

    public void open_drawer(View view) {
    }
}

