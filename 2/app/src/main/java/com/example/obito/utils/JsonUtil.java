package com.example.obito.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class JsonUtil {

    /**
     * 从Assets中读取JSON文件
     * @param fileName
     * @param context
     * @return
     */
    public static String getJson(String fileName,Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 解析Json，返回实体
     * @param jsonData
     * @param <T>
     * @return
     */
    public static <T> List<T> parseJSONWithGson(String jsonData){
        Gson gson=new Gson();
        List<T> tList=gson.fromJson(jsonData,new TypeToken<List<T>>(){}.getType());
        return tList;
    }
}
