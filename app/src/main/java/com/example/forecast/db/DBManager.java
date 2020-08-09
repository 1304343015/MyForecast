package com.example.forecast.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.forecast.bean.DbBean;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    public static SQLiteDatabase db;

    public static void initDB(Context context){
        CityInfoHelper helper=new CityInfoHelper(context);
        db=helper.getWritableDatabase();
    }

    public static long insert(String city,String content){
        ContentValues values=new ContentValues();
        values.put("city",city);
        values.put("content",content);
        return db.insert("info",null,values);
    }

    public static long update(String city,String content){
        ContentValues values=new ContentValues();
        values.put("content",content);
        return db.update("info",values,"city=?",new String[]{city});
    }

    public static String queryContent(String city){
        Cursor cursor=db.query("info",new String[]{"content"},"city=?",new String[]{city},null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            return cursor.getString(cursor.getColumnIndex("content"));
        }
        return null;
    }

    public static int getCount(){
        Cursor cursor=db.query("info",null,null,null,null,null,null);
        return cursor.getCount();
    }

    public static List<DbBean> getAllInfo(){
        Cursor cursor=db.query("info",null,null,null,null,null,null);
        List<DbBean> list=new ArrayList<>();
        while(cursor.moveToNext()){
            int _id=cursor.getInt(cursor.getColumnIndex("_id"));
            String city=cursor.getString(cursor.getColumnIndex("city"));
            String content=cursor.getString(cursor.getColumnIndex("content"));
            DbBean bean=new DbBean(_id,city,content);
            list.add(bean);
        }
        return list;
    }

    public static int delete(String city){
        return db.delete("info","city=?",new String[]{city});
    }

    public static List<String> getCityName(){
        Cursor cursor=db.query("info",new String[]{"city"},null,null,null,null,null);
        List<String> list=new ArrayList<>();
        while(cursor.moveToNext()){
            list.add(cursor.getString(cursor.getColumnIndex("city")));
        }
        return list;
    }
}
