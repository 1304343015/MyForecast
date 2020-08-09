package com.example.forecast.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forecast.R;

import java.util.List;

public class CityDeleteAdapter extends BaseAdapter {
    private List<String> cityList;
    private List<String> delList;
    private Context context;

    public CityDeleteAdapter(List<String> cityList, List<String> delList, Context context) {
        this.cityList = cityList;
        this.delList = delList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int i) {
        return cityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.item_modify,null);
            holder.iv_delete=view.findViewById(R.id.iv_delete);
            holder.cityTv=view.findViewById(R.id.tv_modify);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.cityTv.setText(cityList.get(i));
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delList.add(cityList.get(i));
                cityList.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    class ViewHolder{
        ImageView iv_delete;
        TextView cityTv;
    }
}
