package com.optima.plugin.repluginlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import com.optima.plugin.repluginlib.base.BaseActivity;

/**
 * create by wma
 * on 2020/9/16 0016
 */
public class VerifyVersionReceiver extends BroadcastReceiver {
    String TAG = VerifyVersionReceiver.class.getSimpleName();
    private BaseActivity activity;

    public VerifyVersionReceiver(BaseActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.d(TAG, "onReceive: " + intent.getAction());
        UpdateDialog updateDialog = new UpdateDialog(false);
        updateDialog.show(activity.getSupportFragmentManager(),"UpdateDialog");
    }
}
