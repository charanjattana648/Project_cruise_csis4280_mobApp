package com.example.cruiseapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ParamConcatenation {


    public String putParamsTogether(String[] field, String[] field_values) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < field_values.length; i++) {
            try {
                field_values[i] = URLEncoder.encode(field_values[i].trim(), "UTF-8");
            } catch (UnsupportedEncodingException err) {
                err.printStackTrace();
            }
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(field[i]).append("=").append(field_values[i]);
        }
        Log.d("site...", "putParamsTogether: "+sb.toString());
        return sb.toString();
    }

    public String getIp(Context context) {

        BufferedReader reader = null;
        String address="";
        try {

            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("ipAddress.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                address=mLine;
            }

        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return address;
    }
}
