package com.optima.plugin.repluginlib.utils;


import androidx.core.app.NotificationCompat;

import com.optima.plugin.repluginlib.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * create by wma
 * on 2020/9/1 0001
 */
public class HttpUtils {
    final String TAG = HttpUtils.class.getSimpleName();
    final String PLUGIN_FILE_LOCAL_PATH = "sdcard/Download/test";
    Callback.Cancelable cancelable;

    public void download(final String url, final String fileName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(url);
                params.setAutoResume(true);
                params.setSaveFilePath(PLUGIN_FILE_LOCAL_PATH + File.separator + fileName);
                cancelable = x.http().get(params, new Callback.ProgressCallback<File>() {
                    @Override
                    public void onSuccess(File result) {
                        Logger.d(TAG, "onSuccess: cancelable = " + cancelable);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Logger.d(TAG, "onError: cancelable = " + cancelable);
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        Logger.d(TAG, "onCancelled: cancelable = " + cancelable);
                    }

                    @Override
                    public void onFinished() {
                        Logger.d(TAG, "onFinished: cancelable = " + cancelable);
                    }

                    @Override
                    public void onWaiting() {
                        Logger.d(TAG, "onWaiting: cancelable + " + cancelable);
                    }

                    @Override
                    public void onStarted() {
                        Logger.d(TAG, "onStarted: cancelable = " + cancelable);
                        NotificationUtils utils = new NotificationUtils();
                        NotificationCompat.Builder downloadBuilder = utils.createDownloadBuilder(100, 0);
                        utils.showNotification(100, downloadBuilder.build());
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isDownloading) {
                        Logger.d(TAG, "onLoading: cancelable = " + cancelable);
                        NotificationUtils utils = new NotificationUtils();
                        NotificationCompat.Builder downloadBuilder = utils.createDownloadBuilder(100, 0);
                        utils.updateDownloadNotification(100, 100, ((int) (current * total) / 100), downloadBuilder);
                    }
                });

            }
        }).start();

    }

    public void cancel() {
        cancelable.cancel();
        Logger.d(TAG, "cancel: cancelable = " + cancelable);
    }
}
