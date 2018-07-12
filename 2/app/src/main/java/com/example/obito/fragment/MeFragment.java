package com.example.obito.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.obito.R;
import com.example.obito.activity.AboutActivity;
import com.example.obito.activity.CollectHistoryActivity;
import com.example.obito.activity.LikedActivity;
import com.example.obito.activity.MainActivity;
import com.example.obito.activity.MessageActivity;
import com.kyleduo.switchbutton.SwitchButton;

import org.w3c.dom.Text;

import static android.content.Context.MODE_PRIVATE;

public class MeFragment extends Fragment {

    private FrameLayout messageLayout;
    private SwitchButton nightSwitch;
    private FrameLayout aboutLayout;
    private FrameLayout likedLayout;
    private TextView versionTextView;
    private LinearLayout collectHistoryLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.me_fragment,container,false);
        messageLayout=(FrameLayout)view.findViewById(R.id.messageLayout);
        nightSwitch=(SwitchButton)view.findViewById(R.id.nightSwitch);
        aboutLayout=(FrameLayout)view.findViewById(R.id.aboutLayout);
        likedLayout=(FrameLayout)view.findViewById(R.id.likedLayout);
        versionTextView=(TextView)view.findViewById(R.id.versionTextView);
        collectHistoryLayout=(LinearLayout)view.findViewById(R.id.collectAndHistoryLayout);
        SharedPreferences sf=getContext().getSharedPreferences("data", MODE_PRIVATE);
        boolean nightMode=sf.getBoolean("nightMode",false);
        nightSwitch.setChecked(nightMode);
        versionTextView.setText(getLocalVersionName(getContext()));
        aboutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), AboutActivity.class);
                startActivity(intent);
            }
        });
        messageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MessageFragment messageFragment=new MessageFragment();
//                replaceFragment(messageFragment);
                Intent intent=new Intent(getContext(), MessageActivity.class);
                startActivity(intent);
            }
        });
        likedLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), LikedActivity.class);
                startActivity(intent);
            }
        });
        collectHistoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), CollectHistoryActivity.class);
                startActivity(intent);
            }
        });
        nightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(nightSwitch.isChecked()){
                    SharedPreferences sf=getContext().getSharedPreferences("data", MODE_PRIVATE);
                    SharedPreferences.Editor editor=sf.edit();
                    editor.putBoolean("nightMode",true);
                    editor.commit();
//                    ((MainActivity)getActivity()).setEnableNightMode(true);
                }else{
                    SharedPreferences sf=getContext().getSharedPreferences("data", MODE_PRIVATE);
                    SharedPreferences.Editor editor=sf.edit();
                    editor.putBoolean("nightMode",false);
                    editor.commit();
//                    ((MainActivity)getActivity()).setEnableNightMode(false);
                }
                restartApplication();
            }
        });
        return view;
    }


    private void restartApplication() {

        final Intent intent = getContext().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);

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
