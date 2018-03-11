package com.example.obito.webviewtest.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.obito.webviewtest.R;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ThirdActivity extends AppCompatActivity {

    private  TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Button button=(Button)findViewById(R.id.third_json_button);
        textView=(TextView)findViewById(R.id.json_textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithOkHttp();
            }
        });
    }
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url("http://rapapi.org/mockjsdata/32134/usr.json")
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    String str = responseData.substring(12,responseData.lastIndexOf("}"));
                    String str2 = parseJSONWithJSONObject(str);
                    showResponse(str2);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private String parseJSONWithJSONObject(String jsonData){
        String str="";
        try{
            JSONArray jsonArray=new JSONArray(jsonData);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String id=jsonObject.getString("id");
                String name=jsonObject.getString("name");
                String version=jsonObject.getString("version");
                Log.d("ThirdActivity","id is"+id);
                Log.d("ThirdActivity","name is"+name);
                Log.d("ThirdActivity","version is"+version);
                str=str+" id: "+id+" name: "+name+" version: "+version+"\n";
            }
            return str;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(response);
            }
        });
    }
}
