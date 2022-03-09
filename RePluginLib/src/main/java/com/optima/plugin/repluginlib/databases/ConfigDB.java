package com.optima.plugin.repluginlib.databases;

import android.text.TextUtils;


import com.optima.plugin.repluginlib.Logger;
import com.optima.plugin.repluginlib.module.MenuInfoModule;
import com.optima.plugin.repluginlib.module.PluginInfoModule;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wma
 * on 2020/9/25 0025
 */
public class ConfigDB {
    static final String TAG = ConfigDB.class.getSimpleName();

    // ------------------------------------------------------------------------------------------------------------------  插件配置清单  start
    public static List<PluginInfoModule> getPluginConfigList() {
        List<PluginInfoModule> plugins = new ArrayList<>();
        try {
            DbManager db = DB_Manager.getInstance();
            plugins = db.findAll(PluginInfoModule.class);
        } catch (DbException e) {
            e.printStackTrace();
            Logger.e(TAG, "插件信息获取失败: " + e.getMessage());
            return plugins;
        }
        return plugins;
    }

    public static void putPluginConfigList(List<PluginInfoModule> plugins) {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                DbManager db = DB_Manager.getInstance();
                PluginInfoModule pluginInfoModule = plugins.get(i);
                db.replace(pluginInfoModule);
            } catch (DbException e) {
                e.printStackTrace();
                Logger.e(TAG, "插件信息保存失败: " + e.getMessage());
            }
        }
    }

    public static void putPluginConfig(PluginInfoModule plugin) {
        try {
            DbManager db = DB_Manager.getInstance();
            PluginInfoModule pluginInfoModule = plugin;
            db.replace(pluginInfoModule);
        } catch (DbException e) {
            e.printStackTrace();
            Logger.e(TAG, "插件信息保存失败: " + e.getMessage());
        }
    }


    /**
     * 根据名字获取单个插件信息
     *
     * @return 查询出的插件信息， 可能为空
     */
    public static PluginInfoModule getPluginInfoByName(String name) {
        PluginInfoModule pluginInfo = null;
        try {
            DbManager db = DB_Manager.getInstance();
            pluginInfo = db.selector(PluginInfoModule.class).where("name", "=", name).findFirst();
        } catch (DbException e) {
            Logger.e(TAG, "查询插件信息失败: " + e.getMessage());
            e.printStackTrace();
        }
        return pluginInfo;
    }

    // ------------------------------------------------------------------------------------------------------------------  插件配置清单  end


    // ------------------------------------------------------------------------------------------------------------------  menu清单  start
    public static List<MenuInfoModule> getMenuConfigList() {
        List<MenuInfoModule> menus = new ArrayList<>();
        try {
            DbManager db = DB_Manager.getInstance();
            menus = db.findAll(MenuInfoModule.class);
        } catch (DbException e) {
            e.printStackTrace();
            Logger.e(TAG, "菜单信息获取失败: " + e.getMessage());
            return menus;
        }
        return menus;
    }

    public static void putMenuConfigList(List<MenuInfoModule> menus) {
        if (menus != null) {
            for (int i = 0; i < menus.size(); i++) {
                try {
                    DbManager db = DB_Manager.getInstance();
                    MenuInfoModule menuInfoModule = menus.get(i);
                    if (TextUtils.isEmpty(menuInfoModule.getId())) {
                        Logger.i(TAG, "menuInfoModule.getId() = " + menuInfoModule.getId() + " isTitle = " + menuInfoModule.isTitle() + " 不保存");
                        continue;
                    }
                    db.replace(menuInfoModule);
                } catch (DbException e) {
                    e.printStackTrace();
                    Logger.e(TAG, "菜单信息保存失败: " + e.getMessage());
                }
            }
        }
    }

    public static void putMenuConfig(MenuInfoModule menu) {
        try {
            DbManager db = DB_Manager.getInstance();
            MenuInfoModule menuInfoModule = menu;
            db.replace(menuInfoModule);
        } catch (DbException e) {
            e.printStackTrace();
            Logger.e(TAG, "菜单信息保存失败: " + e.getMessage());
        }
    }
    // ------------------------------------------------------------------------------------------------------------------  menu清单  end


    // ------------------------------------------------------------------------------------------------------------------  需要下载的  start

//    public static List<DownloadModule> getNeedDownloadList() {
//        DbManager db = DB_Manager.getInstance();
//        List<DownloadModule> downloadModules = new ArrayList<>();
//        try {
//            downloadModules = db.findAll(DownloadModule.class);
//        } catch (DbException e) {
//            e.printStackTrace();
//            Logger.e(TAG, "下载信息获取失败: " + e.getMessage());
//            return downloadModules;
//        }
//        return downloadModules;
//
//    }

//    public static void putNeedDownloadList(List<DownloadModule> downloadModules) {
//        DbManager db = DB_Manager.getInstance();
//        try {
//            if (downloadModules == null || downloadModules.size() <= 0) {
//                List<DownloadModule> all = db.findAll(DownloadModule.class);
//                for (int i = 0; i < all.size(); i++) {
//                    DownloadModule downloadModule = all.get(i);
//                    db.delete(downloadModule);
//                }
//                Logger.d(TAG, "putNeedDownloadList: 清空下载表");
//            } else {
//                for (int i = 0; i < downloadModules.size(); i++) {
//                    DownloadModule downloadModule = downloadModules.get(i);
//                    db.replace(downloadModule);
//                }
//            }
//        } catch (DbException e) {
//            e.printStackTrace();
//            Logger.e(TAG, "下载信息保存失败: " + e.getMessage());
//        }
//    }
    // ------------------------------------------------------------------------------------------------------------------  需要下载的 end


    /**
     * 清空数据库
     */
//    public static void clear() {
//        DbManager db = DB_Manager.getInstance();
//        try {
//            db.dropTable(MenuInfoModule.class);
//            db.dropTable(PluginInfoModule.class);
//            db.dropTable(DownloadModule.class);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//    }
}
