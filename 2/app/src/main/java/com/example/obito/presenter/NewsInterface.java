package com.example.obito.presenter;

import android.support.annotation.NonNull;

import com.example.obito.activity.MainActivity;
import com.example.obito.model.News;

public interface NewsInterface {
    interface View {
        //toast显示信息
        void showToast(String string);
        //设置recyclerView
        void setAdapter(News newsBean);
        //刷新adater
        void notifyAdapter();
        //停止刷新
        void stopRefresh();
    }
//    interface InvitationlModel{
//        void getData(StringCallback stringCallback);
//    }
    interface InvitationPresenter {
        //连接、断开view
        void attachView(@NonNull MainActivity View);
        void detachView();

        void getData();
        void onPositive(int position);
        void pullLoadMore();
        void getUserInfor(String userId,String inviationId);
        void deleteInvitation(int position);
        void quit();
    }
}
