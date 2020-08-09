package com.example.forecast.util;

import android.graphics.drawable.Icon;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class StreamTool {

    private static byte[] readStream(InputStream is) throws IOException {
        ByteArrayOutputStream outStream=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int len=0;
        while((len=is.read(buffer))!=-1){
            outStream.write(buffer,0,len);
        }
        byte[] data=outStream.toByteArray();
        outStream.close();
        is.close();
        return data;
    }

    public static String getUnzipStream(InputStream is,String encoding,String charset){
        GZIPInputStream gzin=null;
        String content="";
        try {
        if(encoding!=null&&!encoding.equals("")){
            if(encoding.contains("gzip")){
                    gzin=new GZIPInputStream(is);
            }
        }

        if(gzin==null){
            content=new String(readStream(is),charset);
        }else{
            content=new String(readStream(gzin),charset);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
