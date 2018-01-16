/*
 * Created by yeqinfu on 18-1-15 下午3:36
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 小红点数据db
 */
public class RedPointOpenHelper extends SQLiteOpenHelper {

	private static final String DBNAME = "redpoint.db";

	private static final int VERSION = 1;

	public RedPointOpenHelper(Context context) {
		super(context, DBNAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS redpointtable (id integer primary key autoincrement, downpath varchar(100), threadid INTEGER, downlength INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS redpointtable");
		onCreate(db);
	}
}
