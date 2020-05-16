package com.gamewae.iqra.sqlite;

import com.gamewae.iqra.utils.Constant;

public interface DbSchema {
	
	String DB_NAME = "com_gamewae_iqro.db";
	int DB_VERSION = 1;
	
	String TBL_CHILD = "child";	
	String COL_CHILD_CODE = "id";
	String COL_CHILD_NAME = "name";
	String COL_CHILD_CURRENT_IQRO = "currentiqro";
	
	String CREATE_TBL_CHILD = "CREATE TABLE "
								+ TBL_CHILD
								+ "(" 
									+ COL_CHILD_CODE  + " INTEGER PRIMARY KEY AUTOINCREMENT," 
									+ COL_CHILD_NAME + " TEXT,"
									+ COL_CHILD_CURRENT_IQRO + " TEXT" 
								+ ");";
										
	
	String TBL_SCORE = "score";	
	String COL_SCORE_CODE = "id";
	String COL_SCORE_IQRO_CHILD_CODE = "child_id";
	String COL_SCORE_IQRO_CODE = "iqro_id";
	String COL_SCORE_PAGE_CODE = "page_id";
	String COL_SCORE_SECTION_CODE = "section_id";
	String COL_SCORE_SCORE = "score";
	
	String CREATE_TBL_SCORE = "CREATE TABLE "
								+ TBL_SCORE
								+ "(" 
									+ COL_SCORE_CODE  + " INTEGER PRIMARY KEY AUTOINCREMENT," 
									+ COL_SCORE_IQRO_CHILD_CODE + " INTEGER," 
									+ COL_SCORE_IQRO_CODE + " INTEGER," 
									+ COL_SCORE_PAGE_CODE + " INTEGER," 
									+ COL_SCORE_SECTION_CODE + " INTEGER," 
									+ COL_SCORE_SCORE + " INTEGER" 
								+ ");";
	
	String TBL_SETTING = "settings";	
	String COL_SETTING_CODE = "code";
	String COL_SETTING_NAME = "name";
	String COL_SETTING_VALUE = "value";
			
	String CREATE_TBL_SETTING = "CREATE TABLE "
								+ TBL_SETTING 
								+ "(" 
									+ COL_SETTING_CODE  + " TEXT PRIMARY KEY," 
									+ COL_SETTING_NAME + " TEXT," 
									+ COL_SETTING_VALUE + " TEXT"
								+ ");";
	
	String INSERT_TBL_SETTING = "INSERT INTO "+TBL_SETTING+" ("+COL_SETTING_CODE+","+COL_SETTING_NAME+","+COL_SETTING_VALUE+") VALUES " +
								" ('"+Constant.SETTING_PIN+"', 'PIN','1234')";
	
	String DROP_TBL_SCORE = "DROP TABLE IF EXISTS "+ TBL_SCORE;
	String DROP_TBL_CHILD = "DROP TABLE IF EXISTS "+ TBL_CHILD;
	String DROP_TBL_SETTING = "DROP TABLE IF EXISTS "+ TBL_SETTING;
}
