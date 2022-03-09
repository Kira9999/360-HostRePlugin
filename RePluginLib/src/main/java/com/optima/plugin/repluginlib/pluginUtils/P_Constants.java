package com.optima.plugin.repluginlib.pluginUtils;

/**
 * create by wma
 * on 2020/8/17 0017
 *
 * 插件化常量类，主要储存各个插件和宿主的包名，别名等
 */
public class P_Constants {
    public static final int REQUEST_CODE = 101;

    public static final String HOST_PACKAGE_NAME = P_Context.getHostContext().getPackageName();

    public static final String ALIAS_PLUGIN_1 = "plugin3";
    public static final String PACKAGE_NAME_PLUGIN_1 = "com.example.aaaa";

//    public static final String ALIAS_PLUGIN_1 = "plugin1";
//    public static final String PACKAGE_NAME_PLUGIN_1 = "com.optima.plugin.plugin1";

    public static final String ALIAS_PLUGIN_2 = "plugin2";
    public static final String PACKAGE_NAME_PLUGIN_2 = "com.optima.plugin.plugin2";

//    public static final String ALIAS_PLUGIN_3 = "yellowduck";
//    public static final String PACKAGE_NAME_PLUGIN_3 = "com.dnneauebncdfn.dhudllueny";

    public static final String INTENT_KEY = "intent_key";
    public static final String INTENT_VALUE = "intent_value";
    public static final String ACTION_BROADCAST_RECEIVER = "com.optima.plugin.host.ACTION_BROADCAST_RECEIVER";
    public static final String HOST_PROVIDER_URI = "content://com.optima.plugin.host";
    public static final String PLUGIN1_PROVIDER_URI = "content://com.optima.plugin.plugin1";
    public static final String PLUGIN2_PROVIDER_URI = "content://com.optima.plugin.plugin2";
    public static final String INTENT_ALIAS = "INTENT_ALIAS";
    public static final String INTENT_CLASS_NAME = "INTENT_CLASS_NAME";

    // 中转 Activity 使用的常量 --------- start
    public static final String INTENT_TARGET_PLUGIN_NAME = "intent_target_plugin_name";// 目标插件的名字
    public static final String INTENT_TARGET_CLASS_NAME = "intent_target_class_name";// 目标className
    public static final String INTENT_TARGET_TYPE = "intent_target_type";// 目标类型
    public static final int TARGET_TYPE_START_ACTIVITY = 0;// 跳转Activity
    public static final int TARGET_TYPE_START_SERVICE = 1;// 开启服务
    public static final int TARGET_TYPE_SEND_BROADCAST = 2;// 发送广播
    // 中转 Activity 使用的常量 --------- end
    public static final String LOCK_SCREEN_BINDER = "LOCK_SCREEN_BINDER";// 锁屏幕

    public static final String HOST_BINDER = "HOST_BINDER";
}
