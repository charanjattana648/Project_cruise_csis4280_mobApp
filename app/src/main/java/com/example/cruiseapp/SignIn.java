package com.example.cruiseapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends AppCompatActivity implements OnEventListener<String>{

    EditText email,psw;
    Button btn_login;
    Intent mainActivity_intent;
    ParamConcatenation pc=new ParamConcatenation();
    public static final String MY_PREFS_NAME = "Myshareduserdata";

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
                String site=pc.getIp(SignIn.this);
//                String site= String.valueOf(R.string.site);
                site+="login";
                String field[]={"email","psw"};
                String field_values[]={email.getText().toString(),psw.getText().toString()};
                String params=pc.putParamsTogether(field,field_values);
                mainActivity_intent=new Intent(SignIn.this,MainActivity.class);
                new DownloadAsync(SignIn.this).execute(site,params);
            }
        });



    }

    @Override
    public void onSuccess(String result) {
        String res[]=result.split("@:");
        JSONArray jsonArray=null;
        JSONObject userObj=null;
        try {
            jsonArray=new JSONArray(res[1]);
            userObj=jsonArray.getJSONObject(0);
            Log.d("fname..", "onSuccess: "+userObj.getString("firstName"));
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("fname", userObj.getString("firstName"));
            editor.putString("lname", userObj.getString("lastName"));
            editor.putString("email", userObj.getString("email"));
            editor.apply();

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Toast.makeText(this, ""+res[2], Toast.LENGTH_SHORT).show();
        startActivity(mainActivity_intent);
    }

    @Override
    public void onFailure(String result) {
        String res[]=result.split(":");
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
    }
}
