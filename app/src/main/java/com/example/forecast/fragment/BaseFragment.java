package com.example.forecast.fragment;

import androidx.fragment.app.Fragment;

import com.example.forecast.task.GetWeatherTask;

public class BaseFragment extends Fragment implements GetWeatherTask.OnFinishListener {
    protected void loadParams(String city){
        GetWeatherTask task=new GetWeatherTask();
        task.setOnFinishListener(this);
        task.execute(city);
    }
    @Override
    public void onFinish(String s) {

    }

    @Override
    public void onError() {

    }
}
