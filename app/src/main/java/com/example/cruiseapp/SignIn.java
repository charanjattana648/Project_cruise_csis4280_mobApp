package com.example.cruiseapp;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity implements OnEventListener<String>{

    EditText email,psw;
    Button btn_login;
    Intent mainActivity_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if(Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        btn_login=findViewById(R.id.register_Btn);
        email=findViewById(R.id.firstName_txt);
        psw=findViewById(R.id.pass_txt);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String site="http://54.175.177.16:8000";
//                String site= String.valueOf(R.string.site);
                site+="/login";
                String field[]={"email","psw"};
                String field_values[]={email.getText().toString(),psw.getText().toString()};
                ParamConcatenation pc=new ParamConcatenation();
                String params=pc.putParamsTogether(field,field_values);
                mainActivity_intent=new Intent(SignIn.this,MainActivity.class);
                new DownloadAsync(SignIn.this).execute(site,params);
            }
        });



    }

    @Override
    public void onSuccess(String result) {
        String res[]=result.split(":");
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        startActivity(mainActivity_intent);
    }

    @Override
    public void onFailure(String result) {
        String res[]=result.split(":");
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
    }
}
