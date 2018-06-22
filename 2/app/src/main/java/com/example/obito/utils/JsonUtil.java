package com.example.obito.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class   JsonUtil {

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
    public static <T> List<T> parseJSONWithGson(String jsonData,Class<T> tClass){
        List<T> list=new ArrayList<T>();
        Gson gson=new Gson();
        //return gson.fromJson(jsonData,new TypeToken<ArrayList<T>>(){}.getType());
        JsonArray arry = new JsonParser().parse(jsonData).getAsJsonArray();
        for (JsonElement jsonElement : arry) {
            list.add(gson.fromJson(jsonElement, tClass));
        }
        return list;
    }

    /**
     * 根据num,解析num+5个Json，返回实体
     * @param jsonData
     * @param <T>
     * @return
     */
    public static <T> List<T> parseJSONWithGson(String jsonData,Class<T> tClass,int num){
        List<T> list=new ArrayList<T>();
        Gson gson=new Gson();
        //return gson.fromJson(jsonData,new TypeToken<ArrayList<T>>(){}.getType());
        JsonArray arry = new JsonParser().parse(jsonData).getAsJsonArray();
        for(int i=num;i<num+5;i++){
            list.add(gson.fromJson(arry.get(i),tClass));
        }
        return list;
    }
}
