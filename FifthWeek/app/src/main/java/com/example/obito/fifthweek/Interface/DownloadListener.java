package com.example.obito.fifthweek.Interface;

/**
 * Created by obito on 2018/3/17.
 */

public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPuased();

    void onCanceled();
}
