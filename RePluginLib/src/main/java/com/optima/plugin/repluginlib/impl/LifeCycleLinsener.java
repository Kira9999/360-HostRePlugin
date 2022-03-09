package com.optima.plugin.repluginlib.impl;

/**
 * create by wma
 * on 2020/9/15 0015
 */
public interface LifeCycleLinsener {
    void onCreate();
    void onStart();
    void onRestart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();

}
