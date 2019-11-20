package com.example.cruiseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Review extends AppCompatActivity {

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback,et_userName;
    Spinner cruiseName_spinner;
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
                    if (mFeedback.getText().toString().isEmpty()) {
                        Toast.makeText(Review.this, "Please fill in feedback text box ", Toast.LENGTH_LONG).show();
                    } else {
                        //mFeedback.setText("");

                        mRatingBar.getRating();
                        Toast.makeText(Review.this, mFeedback.getText().toString() + " -- " +, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
