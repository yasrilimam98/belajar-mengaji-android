package com.gamewae.iqra.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	public DatabaseHelper(Context context) {
		super(context, DbSchema.DB_NAME, null, DbSchema.DB_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DbSchema.CREATE_TBL_CHILD);
		db.execSQL(DbSchema.CREATE_TBL_SCORE);
		db.execSQL(DbSchema.CREATE_TBL_SETTING);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(DbSchema.DROP_TBL_CHILD);
		db.execSQL(DbSchema.DROP_TBL_SCORE);
		db.execSQL(DbSchema.DROP_TBL_SETTING);
	}
}
