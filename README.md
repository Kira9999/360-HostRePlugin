# 360-RePlugin

RePlugin，360开源的全面插件化框架，按照官网说的，其目的是“尽可能多的让模块变成插件”，并在很稳定的前提下，尽可能像开发普通App那样灵活。那么下面就让我们一起深入♂了解它吧。

## 介绍

RePlugin对比其他插件化，它的强大和特色，在于它只Hook住了ClassLoader。One Hook这个坚持，最大程度保证了稳定性、兼容性和可维护性，详见《全面插件化——RePlugin的使命》。当然，One Hook也极大的提高了实现复杂程度性，其中主要体现在：

1.增加了Gradle插件脚本，实现开发中自动代码修改与生成。
2.分割了插件库和宿主库的代码实现。
3.代码中存在很多不少@deprecated、TODO和临时修改。
4.初始化、加载、启动等逻辑比较复杂。

## ！！！步驟！！！

第 1 步：添加 RePlugin Host Gradle 依赖
在项目根目录的 build.gradle（注意：不是 app/build.gradle） 中添加 replugin-host-gradle 依赖：

buildscript {
dependencies {
classpath 'com.qihoo360.replugin:replugin-host-gradle:2.2.4'
...
}
}
第 2 步：添加 RePlugin Host Library 依赖
在 app/build.gradle 中应用 replugin-host-gradle 插件，并添加 replugin-host-lib 依赖:

android {
// ATTENTION!!! Must CONFIG this to accord with Gradle's standard, and avoid some error
defaultConfig {
applicationId "com.qihoo360.replugin.sample.host"
...
}
...
}

// ATTENTION!!! Must be PLACED AFTER "android{}" to read the applicationId
apply plugin: 'replugin-host-gradle'

/**
* 配置项均为可选配置，默认无需添加
* 更多可选配置项参见replugin-host-gradle的RepluginConfig类
* 可更改配置项参见 自动生成RePluginHostConfig.java
  */
  repluginHostConfig {
  /**
    * 是否使用 AppCompat 库
    * 不需要个性化配置时，无需添加
      */
      useAppCompat = true
      /**
    * 背景不透明的坑的数量
    * 不需要个性化配置时，无需添加
      */
      countNotTranslucentStandard = 6
      countNotTranslucentSingleTop = 2
      countNotTranslucentSingleTask = 3
      countNotTranslucentSingleInstance = 2
      }

dependencies {
compile 'com.qihoo360.replugin:replugin-host-lib:2.2.4'
...
}
务必注意
以下请务必注意：

请一定要确保符合Gradle开发规范，也即“必须将包名写在applicatonId”，而非AndroidManifest.xml中（通常从Eclipse迁移过来的项目可能出现此问题）。如果不这么写，则有可能导致运行时出现“Failed to find provider info for com.ss.android.auto.loader.p.main”的问题。具体可参见 #87 Issue的问答。
请将apply plugin: 'replugin-host-gradle'放在 android{} 块之后，防止出现无法读取applicationId，导致生成的坑位出现异常
如果您的应用需要支持AppComat，则除了在主程序中引入AppComat-v7包以外，还需要在宿主的build.gradle中添加下面的代码若不支持AppComat则请不要设置此项：
repluginHostConfig {
useAppCompat = true
}
开启useAppCompat后，我们会在编译期生成AppCompat专用坑位，这样插件若使用AppCompat的Theme时就能生效了。若不设置，则可能会出现“IllegalStateException: You need to use a Theme.AppCompat theme (or descendant) with this activity.”的异常。

如果您的应用需要个性化配置坑位数量，则需要在宿主的build.gradle中添加下面的代码：
repluginHostConfig {
/**
* 背景不透明的坑的数量
*/
countNotTranslucentStandard = 6
countNotTranslucentSingleTop = 2
countNotTranslucentSingleTask = 3
countNotTranslucentSingleInstance = 2
}
更多可选配置项参见replugin-host-gradle的RepluginConfig类

第 3 步：配置 Application 类
让工程的 Application 直接继承自 RePluginApplication。

如果您的工程已有Application类，则可以将基类切换到RePluginApplication即可。或者您也可以用“非继承式”接入。

public class MainApplication extends RePluginApplication {
}
既然声明了Application，自然还需要在AndroidManifest中配置这个Application。

    <application
        android:name=".MainApplication"
        ... />
备选：“非继承式”配置Application
若您的应用对Application类继承关系的修改有限制，或想自定义RePlugin加载过程（慎用！），则可以直接调用相关方法来使用RePlugin。

public class MainApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        RePlugin.App.attachBaseContext(this);
        ....
    }

    @Override
    public void onCreate() {
        super.onCreate();
        
        RePlugin.App.onCreate();
        ....
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        /* Not need to be called if your application's minSdkVersion > = 14 */
        RePlugin.App.onLowMemory();
        ....
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

        /* Not need to be called if your application's minSdkVersion > = 14 */
        RePlugin.App.onTrimMemory(level);
        ....
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);

        /* Not need to be called if your application's minSdkVersion > = 14 */
        RePlugin.App.onConfigurationChanged(config);
        ....
    }
}
针对“非继承式”的注意点
所有方法必须在UI线程来“同步”调用。切勿放到工作线程，或者通过post方法来执行
所有方法必须一一对应，例如 RePlugin.App.attachBaseContext 方法只在Application.attachBaseContext中调用
请将RePlugin.App的调用方法，放在“仅次于super.xxx()”方法的后面