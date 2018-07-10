package com.example.obito.utils;

import android.content.Context;
import android.content.Intent;

public class Share {

    public void shareText(Context context,String title){
        if(title==null||title.equals(""))
            return;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, title);
        context.startActivity(intent);
    }
}
