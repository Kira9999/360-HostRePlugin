package com.optima.plugin.repluginlib.pluginUtils;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.Set;

/**
 * create by wma
 * on 2020/8/14 0014
 * <p>
 * SharedPreference 管理类
 * <p>
 * 注意：宿主和插件之间的SharedPreference是不同的对象，所以要区分数据储存在宿主还是插件
 */
public class P_SPManager {


    /**
     * 获取宿主SharedPreference，如果是单品开发，那么就是获取自己的SharedPreference
     *
     * @param spName
     * @param spMode
     * @return
     */
    private static SharedPreferences getHostSP(String spName, int spMode) {
        Context hostContext = P_Context.getHostContext();
        SharedPreferences hostSP = hostContext.getSharedPreferences(spName, spMode);
        return hostSP;
    }

    /**
     * 获取当前Context下的SharedPreference，如果在宿主中那么SharedPreference就是宿主的，如果在插件中，那么获取的SharedPreference就是插件的
     *
     * @param spName
     * @param spMode
     * @return
     */
    private static SharedPreferences getCurSP(String spName, int spMode) {
        Context context = P_Context.getContext();
        SharedPreferences curSP = context.getSharedPreferences(spName, spMode);
        return curSP;
    }

    /**
     * 获取SharedPreference对象
     *
     * @param spName
     * @param useHost
     * @return
     */
    public static SharedPreferences getSP(String spName, boolean useHost) {
        SharedPreferences sp = null;
        if (useHost) {
            sp = getHostSP(spName, Context.MODE_PRIVATE);
        } else {
            sp = getCurSP(spName, Context.MODE_PRIVATE);
        }
        return sp;
    }


    /**
     * 更具插件名字获取对应插件SharedPreference对象
     *
     * @param pluginName
     * @param spName
     * @return
     */
    public static SharedPreferences getSPByPluginName(String pluginName, String spName) {
        Context pluginContext = P_Context.getPluginContext(pluginName);
        SharedPreferences pluginSP = pluginContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return pluginSP;
    }



    /**
     * 替换目前项目里面所有获取SharedPreference对象方法
     *
     * @param spName
     * @param mode
     * @return
     */
    public static SharedPreferences getSharedPreferences(String spName, int mode) {
        SharedPreferences sp = getSP(spName, true);
        return sp;
    }

    /**
     * 替换项目获取userPreference方法
     * @param spName
     * @param mode
     * @return
     */
    public static SharedPreferences getUserPreferences(String spName, int mode) {
        SharedPreferences sp = getSP(spName, true);
        return sp;
    }

    /**
     * 替换项目获取userPreference方法
     * @return
     */
    public static SharedPreferences getUserPreferences() {
        return getSP("USER_PREFERENCES", true);
    }


    /**
     * 替换项目获取BasePreferences方法
     */
    public static SharedPreferences getBasePreferences() {
        return getSP("BASE_PREFERENCES", true);
    }

    public static SharedPreferences getConfigPreferences(){
        return getSP("CONFIG_PREFERENCE", true);
    }



    /**
     * 存储数据方法
     * @param spName SharedPreference名字
     * @param spKey 储存key
     * @param spValue 储存值
     * @param useHost 是否使用宿主 SharedPreference 对象 true:使用宿主的，false 使用自己的
     */
    public static void putString(String spName, String spKey, String spValue, boolean useHost) {
        SharedPreferences sp = getSP(spName, useHost);
        sp.edit().putString(spKey, spValue).apply();
    }

    /**
     * 获取数据方法
     * @param spName SharedPreference名字
     * @param spKey 储存key
     * @param spDefValue 获取默认值
     * @param useHost 是否使用宿主 SharedPreference 对象 true:使用宿主的，false 使用自己的
     * @return
     */
    public static String getString(String spName, String spKey, String spDefValue, boolean useHost) {
        SharedPreferences sp = getSP(spName, useHost);
        String value = sp.getString(spKey, spDefValue);
        return value;
    }


    /**
     * 存储数据方法
     * @param spName SharedPreference名字
     * @param spKey 储存key
     * @param spValue 储存值
     * @param useHost 是否使用宿主 SharedPreference 对象 true:使用宿主的，false 使用自己的
     */
    public static void putBoolean(String spName, String spKey, Boolean spValue, boolean useHost) {
        SharedPreferences sp = getSP(spName, useHost);
        sp.edit().putBoolean(spKey, spValue).apply();
    }

    /**
     * 获取数据方法
     * @param spName SharedPreference名字
     * @param spKey 储存key
     * @param spDefValue 获取默认值
     * @param useHost 是否使用宿主 SharedPreference 对象 true:使用宿主的，false 使用自己的
     * @return
     */
    public static boolean getBoolean(String spName, String spKey, Boolean spDefValue, boolean useHost) {
        SharedPreferences sp = getSP(spName, useHost);
        boolean value = sp.getBoolean(spKey, spDefValue);
        return value;
    }


    /**
     * 存储数据方法
     * @param spName SharedPreference名字
     * @param spKey 储存key
     * @param spValue 储存值
     * @param useHost 是否使用宿主 SharedPreference 对象 true:使用宿主的，false 使用自己的
     */
    public static void putInt(String spName, String spKey, int spValue, boolean useHost) {
        SharedPreferences sp = getSP(spName, useHost);
        sp.edit().putInt(spKey, spValue).apply();
    }

    /**
     * 获取数据方法
     * @param spName SharedPreference名字
     * @param spKey 储存key
     * @param spDefValue 获取默认值
     * @param useHost 是否使用宿主 SharedPreference 对象 true:使用宿主的，false 使用自己的
     * @return
     */
    public static int getInt(String spName, String spKey, int spDefValue, boolean useHost) {
        SharedPreferences sp = getSP(spName, useHost);
        int value = sp.getInt(spKey, spDefValue);
        return value;
    }

    /**
     * 存储数据方法
     * @param spName SharedPreference名字
     * @param spKey 储存key
     * @param spValue 储存值
     * @param useHost 是否使用宿主 SharedPreference 对象 true:使用宿主的，false 使用自己的
     */
    public static void putLong(String spName, String spKey, long spValue, boolean useHost) {
        SharedPreferences sp = getSP(spName, useHost);
        sp.edit().putLong(spKey, spValue).apply();
    }

    /**
     * 获取数据方法
     * @param spName SharedPreference名字
     * @param spKey 储存key
     * @param spDefValue 获取默认值
     * @param useHost 是否使用宿主 SharedPreference 对象 true:使用宿主的，false 使用自己的
     * @return
     */
    public static long getLong(String spName, String spKey, long spDefValue, boolean useHost) {
        SharedPreferences sp = getSP(spName, useHost);
        long value = sp.getLong(spKey, spDefValue);
        return value;
    }

    /**
     * 存储数据方法
     * @param spName SharedPreference名字
     * @param spKey 储存key
     * @param spValue 储存值
     * @param useHost 是否使用宿主 SharedPreference 对象 true:使用宿主的，false 使用自己的
     */
    public static void putFloat(String spName, String spKey, float spValue, boolean useHost) {
        SharedPreferences sp = getSP(spName, useHost);
        sp.edit().putFloat(spKey, spValue).apply();
    }

    /**
     * 获取数据方法
     * @param spName SharedPreference名字
     * @param spKey 储存key
     * @param spDefValue 获取默认值
     * @param useHost 是否使用宿主 SharedPreference 对象 true:使用宿主的，false 使用自己的
     * @return
     */
    public static float getFloat(String spName, String spKey, float spDefValue, boolean useHost) {
        SharedPreferences sp = getSP(spName, useHost);
        float value = sp.getFloat(spKey, spDefValue);
        return value;
    }

    /**
     * 存储数据方法
     * @param spName SharedPreference名字
     * @param spKey 储存key
     * @param spValue 储存值
     * @param useHost 是否使用宿主 SharedPreference 对象 true:使用宿主的，false 使用自己的
     */
    public static void putStringSet(String spName, String spKey, Set<String> spValue, boolean useHost) {
        SharedPreferences sp = getSP(spName, useHost);
        sp.edit().putStringSet(spKey, spValue).apply();
    }

    /**
     * 获取数据方法
     * @param spName SharedPreference名字
     * @param spKey 储存key
     * @param spDefValue 获取默认值
     * @param useHost 是否使用宿主 SharedPreference 对象 true:使用宿主的，false 使用自己的
     * @return
     */
    public static Set<String> getStringSet(String spName, String spKey, Set<String> spDefValue, boolean useHost) {
        SharedPreferences sp = getSP(spName, useHost);
        Set<String> value = sp.getStringSet(spKey, spDefValue);
        return value;
    }


}
