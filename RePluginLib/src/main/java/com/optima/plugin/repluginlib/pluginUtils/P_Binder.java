package com.optima.plugin.repluginlib.pluginUtils;

import android.os.IBinder;

import com.qihoo360.replugin.RePlugin;

/**
 * create by wma
 * on 2020/8/28 0028
 * <p>
 * 宿主和插件的Binder管理类
 * <p>
 * 注意：注册的插件Binder和注册的宿主Binder都需要在同一进程中才可相互调用
 */
public class P_Binder {

    /**
     * 注册一个全局Binder，插件和宿主都可使用，不同进程也可以使用
     *
     * @param name   全局Binder的名字
     * @param binder 实现aidl接口的实现Binder类
     * @return
     */
    public static boolean registerGlobalBinder(String name, IBinder binder) {
        return RePlugin.registerGlobalBinder(name, binder);
    }

    /**
     * 注销一个全局Binder
     *
     * @param name 全局Binder的名字
     * @return
     */
    public static boolean unRegisterGlobalBinder(String name) {
        return RePlugin.unregisterGlobalBinder(name);
    }

    /**
     * 获取一个已经注册的全局Binder
     *
     * @param name 全局Binder的名字
     * @return
     */
    public static IBinder getGlobalBinder(String name) {
        return RePlugin.getGlobalBinder(name);
    }

    /**
     * 注册一个插件Binder
     *
     * @param name   Binder名字
     * @param binder 实现插件aidl接口的Binder实现类
     */
    public static void registerPluginBinder(String name, IBinder binder) {
        RePlugin.registerPluginBinder(name, binder);
    }

    /**
     * 获取插件Binder
     *
     * @param pluginName 插件名字
     * @param binderName 注册的binder名字
     */
    public static IBinder getPluginBinder(String pluginName, String binderName) {
        return RePlugin.fetchBinder(pluginName, binderName);
    }

    /**
     * 获取一个宿主的Binder
     *
     * @param name 宿主Binder名字，需要在宿主中注册的Binder里面使用
     * @return
     */
    public static IBinder getHostBinder(String name) {
        return RePlugin.fetchBinder("main", name);
    }

}
