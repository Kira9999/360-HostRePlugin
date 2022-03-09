package com.optima.plugin.repluginlib.databases;


import com.optima.plugin.repluginlib.Logger;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * create by wma
 * on 2020/9/28 0028
 */
public class DB_Manager implements DbManager.DbUpgradeListener, DbManager.TableCreateListener, DbManager.DbOpenListener {
	final String TAG = DB_Manager.class.getSimpleName();

	private final String DB_NAME = "host.db";
	private final int DB_VERSION = 1;

	public static DbManager db;

	private DB_Manager() {

	}

	private DbManager initDb() throws DbException {
		DbManager.DaoConfig daoConfig = new DbManager.DaoConfig();
		daoConfig.setDbName(DB_NAME);
		daoConfig.setDbVersion(DB_VERSION);
		daoConfig.setAllowTransaction(true);
		daoConfig.setTableCreateListener(this);
		daoConfig.setDbOpenListener(this);
		daoConfig.setDbUpgradeListener(this);
		return x.getDb(daoConfig);
	}

	/**
	 * 获取db
	 * @return
	 */
	public static DbManager getInstance() throws DbException {
		if (db == null) {
			db = new DB_Manager().initDb();
		}
		return db;
	}

	@Override
	public void onDbOpened(DbManager db) {
		Logger.d(TAG, "onDbOpened: ");
	}

	@Override
	public void onTableCreated(DbManager db, TableEntity<?> table) {
		Logger.d(TAG, "onTableCreated: " + table.toString());
	}

	@Override
	public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
		Logger.d(TAG, "onUpgrade: oldVersion = " + oldVersion + " newVersion = " + newVersion);
	}
}
