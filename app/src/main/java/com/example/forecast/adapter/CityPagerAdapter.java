package com.example.forecast.adapter;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.forecast.fragment.MainFragment;

import java.util.List;

public class CityPagerAdapter extends FragmentStatePagerAdapter {
    private List<MainFragment> fragmentList;

    public CityPagerAdapter(@NonNull FragmentManager fm,List<MainFragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    int count=0;
    @Override
    public void notifyDataSetChanged() {
        count=getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if(count>0){
            count--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
