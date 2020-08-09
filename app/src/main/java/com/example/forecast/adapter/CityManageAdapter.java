package com.example.forecast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.forecast.R;
import com.example.forecast.bean.DbBean;
import com.example.forecast.bean.WeatherBean;
import com.google.gson.Gson;

import java.util.List;

public class CityManageAdapter extends BaseAdapter {
    private List<DbBean> list;
    private Context context;
    public CityManageAdapter(Context context,List<DbBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.item_city,null);
            holder.cityTv=view.findViewById(R.id.tv_manage_city);
            holder.tempTv=view.findViewById(R.id.tv_manage_temp);
            holder.weatherTv=view.findViewById(R.id.tv_manage_weather);
            holder.windTv=view.findViewById(R.id.tv_manage_wind);
            holder.rangeTv=view.findViewById(R.id.tv_manage_range);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.cityTv.setText(list.get(i).getCity());
        WeatherBean bean=new Gson().fromJson(list.get(i).getContent(),WeatherBean.class);
        WeatherBean.ResultsBean.WeatherDataBean data=bean.getResults().get(0).getWeather_data().get(0);
        String temp=data.getDate().split("：")[1];
        temp=temp.replace(")","");
        holder.tempTv.setText(temp);
        holder.weatherTv.setText("天气："+data.getWeather());
        holder.windTv.setText(data.getWind());
        holder.rangeTv.setText(data.getTemperature());
        return view;
    }


    class ViewHolder{
        TextView cityTv;
        TextView tempTv;
        TextView weatherTv;
        TextView windTv;
        TextView rangeTv;
    }
}
