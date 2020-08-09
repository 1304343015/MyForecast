package com.example.forecast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.forecast.adapter.CityDeleteAdapter;
import com.example.forecast.db.DBManager;

import java.util.ArrayList;
import java.util.List;

public class ModifyCityActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_cancel;
    private ImageView iv_enter;
    private List<String> cityList;
    private List<String> delList;
    private ListView lv_modify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_city);
        cityList= DBManager.getCityName();
        delList=new ArrayList<>();
        iv_cancel=findViewById(R.id.iv_cancel);
        iv_enter=findViewById(R.id.iv_enter);
        iv_cancel.setOnClickListener(this);
        iv_enter.setOnClickListener(this);
        CityDeleteAdapter adapter=new CityDeleteAdapter(cityList,delList,this);
        lv_modify=findViewById(R.id.lv_modify);
        lv_modify.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("是否放弃此次编辑？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.create().show();
                break;
            case R.id.iv_enter:
                for(int i=0;i<delList.size();i++){
                    DBManager.delete(delList.get(i));
                }
                finish();
                break;
        }
    }
}