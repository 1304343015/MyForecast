package com.example.forecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.forecast.task.GetWeatherTask;

import org.json.JSONException;
import org.json.JSONObject;

public class AddCityActivity extends AppCompatActivity implements View.OnClickListener, GetWeatherTask.OnFinishListener {
    private static final String TAG = "AddCityActivity";
    private EditText et_search;
    private ImageView iv_search;
    private GridView gv_city;
    String[] hotCitys = {"北京","上海","广州","深圳","珠海","佛山","南京","苏州","厦门","长沙","成都","福州",
            "杭州","武汉","青岛","西安","太原","沈阳","重庆","天津","南宁"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        et_search=findViewById(R.id.et_search);
        iv_search=findViewById(R.id.iv_search);
        gv_city=findViewById(R.id.gv_city);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.item_hot,hotCitys);
        gv_city.setAdapter(adapter);
        setListener();
        iv_search.setOnClickListener(this);
    }
    String city;
    private void setListener() {
        gv_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                city=hotCitys[i];
                startSearch();
            }
        });
    }

    @Override
    public void onClick(View view) {
        city=et_search.getText().toString();
        startSearch();
    }

    private void startSearch(){
        if(!TextUtils.isEmpty(city)){
            GetWeatherTask task=new GetWeatherTask();
            task.setOnFinishListener(this);
            task.execute(city);
        }
    }
    @Override
    public void onFinish(String result) {
        Log.e(TAG, "onFinish: ");
        try {
            int error=new JSONObject(result).getInt("error");
            if(error==0){
                Intent intent=new Intent(AddCityActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("city",city);
                startActivity(intent);
            }else{
                Toast.makeText(this,"该城市不存在",Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError() {
        Toast.makeText(this,"网络似乎除了问题呢~",Toast.LENGTH_SHORT).show();
    }
}