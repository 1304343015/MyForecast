package com.example.forecast;

import android.app.Application;

import com.example.forecast.db.DBManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.initDB(this);
    }
}
