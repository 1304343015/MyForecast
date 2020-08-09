package com.example.forecast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.forecast.adapter.CityPagerAdapter;
import com.example.forecast.db.DBManager;
import com.example.forecast.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager vp_city;
    private List<MainFragment> fragmentList;
    private List<String> cityList;
    private List<ImageView> imageViewList;
    private LinearLayout ll_group;
    private ImageView iv_add,iv_more;
    private CityPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_add=findViewById(R.id.iv__main_add);
        iv_more=findViewById(R.id.iv_main_more);
        iv_add.setOnClickListener(this);
        iv_more.setOnClickListener(this);
        fragmentList=new ArrayList<>();
        cityList= DBManager.getCityName();
        imageViewList=new ArrayList<>();
        Intent intent=getIntent();
        String city=intent.getStringExtra("city");
        if(!TextUtils.isEmpty(city)){

            cityList.add(city);
        }
        if(cityList.size()==0){
            cityList.add("泉州");
            cityList.add("邯郸");
        }
        vp_city=findViewById(R.id.vp_main);
        initPager();
        adapter=new CityPagerAdapter(getSupportFragmentManager(),fragmentList);
        vp_city.setAdapter(adapter);
        vp_city.addOnPageChangeListener(this);
        ll_group=findViewById(R.id.ll_main_group);
        initImage();

    }

    private void initPager(){

        for(int i=0;i<cityList.size();i++){
            MainFragment fragment=new MainFragment();
            Bundle bundle=new Bundle();
            bundle.putString("city",cityList.get(i));
            Log.e("22", "initPager: "+cityList.get(i) );
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
        Log.e("33", "initPager: "+fragmentList.size() );

    }

    private void initImage(){

        for(int i=0;i<cityList.size();i++){
            ImageView iv=new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10,0,10,0);
            iv.setLayoutParams(layoutParams);
            iv.setImageDrawable(getDrawable(R.drawable.a3));
            ll_group.addView(iv);
            imageViewList.add(iv);
        }
        imageViewList.get(0).setImageDrawable(getDrawable(R.drawable.a2));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i=0;i<imageViewList.size();i++){
            imageViewList.get(i).setImageDrawable(getDrawable(R.drawable.a3));
        }
        imageViewList.get(position).setImageDrawable(getDrawable(R.drawable.a2));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv__main_add:
                startActivity(new Intent(MainActivity.this,CityManageActivity.class));
                break;
            case R.id.iv_main_more:

                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        List<String> list=DBManager.getCityName();
        Log.e("11", "onRestart: "+list.toString() );
        if(list.size()==0)
            list.add("泉州");
        cityList.clear();
        cityList.addAll(list);
        fragmentList.clear();
        initPager();
        adapter.notifyDataSetChanged();
        ll_group.removeAllViews();
        imageViewList.clear();
        initImage();
        vp_city.setCurrentItem(0);
    }
}