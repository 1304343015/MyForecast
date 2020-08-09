package com.example.forecast.task;

import android.os.AsyncTask;

import com.example.forecast.util.HttpUtil;

import java.io.IOException;

public class GetWeatherTask extends AsyncTask<String,Void,String> {
    private static final String URL1="http://api.map.baidu.com/telematics/v3/weather?location=";
    private static final String URL2="&output=json&ak=fxKC93YpAWaOiObbrhkzYDBUSQBzWfem&mcode=BB:0D:AC:74:D3:21:E1:43:67:71:9B:62:91:AF:A1:66:6E:44:5D:75;com.baidumap.demo";
    @Override
    protected String doInBackground(String... strings) {
        String url=URL1+strings[0]+URL2;
        String resp= null;
        try {
            resp = HttpUtil.getRespData(url,"GET");
        } catch (IOException e) {
            e.printStackTrace();
            publishProgress();
            return null;
        }
        return resp;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        onFinishListener.onError();
    }

    @Override
    protected void onPostExecute(String s) {
        if(s!=null)
        onFinishListener.onFinish(s);
    }

    private OnFinishListener onFinishListener;
    public void setOnFinishListener(OnFinishListener onFinishListener){
        this.onFinishListener=onFinishListener;
    }
    public interface OnFinishListener{
        void onFinish(String result);
        void onError();
    }
}
