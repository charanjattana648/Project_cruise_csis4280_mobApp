package com.example.cruiseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements OnEventListener<String> {

    EditText firstName,lastName,email,pass,confirm_pass;
    Button btn_register;
    Intent signIn_intent;
    ParamConcatenation pc=new ParamConcatenation();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstName=findViewById(R.id.firstName_txt);
        lastName=findViewById(R.id.lastName_txt);
        email=findViewById(R.id.email_txt);
        pass=findViewById(R.id.pass_txt);
        confirm_pass=findViewById(R.id.confirm_pass_txt);
        btn_register=findViewById(R.id.register_Btn);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fn=firstName.getText().toString();
                String ln=lastName.getText().toString();
                String em=email.getText().toString();
                String psw=pass.getText().toString();
                String c_psw=confirm_pass.getText().toString();

                if(matchPassWord(psw,c_psw))
                {
//                    String site= String.valueOf(R.string.site);

                   // String site="http://54.175.177.16:8000";
                    String site=pc.getIp(SignUp.this);
                    site+="signUp";
                    String fields[]={"firstName","lastName","email","psw"};
                    String field_values[]={fn,ln,em,psw};
                    String params=pc.putParamsTogether(fields,field_values);
                    signIn_intent=new Intent(SignUp.this,SignIn.class);
                    new DownloadAsync(SignUp.this).execute(site,params);
                }


            }
        });


    }

    private boolean matchPassWord(String pass, String confirm_pass) {
        return pass.equalsIgnoreCase(confirm_pass);
    }

    @Override
    public void onSuccess(String result) {
        String res[]=result.split(":");
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        startActivity(signIn_intent);
    }

    @Override
    public void onFailure(String result) {
        String res[]=result.split(":");
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
    }
}
