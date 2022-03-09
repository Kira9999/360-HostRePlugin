package com.optima.plugin.repluginlib.pluginUtils;


import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;

import com.optima.plugin.repluginlib.Logger;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.component.provider.PluginProviderClient;
import com.qihoo360.replugin.component.service.PluginServiceClient;

/**
 * create by wma
 * on 2020/8/14 0014
 * <p>
 * Context 管理类
 */
public class P_Context {

    private static Context context;

    /**
     * 获取当前Context,需要在当前应用Application中注册
     *
     * @return
     */
    public static Context getContext() {
        if (context == null) {
            throw new RuntimeException("context = null，请在你 Application 的 onCreate 方法中调用 P_Context.setContext(this)");
        }
        return context;
    }

    public static void setContext(Context context) {
        P_Context.context = context;
    }

    /**
     * 获取宿主Context
     *
     * @return
     */
    public static Context getHostContext() {
        try {
            Context context = RePlugin.getHostContext();
            if(context == null){
                context = getContext();
            }
            return context;
        } catch (NoSuchMethodError error) {
            return getContext();
        }
    }

    /**
     * 获取宿主的 ClassLoader
     *
     * @return
     */
    public static ClassLoader getHostClassLoader() {
        return RePlugin.getHostClassLoader();
    }

    /**
     * 获取插件的 ClassLoader
     *
     * @return
     */
    public static ClassLoader getPluginClassLoader(String pluginName) {
        return RePlugin.fetchClassLoader(pluginName);
    }

    /**
     * 获取某个插件的Context;
     *
     * @param pluginName
     * @return
     */
    public static Context getPluginContext(String pluginName) {
        Context context = RePlugin.fetchContext(pluginName);
        return context;
    }


    /**
     * 跳转插件Acitivyt
     *
     * @param context 跳转Activity的当前Context
     * @param intent  可以使用{@link P_Context#createIntent(String, String)}创建，也可以自己创建
     */
    public static void startPluginActivity(Context context, Intent intent) {
        RePlugin.startActivity(context, intent);
    }


    /**
     * @param alias        插件别名，或者插件包名
     * @param allClassName 跳转组件的全路径 包名加类名
     * @return
     */
    public static Intent createIntent(String alias, String allClassName) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(alias, allClassName));
        return intent;
    }

    /**
     * 跳转插件Activity并且得到返回值
     *
     * @param activity
     * @param intent
     * @param requestCode
     */
    public static void startPluginActivityForResult(Activity activity, Intent intent, int requestCode) {
        RePlugin.startActivityForResult(activity, intent, requestCode);
    }

    /**
     * 开启插件服务
     *
     * @param context
     * @param intent
     */
    public static ComponentName startPluginService(Context context, Intent intent) {
        ComponentName componentName = PluginServiceClient.startService(context, intent);
        return componentName;
    }

    /**
     * 停止插件服务
     *
     * @param context
     * @param intent
     */
    public static boolean stopPluginService(Context context, Intent intent) {
        boolean b = PluginServiceClient.stopService(context, intent);
        return b;
    }

    /**
     * 绑定插件服务
     *
     * @param context
     * @param intent
     * @param connection
     */
    public static boolean bindPluginService(Context context, Intent intent, ServiceConnection connection) {
        boolean b = PluginServiceClient.bindService(context, intent, connection, Context.BIND_AUTO_CREATE);
        return b;
    }

    /**
     * 解绑插件服务
     *
     * @param context
     * @param connection
     */
    public static void unBindPluginService(Context context, ServiceConnection connection) {
        PluginServiceClient.unbindService(context, connection);
    }


    // ===========================================================================  ContentProvider 操作开始
    // 注意：ContentProvider 操作中，使用谁的ContentProvider就需要谁的Context,类似SharedPreference

    /**
     * 宿主调用插件ContentProvider 插入
     *
     * @param hostContext
     * @param uri
     * @param values
     * @return
     */
    public static Uri insertToHost(Context hostContext, Uri uri, ContentValues values) {
        Uri insert = PluginProviderClient.insert(hostContext, uri, values);
        return insert;
    }

    /**
     * 宿主调用插件ContentProvider 删除
     *
     * @param hostContext
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    public static int deleteToHost(Context hostContext, Uri uri, String selection, String[] selectionArgs) {
        int delete = PluginProviderClient.delete(hostContext, uri, selection, selectionArgs);
        return delete;
    }

    /**
     * 宿主调用插件ContentProvider 修改
     *
     * @param hostContext
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    public static int updateToHost(Context hostContext, Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int update = PluginProviderClient.update(hostContext, uri, values, selection, selectionArgs);
        return update;
    }

    /**
     * 宿主调用插件ContentProvider 查询
     *
     * @param hostContext
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @param cancellationSignal
     * @return
     */
    public static Cursor queryToHost(Context hostContext, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) {
        Cursor query = PluginProviderClient.query(hostContext, uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
        return query;
    }

    /**
     * 宿主调用插件ContentProvider 查询
     *
     * @param hostContext
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    public static Cursor queryToHost(Context hostContext, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor query = PluginProviderClient.query(hostContext, uri, projection, selection, selectionArgs, sortOrder);
        return query;
    }


    /**
     * 宿主调用插件ContentProvider 插入
     *
     * @param pluginName
     * @param uri
     * @param values
     * @return
     */
    public static Uri insert(String pluginName, Uri uri, ContentValues values) {
        Context context = RePlugin.fetchContext(pluginName);
        Uri insert = PluginProviderClient.insert(context, uri, values);
        return insert;
    }

    /**
     * 宿主调用插件ContentProvider 删除
     *
     * @param pluginName
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    public static int delete(String pluginName, Uri uri, String selection, String[] selectionArgs) {
        Context context = RePlugin.fetchContext(pluginName);
        int delete = PluginProviderClient.delete(context, uri, selection, selectionArgs);
        return delete;
    }

    /**
     * 宿主调用插件ContentProvider 修改
     *
     * @param pluginName
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    public static int update(String pluginName, Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Context context = RePlugin.fetchContext(pluginName);
        int update = PluginProviderClient.update(context, uri, values, selection, selectionArgs);
        return update;
    }

    /**
     * 宿主调用插件ContentProvider 查询
     *
     * @param pluginName
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @param cancellationSignal
     * @return
     */
    public static Cursor query(String pluginName, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) {
        Context context = RePlugin.fetchContext(pluginName);
        Cursor query = PluginProviderClient.query(context, uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
        return query;
    }

    /**
     * 宿主调用插件ContentProvider 查询
     *
     * @param pluginName
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    public static Cursor query(String pluginName, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Context context = RePlugin.fetchContext(pluginName);
        Cursor query = PluginProviderClient.query(context, uri, projection, selection, selectionArgs, sortOrder);
        return query;
    }
    // ===========================================================================  ContentProvider 结束


}
