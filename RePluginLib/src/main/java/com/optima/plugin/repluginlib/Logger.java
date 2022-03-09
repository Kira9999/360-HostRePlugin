package com.optima.plugin.repluginlib;

import android.util.Log;

/**
 * create by wma
 * on 2020/6/2 0002
 */
public class Logger {
    private static final String TAG = "WMA-WMA";

    public static void d(String tag, String msg) {
        Log.d(TAG, tag + " : " + msg);
    }

    public static void v(String tag, String msg) {
        Log.v(TAG, tag + " : " + msg);
    }

    public static void i(String tag, String msg) {
        Log.i(TAG, tag + " : " + msg);
    }

    public static void e(String tag, String msg) {
        Log.e(TAG, tag + " : " + msg);
    }

    public static void wtf(String tag, String msg) {
        Log.wtf(TAG, tag + " : " + msg);
    }

    public static void line(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }
}
