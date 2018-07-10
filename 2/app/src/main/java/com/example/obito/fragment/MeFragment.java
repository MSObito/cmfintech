package com.example.obito.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.obito.R;
import com.example.obito.activity.AboutActivity;
import com.kyleduo.switchbutton.SwitchButton;

import org.w3c.dom.Text;

public class MeFragment extends Fragment {

    private FrameLayout messageLayout;
    private SwitchButton nightSwitch;
    private FrameLayout aboutLayout;
    private FrameLayout likedLayout;
    private TextView versionTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.me_fragment,container,false);
        messageLayout=(FrameLayout)view.findViewById(R.id.messageLayout);
        nightSwitch=(SwitchButton)view.findViewById(R.id.nightSwitch);
        aboutLayout=(FrameLayout)view.findViewById(R.id.aboutLayout);
        likedLayout=(FrameLayout)view.findViewById(R.id.likedLayout);
        versionTextView=(TextView)view.findViewById(R.id.versionTextView);
        versionTextView.setText(getLocalVersionName(getContext()));
        aboutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), AboutActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    /**
     * 获取本地软件版本号名称
     */
    private static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
}
