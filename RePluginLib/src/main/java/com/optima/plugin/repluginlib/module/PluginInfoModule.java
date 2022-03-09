package com.optima.plugin.repluginlib.module;

import androidx.annotation.NonNull;


import com.optima.plugin.repluginlib.base.BaseModule;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * create by wma
 * on 2020/9/23 0023
 */
@Table(name = "plugin_info")
public class PluginInfoModule extends BaseModule {

	public static final String TYPE_SERVICE = "service";
	public static final String TYPE_ACTIVITY = "activity";
	public static final String TYPE_BROADCAST = "broadcast";

	/**
	 *
	 */
	@Column(name = "id", isId = true, autoGen = true)
	public String id;

	/**
	 * 插件别名定义
	 */
	@Column(name = "name")
	public String name;

	/**
	 * 插件版本号
	 */
	@Column(name = "version_code")
	public int versionCode;

	/**
	 * 插件版本名
	 */
	@Column(name = "version_name")
	public String versionName;

	/**
	 * 插件的APP图标
	 */
	@Column(name = "icon_url")
	public String iconUrl;

	/**
	 * 插件下载地址
	 */
	@Column(name = "download_url")
	public String downloadUrl;

	/**
	 * 服务地址类型
	 */
	@Column(name = "server_type")
	public String serverType;

	/**
	 * 服务器地址
	 */
	@Column(name = "server_url")
	public String serverUrl;

	/**
	 * 服务器ip
	 */
	@Column(name = "server_ip")
	public String serverIp;

	/**
	 * 服务器port
	 */
	@Column(name = "server_port")
	public String serverPort;

	/**
	 * 服务器名称
	 */
	@Column(name = "server_name")
	public String serverName;

	/**
	 * 闹钟唤醒
	 */
	@Column(name = "alarm_enable")
	public boolean alarmEnable;

	/**
	 * 闹钟唤醒周期
	 */
	@Column(name = "alarm_cycle")
	public int alarmCycle;

	/**
	 * 闹钟唤醒触发广播类
	 */
	@Column(name = "alarm_class")
	public String alarmClass;

	/**
	 * 是否需要宿主直接启动插件
	 */
	@Column(name = "launcher_auto")
	public boolean launcherAuto;

	/**
	 * 启动的组件类型
	 */
	@Column(name = "launcher_type")
	public String launcherType;

	/**
	 * 启动的组件class
	 */
	@Column(name = "launcher_class")
	public String launcherClass;

	/**
	 * 本地路径 宿主登录成功后，会对插件信息和menu信息进行下载，下载后的路径将储存在iconLocalPath中
	 * 插件apk路径不需要保存，下载完成后会进行安装删除的操作。
	 */
	@Column(name = "iconLocal_path")
	public String iconLocalPath;

	@NonNull
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("name : " + name + "\n");
		stringBuffer.append("versionCode : " + versionCode + "\n");
		stringBuffer.append("versionName : " + versionName + "\n");
		stringBuffer.append("serverType : " + serverType + "\n");
		stringBuffer.append("serverIp:serverPort/serverName : " + serverIp + serverPort + serverName + "\n");
		stringBuffer.append("serverUrl : " + serverUrl + "\n");
		stringBuffer.append("launcherAuto : " + launcherAuto + "\n");
		stringBuffer.append("launcherType : " + launcherType + "\n");
		stringBuffer.append("launcherClass : " + launcherClass + "\n");
		stringBuffer.append("alarmEnable : " + alarmEnable + "\n");
		stringBuffer.append("alarmClass : " + alarmClass + "\n");
		stringBuffer.append("alarmCycle : " + alarmCycle + "\n");
		stringBuffer.append("downloadUrl : " + downloadUrl + "\n");
		return stringBuffer.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public boolean isAlarmEnable() {
		return alarmEnable;
	}

	public void setAlarmEnable(boolean alarmEnable) {
		this.alarmEnable = alarmEnable;
	}

	public int getAlarmCycle() {
		return alarmCycle;
	}

	public void setAlarmCycle(int alarmCycle) {
		this.alarmCycle = alarmCycle;
	}

	public String getAlarmClass() {
		return alarmClass;
	}

	public void setAlarmClass(String alarmClass) {
		this.alarmClass = alarmClass;
	}

	public boolean isLauncherAuto() {
		return launcherAuto;
	}

	public void setLauncherAuto(boolean launcherAuto) {
		this.launcherAuto = launcherAuto;
	}

	public String getLauncherType() {
		return launcherType;
	}

	public void setLauncherType(String launcherType) {
		this.launcherType = launcherType;
	}

	public String getLauncherClass() {
		return launcherClass;
	}

	public void setLauncherClass(String launcherClass) {
		this.launcherClass = launcherClass;
	}

	public String getIconLocalPath() {
		return iconLocalPath;
	}

	public void setIconLocalPath(String iconLocalPath) {
		this.iconLocalPath = iconLocalPath;
	}
}
