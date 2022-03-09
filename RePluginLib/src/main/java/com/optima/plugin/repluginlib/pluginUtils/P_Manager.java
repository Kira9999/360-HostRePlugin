package com.optima.plugin.repluginlib.pluginUtils;

import android.app.ActivityManager;
import android.content.Context;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;

import java.util.List;

/**
 * create by wma
 * on 2020/8/24 0024
 */
public class P_Manager {

    /**
     * 判断插件是否在运行
     *
     * @param pluginName
     * @return
     */
    public static boolean isPluginRunning(String pluginName) {
        return RePlugin.isPluginRunning(pluginName);
    }

    /**
     * 插件Dex文件是否释放
     *
     * @param pluginName
     * @return
     */
    public static boolean isPluginDexExtracted(String pluginName) {
        return RePlugin.isPluginDexExtracted(pluginName);
    }

    /**
     * 是否在某个进程里面
     *
     * @param pluginName
     * @param processName
     * @return
     */
    public static boolean isPluginRunningInProcess(String pluginName, String processName) {
        return RePlugin.isPluginRunningInProcess(pluginName, processName);
    }

    /**
     * 插件是否被安装过
     *
     * @param pluginName
     * @return
     */
    public static boolean isPluginInstalled(String pluginName) {
        return RePlugin.isPluginInstalled(pluginName);
    }

    /**
     * 插件是否被使用
     *
     * @param pluginName
     * @return
     */
    public static boolean isPluginUsed(String pluginName) {
        return RePlugin.isPluginUsed(pluginName);
    }


    /**
     * 获取插件版本
     *
     * @param pluginName
     * @return
     */
    public static int getPluginVersion(String pluginName) {
        return RePlugin.getPluginVersion(pluginName);
    }


    /**
     * 获取插件信息对象
     *
     * @param pluginName
     * @return
     */
    private static PluginInfo getPluginInfo(String pluginName) {
        return RePlugin.getPluginInfo(pluginName);
    }

    /**
     * 获取插件包名
     *
     * @param pluginName
     * @return
     */
    public static String getPluginPackageName(String pluginName) {
        PluginInfo pluginInfo = getPluginInfo(pluginName);
        return pluginInfo.getPackageName();
    }


    /**
     * 获取插件路径
     *
     * @param pluginName
     * @return
     */
    public static String getPluginPath(String pluginName) {
        PluginInfo pluginInfo = getPluginInfo(pluginName);
        return pluginInfo.getPath();
    }

    /**
     * 获取所有插件信息
     *
     * @return
     */
    public static List<PluginInfo> getPluginInfo() {
        return RePlugin.getPluginInfoList();
    }


    /**
     * 安装插件
     *
     * @param path
     * @return
     */
    public static PluginInfo install(String path) {
        return RePlugin.install(path);
    }

    /**
     * 体检释放dex文件
     *
     * @param pluginName
     * @return
     */
    public static boolean preload(String pluginName) {
        return RePlugin.preload(pluginName);
    }


    public static String getProcessName(int pid) {
        ActivityManager ams = (ActivityManager) P_Context.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : ams.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
