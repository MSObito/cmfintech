package com.example.obito.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SdCardManager {
    /**
     * 写入object
     * @param context
     * @param name
     * @param obj
     */
    public static void saveObject(Context context, String name, Object obj) {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = context.openFileOutput(name, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    // fos流关闭异常
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    // oos流关闭异常
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取object
     * @param context
     * @param name
     * @return
     */
    public static Object getObject(Context context, String name) {

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = context.openFileInput(name);
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // fis流关闭异常
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    // ois流关闭异常
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 根据文件名字删除文件
     * @param context
     * @param name
     */
    public static void deleteObject(Context context, String name){
        context.deleteFile(name);
    }

    /**
     * 获取files下所有文件
     * @param context
     * @return
     */
    public static List<Object> getAll(Context context){
        List<Object> objectList=new ArrayList<>();
        String[] files=context.fileList();
        for(int i=0;i<files.length;i++){
            objectList.add(getObject(context,files[i]));
        }
        return objectList;
    }
}


