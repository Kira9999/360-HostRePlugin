package com.optima.plugin.repluginlib.pluginUtils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.optima.plugin.repluginlib.Logger;
import com.qihoo360.replugin.model.PluginInfo;
import com.qihoo360.replugin.utils.FileUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * create by wma
 * on 2020/8/17 0017
 * 插件文件工具类，之后会有插件的更新下载操作放在这里面
 */
public class P_FileUtil {
    static String TAG = P_FileUtil.class.getSimpleName();

    /**
     * 下载图标的文件夹名字
     */
    public static final String ICON_FOLDER = "icon";// 图标存放文件夹

    /**
     * 下载插件的文件夹名字
     */
    public static final String PLUGIN_FOLDER = "plugin";// 插件存放文件夹

    /**
     * 下载插件的文件夹名字
     */
    public static final String HOST_FOLDER = "host";// 宿主存放文件夹

    /**
     * 判断下载的某类型文件是否存在
     *
     * @param type 图标 {@link P_FileUtil#ICON_FOLDER},
     *             插件 {@link P_FileUtil#PLUGIN_FOLDER},
     *             宿主 {@link P_FileUtil#HOST_FOLDER}
     * @param name
     * @return
     */
    public static boolean isFileExists(String type, String name) {
        String path = P_Context.getContext().getExternalFilesDir(type).getAbsolutePath() + File.separator + name;
        File file = new File(path);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 模拟安装或升级（覆盖安装）外置插件
     * 注意：为方便演示，外置插件临时放置到Host的assets/external目录下，具体说明见README</p>
     */
    public static void simulateInstallExternalPlugin(List<String> fileNames) {
        PluginInfo info = null;
        for (int i = 0; i < fileNames.size(); i++) {
            String fileName = fileNames.get(i);
            String filePath = "external" + File.separator + fileName;
            // 文件是否已经存在？直接删除重来
            String pluginFilePath = P_Context.getHostContext().getFilesDir().getAbsolutePath() + File.separator + fileName;
            File pluginFile = new File(pluginFilePath);
            if (pluginFile.exists()) {
                FileUtils.deleteQuietly(pluginFile);
            }

            // 开始复制
            copyAssetsFileToAppFiles(filePath, fileName);

            if (pluginFile.exists()) {
                info = P_Manager.install(pluginFilePath);
            }

            if (info != null) {
                Logger.d("WMA-WMA", "simulateInstallExternalPlugin: 插件加载成功： " + info.getName());
                Logger.d("WMA-WMA", "simulateInstallExternalPlugin: 插件加载成功： " + info.getPath());

//                parseXML(info);

            } else {
                Logger.d("WMA-WMA", "simulateInstallExternalPlugin: 插件加失败： ");
            }
        }

    }

    /**
     * 从assets目录中复制某文件内容
     *
     * @param assetFileName assets目录下的Apk源文件路径
     * @param newFileName   复制到/data/data/package_name/files/目录下文件名
     */
    private static void copyAssetsFileToAppFiles(String assetFileName, String newFileName) {
        InputStream is = null;
        FileOutputStream fos = null;
        int buffsize = 1024;

        try {
            is = P_Context.getHostContext().getAssets().open(assetFileName);
            fos = P_Context.getHostContext().openFileOutput(newFileName, Context.MODE_PRIVATE);
            int byteCount = 0;
            byte[] buffer = new byte[buffsize];
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
