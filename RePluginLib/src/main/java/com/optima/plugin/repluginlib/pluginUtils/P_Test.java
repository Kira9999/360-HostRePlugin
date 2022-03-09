package com.optima.plugin.repluginlib.pluginUtils;

import android.content.SharedPreferences;

import java.util.Set;

/**
 * create by wma
 * on 2020/8/14 0014
 */
public class P_Test {
    public static final String TEST_SP_NAME = "SharedPreference的名字";
    public static final boolean USE_HOST = true;
    public static void putString(String spKey, String spValue) {
        P_SPManager.putString(TEST_SP_NAME,spKey,spValue,USE_HOST);
    }

    public static String getString(String spKey, String spDefValue){
        SharedPreferences sp = P_SPManager.getSP(TEST_SP_NAME, USE_HOST);
        String value = sp.getString(spKey, spDefValue);
        return value;
    }


    public static void putBoolean(String spKey, Boolean spValue) {
        SharedPreferences sp = P_SPManager.getSP(TEST_SP_NAME, USE_HOST);
        sp.edit().putBoolean(spKey, spValue).apply();
    }

    public static boolean getBoolean(String spKey, Boolean spDefValue){
        SharedPreferences sp = P_SPManager.getSP(TEST_SP_NAME, USE_HOST);
        boolean value = sp.getBoolean(spKey, spDefValue);
        return value;
    }


    public static void putInt(String spKey, int spValue) {
        SharedPreferences sp = P_SPManager.getSP(TEST_SP_NAME, USE_HOST);
        sp.edit().putInt(spKey, spValue).apply();
    }

    public static int getInt(String spKey, int spDefValue){
        SharedPreferences sp = P_SPManager.getSP(TEST_SP_NAME, USE_HOST);
        int value = sp.getInt(spKey, spDefValue);
        return value;
    }


    public static void putLong(String spKey, long spValue) {
        SharedPreferences sp = P_SPManager.getSP(TEST_SP_NAME, USE_HOST);
        sp.edit().putLong(spKey, spValue).apply();
    }

    public static long getLong(String spKey, long spDefValue){
        SharedPreferences sp = P_SPManager.getSP(TEST_SP_NAME, USE_HOST);
        long value = sp.getLong(spKey, spDefValue);
        return value;
    }


    public static void putFloat(String spKey, float spValue) {
        SharedPreferences sp = P_SPManager.getSP(TEST_SP_NAME, USE_HOST);
        sp.edit().putFloat(spKey, spValue).apply();
    }

    public static float getFloat(String spKey, float spDefValue){
        SharedPreferences sp = P_SPManager.getSP(TEST_SP_NAME, USE_HOST);
        float value = sp.getFloat(spKey, spDefValue);
        return value;
    }


    public static void putStringSet(String spKey, Set<String> spValue) {
        SharedPreferences sp = P_SPManager.getSP(TEST_SP_NAME, USE_HOST);
        sp.edit().putStringSet(spKey, spValue).apply();
    }

    public static Set<String> getStringSet(String spKey, Set<String> spDefValue){
        SharedPreferences sp = P_SPManager.getSP(TEST_SP_NAME, USE_HOST);
        Set<String> value = sp.getStringSet(spKey, spDefValue);
        return value;
    }
}
