package com.example.forecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.forecast.adapter.CityManageAdapter;
import com.example.forecast.bean.DbBean;
import com.example.forecast.db.DBManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CityManageActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lv_city;
    private List<DbBean> list;
    private CityManageAdapter adapter;
    private ImageView iv_modify;
    private FloatingActionButton btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manage);
        lv_city=findViewById(R.id.lv_city);
        list= new ArrayList<>();
        adapter=new CityManageAdapter(this,list);
        lv_city.setAdapter(adapter);
        iv_modify=findViewById(R.id.btn_modify);
        btn_add=findViewById(R.id.btn_add);
        iv_modify.setOnClickListener(this);
        btn_add.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        List<DbBean> newList=DBManager.getAllInfo();
        list.clear();
        list.addAll(newList);
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_modify:
                startActivity(new Intent(CityManageActivity.this,ModifyCityActivity.class));
                break;
            case R.id.btn_add:
                startActivity(new Intent(CityManageActivity.this,AddCityActivity.class));
                break;
        }
    }
}