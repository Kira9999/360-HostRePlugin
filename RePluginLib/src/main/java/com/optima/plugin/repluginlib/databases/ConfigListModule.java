package com.optima.plugin.repluginlib.databases;


import com.optima.plugin.repluginlib.base.BaseModule;
import com.optima.plugin.repluginlib.module.MenuInfoModule;
import com.optima.plugin.repluginlib.module.PluginInfoModule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by wma
 * on 2020/9/25 0025
 */
public class ConfigListModule extends BaseModule {
    private final String GET_CONFIG_LIST_API = "v100/device/get/config/info";

    private List<PluginInfoModule> plugins;
    private List<MenuInfoModule> menus;

    public List<PluginInfoModule> getPlugins() {
        return plugins;
    }

    public void setPlugins(List<PluginInfoModule> plugins) {
        this.plugins = plugins;
    }

    public List<MenuInfoModule> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuInfoModule> menus) {
        this.menus = menus;
    }

//    public void getConfigList(OptimaHttpUtils.OnOptimaHttpUtilsListener listener) {
//        OptimaHttpUtils httpUtils = new OptimaHttpUtils("http://192.168.0.242:8084/", listener); // TODO: 2020/9/25 这里是调试用的url，后期需要修改为整数动态配置的地址
//        httpUtils.setAutoVerify(false);
//        Map<String, String> bodyParameterMap = new HashMap<>();
//        bodyParameterMap.put("userId", UserSP.getUserId());
//        bodyParameterMap.put("deviceType", "phone");
//        cancelable = httpUtils.requestGet(GET_CONFIG_LIST_API, GET_CONFIG_LIST_API, bodyParameterMap);
//    }
}
