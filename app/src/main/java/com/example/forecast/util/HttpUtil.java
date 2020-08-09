package com.example.forecast.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpUtil {

    private static void setConnHeader(HttpURLConnection conn,String method) throws ProtocolException {
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(10000);
        conn.setRequestMethod(method);
        conn.setRequestProperty("Accept","/*/");
        conn.setRequestProperty("Accept-Language","zh-cn");
        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
        conn.setRequestProperty("Accept-Encoding","gzip,deflate");

    }
    public static String getRespData(String urlStr,String method) throws IOException {
        StringBuilder sb=new StringBuilder();
            URL url=new URL(urlStr);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            setConnHeader(conn,method);
            conn.connect();
            sb.append(StreamTool.getUnzipStream(conn.getInputStream(),conn.getHeaderField("Content-Encoding"),"utf-8"));
        return sb.toString();
    }


    public static Bitmap getImage(String urlStr,String method) throws IOException {
        URL url=new URL(urlStr);
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        setConnHeader(conn,method);
        conn.connect();
        Bitmap bitmap= BitmapFactory.decodeStream(conn.getInputStream());
        return bitmap;
    }

}
