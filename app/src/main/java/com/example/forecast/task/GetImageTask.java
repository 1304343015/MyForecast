package com.example.forecast.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.forecast.util.HttpUtil;

import java.io.IOException;

public class GetImageTask extends AsyncTask<String,Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap=null;
        try {
            bitmap=HttpUtil.getImage(strings[0],"GET");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        onGetImageListener.getImageSuccess(bitmap);
    }
    private OnGetImageListener onGetImageListener;
    public void setOnGetImageListener(OnGetImageListener onGetImageListener){
        this.onGetImageListener=onGetImageListener;
    }
    public interface OnGetImageListener{
        void getImageSuccess(Bitmap bitmap);
    }

}
