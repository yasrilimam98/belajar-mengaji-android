package com.gamewae.iqra.sqlite.ds;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gamewae.iqra.entity.Child;
import com.gamewae.iqra.sqlite.DbSchema;

public class ChildDataSource {
	
	private SQLiteDatabase db;
	public ChildDataSource(SQLiteDatabase db)
	{
		this.db = db;
	}
	
	public long truncate()
	{
		return db.delete(DbSchema.TBL_CHILD,null,null);
	}
	
	public Child get(String code) {
		 
		Child item = new Child();
		 
		String selectQuery = " SELECT  *  FROM " + DbSchema.TBL_CHILD  + 
						       " Where " +DbSchema.COL_CHILD_CODE + " = '"+code+"'";
		
		Cursor c = db.rawQuery(selectQuery, null);
	
		if (c.moveToFirst()) {
			do {
			
				item.setId(c.getInt(c.getColumnIndex(DbSchema.COL_CHILD_CODE)));
				item.setName(c.getString(c.getColumnIndex(DbSchema.COL_CHILD_NAME)));
				item.setCurrentIqro(c.getString(c.getColumnIndex(DbSchema.COL_CHILD_CURRENT_IQRO)));
			} while (c.moveToNext());
		}
		return item;
	}
	
	public ArrayList<Child> getAll() {
		 
		ArrayList<Child> items = new ArrayList<Child>();
		
		
		String selectQuery = " SELECT  *  FROM " + DbSchema.TBL_CHILD ;
		Cursor c = db.rawQuery(selectQuery, null);
	
		if (c.moveToFirst()) {
			do {
				Child item = new Child();
				item.setId(c.getInt(c.getColumnIndex(DbSchema.COL_CHILD_CODE)));
				item.setName(c.getString(c.getColumnIndex(DbSchema.COL_CHILD_NAME)));
				item.setCurrentIqro(c.getString(c.getColumnIndex(DbSchema.COL_CHILD_CURRENT_IQRO)));
				items.add(item);
			} while (c.moveToNext());
		}
	
		return items;
	}
	
	public long insert(Child item)
	{
		ContentValues values = new ContentValues();
		values.put(DbSchema.COL_CHILD_NAME, item.getName());
		values.put(DbSchema.COL_CHILD_CURRENT_IQRO, item.getCurrentIqro());
		
		return db.insert(DbSchema.TBL_CHILD, null, values);
	}
	
	public long update(Child item,String lastCode)
	{
		ContentValues values = new ContentValues();
		values.put(DbSchema.COL_CHILD_NAME, item.getName());
		values.put(DbSchema.COL_CHILD_CURRENT_IQRO, item.getCurrentIqro());
		
		return db.update(DbSchema.TBL_CHILD, values, DbSchema.COL_CHILD_CODE+"= '"+lastCode+"' ", null);
	}
	
	public int delete(int code)
	{
		return db.delete(DbSchema.TBL_CHILD, DbSchema.COL_CHILD_CODE + "= '" + code + "'", null);
	}
	
	public boolean cekName(String name) {
		 
		boolean has = false;
		String selectQuery = " SELECT  * FROM " + DbSchema.TBL_CHILD  + 
						      " Where lower(" +DbSchema.COL_CHILD_NAME + ") = '"+name.toLowerCase()+"'";
		 
		Cursor c = db.rawQuery(selectQuery, null);
		if(c.getCount() > 0)
			has = true;
			
		return has;
	}

}
