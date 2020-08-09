package com.example.forecast.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.forecast.R;
import com.example.forecast.bean.WeatherBean;
import com.example.forecast.db.DBManager;
import com.example.forecast.task.GetImageTask;
import com.google.gson.Gson;

import java.util.List;

public class MainFragment extends BaseFragment implements View.OnClickListener {
    private TextView tempTv,cityTv,weatherTv,windTv,rangeTv,timeTv;
    private ImageView weatherIv;
    private LinearLayout ll_future;
    private TextView clothTv,carTv,coldTv,sportTv,raysTv;
    private String city;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_main,container,false);
        initView(view);
        Bundle bundle=getArguments();
        city=bundle.getString("city");
        loadParams(city);
        return view;
    }

    private void initView(View view) {
        tempTv=view.findViewById(R.id.tv_main_temp);
        cityTv=view.findViewById(R.id.tv_main_city);
        weatherTv=view.findViewById(R.id.tv_main_weather);
        windTv=view.findViewById(R.id.tv_main_wind);
        rangeTv=view.findViewById(R.id.tv_main_temp_range);
        weatherIv=view.findViewById(R.id.iv_main_weather);
        timeTv=view.findViewById(R.id.tv_main_time);
        ll_future=view.findViewById(R.id.ll_main_future);
        //指数文本
        clothTv=view.findViewById(R.id.tv_main_cloth);
        carTv=view.findViewById(R.id.tv_main_car);
        coldTv=view.findViewById(R.id.tv_main_cold);
        sportTv=view.findViewById(R.id.tv_main_sport);
        raysTv=view.findViewById(R.id.tv_main_rays);
        clothTv.setOnClickListener(this);
        carTv.setOnClickListener(this);
        sportTv.setOnClickListener(this);
        coldTv.setOnClickListener(this);
        raysTv.setOnClickListener(this);
    }

    @Override
    public void onFinish(String result) {
        WeatherBean weatherBean=new Gson().fromJson(result,WeatherBean.class);
        WeatherBean.ResultsBean resultsBean=weatherBean.getResults().get(0);
        long flag=DBManager.insert(resultsBean.getCurrentCity(),result);
        if(flag<=0){
            DBManager.update(resultsBean.getCurrentCity(),result);
        }
        timeTv.setText(weatherBean.getDate());
        cityTv.setText(resultsBean.getCurrentCity());
        //设置中间提示部分
        setTipsItem(resultsBean.getIndex());
        //设置未来天气部分
        setFutureItem(resultsBean.getWeather_data());


    }

    @Override
    public void onError() {
        String result=DBManager.queryContent(city);
        WeatherBean weatherBean=new Gson().fromJson(result,WeatherBean.class);
        WeatherBean.ResultsBean resultsBean=weatherBean.getResults().get(0);
        DBManager.insert(resultsBean.getCurrentCity(),result);
        timeTv.setText(weatherBean.getDate());
        cityTv.setText(resultsBean.getCurrentCity());
        //设置中间提示部分
        setTipsItem(resultsBean.getIndex());
        //设置未来天气部分
        setFutureItem(resultsBean.getWeather_data());
    }

    private void setTipsItem(List<WeatherBean.ResultsBean.IndexBean> indexBeanList){

    }

    private void setFutureItem(List<WeatherBean.ResultsBean.WeatherDataBean> weatherDataBeanList){
        //设置今天
        WeatherBean.ResultsBean.WeatherDataBean data1=weatherDataBeanList.get(0);
        String temp=data1.getDate().split("：")[1];
        temp=temp.replace(")","");
        tempTv.setText(temp);
        weatherTv.setText(data1.getWeather());
        windTv.setText(data1.getWind());
        rangeTv.setText(data1.getTemperature());
        GetImageTask task=new GetImageTask();
        task.setOnGetImageListener(new GetImageTask.OnGetImageListener() {
            @Override
            public void getImageSuccess(Bitmap bitmap) {
                weatherIv.setImageBitmap(bitmap);
            }
        });
        task.execute(data1.getDayPictureUrl());

        //设置未来三天
        weatherDataBeanList.remove(0);
        for(int i=0;i<weatherDataBeanList.size();i++){
            View futureView=LayoutInflater.from(getActivity()).inflate(R.layout.item_main_center,null);
            futureView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            TextView item_dayTv=futureView.findViewById(R.id.item_day);
            TextView item_weatherTv=futureView.findViewById(R.id.item_weather);
            TextView item_tempTv=futureView.findViewById(R.id.item_temp);
            final ImageView imageView=futureView.findViewById(R.id.item_iv);
            //赋值
            item_dayTv.setText(weatherDataBeanList.get(i).getDate());
            item_weatherTv.setText(weatherDataBeanList.get(i).getWeather());
            item_tempTv.setText(weatherDataBeanList.get(i).getTemperature());
            GetImageTask task1=new GetImageTask();
            task1.setOnGetImageListener(new GetImageTask.OnGetImageListener() {
                @Override
                public void getImageSuccess(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            });
            task1.execute(weatherDataBeanList.get(i).getDayPictureUrl());
            ll_future.addView(futureView);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
