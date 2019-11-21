package com.example.cruiseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Review extends AppCompatActivity implements OnEventListener<String>{

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback,et_userName;
    Spinner cruiseName_spinner;
    ParamConcatenation pc=new ParamConcatenation();
    ArrayAdapter<String> cruiseName_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.etFeedback);
        cruiseName_spinner =  findViewById(R.id.shipSpinner);
        et_userName = (EditText) findViewById(R.id.editText_username);
        Button mSendFeedback = (Button) findViewById(R.id.btnSubmit);

        String site=pc.getIp(Review.this);
        site+="cruiseNameList";
        new DownloadAsync(Review.this).execute(site,"");

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Very bad");
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScale.setText("");
                }

            }
        });

        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRatingBar.getRating()<=0)
                {
                    Toast.makeText(Review.this, "Please add rating!!", Toast.LENGTH_SHORT).show();
                }else {

                    String site = pc.getIp(Review.this);
                    site+="addreview";
                    String[] field={"companyName","userName","rating","comments"};
                    String[] value = {cruiseName_spinner.getSelectedItem().toString(),et_userName.getText().toString(),
                            String.valueOf(mRatingBar.getRating()),mFeedback.getText().toString()};
                    String params = pc.putParamsTogether(field, value);
                    new DownloadAsync(Review.this).execute(site, params);

                        Toast.makeText(Review.this, mFeedback.getText().toString() + " -- " +mRatingBar.getRating(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onSuccess(String result) {
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        String[] res=result.split("@:");

        switch (res[0].trim()) {
            case "CruiseNames":
               String[] data = res[1].split(",");
                setSpinnerAdapter(data, "Select CruiseName", cruiseName_spinner);
                break;
            case "ReviewAdded":
                Toast.makeText(this, "Thanks, Review Added successfully", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onFailure(String result) {

    }
    public void setSpinnerAdapter(String[] res,String title,Spinner spinner)
    {
        ArrayList<String> cruiseNamesList=new ArrayList<>(Arrays.asList(res));
//        CruiseDayList.add(0,title);
        cruiseName_adapter=new ArrayAdapter<String>(Review.this,android.R.layout.simple_list_item_1,cruiseNamesList);
        spinner.setAdapter(cruiseName_adapter);
    }
}
