package com.example.cruiseapp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ParamConcatenation {
    public String putParamsTogether(String[] field, String[] field_values) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<field_values.length;i++)
        {
            try{
                field_values[i]= URLEncoder.encode(field_values[i],"UTF-8");
            }catch (UnsupportedEncodingException err)
            {
                err.printStackTrace();
            }
            if(sb.length()>0)
            {
                sb.append("&");
            }
            sb.append(field[i]).append("=").append(field_values[i]);
        }
        return sb.toString();
    }
}
