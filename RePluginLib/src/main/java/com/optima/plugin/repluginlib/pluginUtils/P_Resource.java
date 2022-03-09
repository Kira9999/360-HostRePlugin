package com.optima.plugin.repluginlib.pluginUtils;

import android.view.View;
import android.view.ViewGroup;

import com.qihoo360.replugin.RePlugin;

/**
 * create by wma
 * on 2020/9/7 0007
 */
public class P_Resource {

    public static View fetchLayoutByName(String pluginName, String layoutName, ViewGroup viewRoot) {
        return RePlugin.fetchViewByLayoutName(pluginName, layoutName, viewRoot);
    }
}
