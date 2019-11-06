package com.example.cruiseapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadAsync extends AsyncTask<String,Integer,String> {

    private OnEventListener<String> theCallBack;
    public DownloadAsync(OnEventListener theCallBack) {
        this.theCallBack=theCallBack;
    }

    @Override
    protected String doInBackground(String... data) {
        String site=data[0];
        String params=data[1];
        String result=getRemoteData(site,params);
        return result;
    }

    private String getRemoteData(String site, String params) {
        HttpURLConnection conn=null;
        String dataFromServer="";
        try {
            Log.d("site...", "getRemoteData: "+site);
            Log.d("params...", "getRemoteData: "+params);
            URL url=new URL(site);
            conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStreamWriter writer=new OutputStreamWriter(conn.getOutputStream());
            writer.write(params);
            writer.flush();
            writer.close();
            conn.connect();
            int status=conn.getResponseCode();
            switch (status)
            {
                case 200:
                case 201:

                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb=new StringBuilder();
                    String line="";
                    while ((line=br.readLine())!=null)
                    {
                        sb.append(line+"\n");
                    }
                    br.close();
                    dataFromServer=sb.toString();
                    break;
                case 400:
                    return "Can't find";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(conn!=null)
            {
                try {
                    conn.disconnect();
                }catch (Exception e)
                {

                }
            }
        }
        return dataFromServer;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("success...test..", "onPostExecute: "+result);
//        String res[]=result.split(":");
//        if(res[0].equalsIgnoreCase("ok"))
//        {
            theCallBack.onSuccess(result);
//        }else {
//            theCallBack.onFailure(result);
//        }
    }


}
