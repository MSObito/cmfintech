package com.example.obito.utils;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
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
        for (int i = 0; i < files.length; i++) {
            objectList.add(getObject(context, files[i]));
        }
        return objectList;
    }

    public static void createFolder(Context context,String folderName){
        File file=new File(context.getFilesDir(),folderName);
        file.mkdir();
    }

    /**
     * 保存文件
     * @param context
     * @param folerName
     * @param fileName
     * @param object
     */
    public static void saveFile(Context context,String folerName,String fileName,Object object){
        File file=new File(context.getFilesDir(),folerName);
        if(!file.exists())
            file.mkdir();
        File file1=new File(file,fileName);
        FileOutputStream fileOutputStream=null;
        ObjectOutputStream objectOutputStream=null;
        if(!file1.exists()){
            try {
                file1.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try{
            fileOutputStream = new FileOutputStream(file1.toString());
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(objectOutputStream!=null){
                try{
                    objectOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件
     * @param context
     * @param folerName
     * @param fileName
     */
    public static void deleteFile(Context context,String folerName,String fileName){
        File file=new File(context.getFilesDir(),folerName);
        File file1=new File(file,fileName);
        if(file1.exists()){
            file1.delete();
        }
    }

    /**
     * 获取所有object
     * @param context
     * @param folerName
     * @return
     */
    public static List<Object> getAllFiles(Context context,String folerName){
        List<Object> objectList=new ArrayList<>();
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        File file=new File(context.getFilesDir(),folerName);
        if(!file.exists())
            return null;
        File[] files=file.listFiles();
        for (int i = 0; i < files.length; i++) {
            try {
                fileInputStream = new FileInputStream(files[i].toString());
                objectInputStream = new ObjectInputStream(fileInputStream);
                Object object = objectInputStream.readObject();
                objectList.add(object);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try{
                    if(fileInputStream!=null){
                        fileInputStream.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
                try {
                    if(objectInputStream!=null){
                        objectInputStream.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return objectList;
    }
}


