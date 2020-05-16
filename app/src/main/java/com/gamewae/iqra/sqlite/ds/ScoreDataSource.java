package com.gamewae.iqra.sqlite.ds;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gamewae.iqra.entity.Score;
import com.gamewae.iqra.entity.section;
import com.gamewae.iqra.sqlite.DbSchema;

public class ScoreDataSource {
	
	private SQLiteDatabase db;
	public ScoreDataSource(SQLiteDatabase db)
	{
		this.db = db;
	}
	
	public long truncate()
	{
		return db.delete(DbSchema.TBL_SCORE,null,null);
	}
	
	
	public ArrayList<Score> getAll(String code,String iqrocode) {
		ArrayList<Score> items = new ArrayList<Score>();
		
		String whereClause = " Where " +DbSchema.COL_SCORE_CODE + " = '"+code+"'";
		if(iqrocode != null)
			whereClause += " and s."+DbSchema.COL_SCORE_IQRO_CODE + " = "+iqrocode; 
			
		String selectQuery = " SELECT  s.*,c."+DbSchema.COL_CHILD_NAME+"  FROM " + DbSchema.TBL_SCORE  + " s " + 
							 " LEFT JOIN "+DbSchema.TBL_CHILD+" c on c."+DbSchema.COL_CHILD_CODE + " = s." +DbSchema.COL_SCORE_IQRO_CHILD_CODE + 
							 whereClause;
						     
		
		Cursor c = db.rawQuery(selectQuery, null);
	
		if (c.moveToFirst()) {
			do {
				Score item = new Score();
				item.setId(c.getInt(c.getColumnIndex(DbSchema.COL_SCORE_CODE)));
				item.setChildId(c.getInt(c.getColumnIndex(DbSchema.COL_SCORE_IQRO_CHILD_CODE)));
				item.setChildName(c.getString(c.getColumnIndex(DbSchema.COL_CHILD_NAME)));
				item.setIqroId(c.getInt(c.getColumnIndex(DbSchema.COL_SCORE_IQRO_CODE)));
				item.setPagesId(c.getInt(c.getColumnIndex(DbSchema.COL_SCORE_PAGE_CODE)));
				item.setSectionId(c.getInt(c.getColumnIndex(DbSchema.COL_SCORE_SECTION_CODE)));
				item.setScore(c.getInt(c.getColumnIndex(DbSchema.COL_SCORE_SCORE)));
				items.add(item);
			} while (c.moveToNext());
		}
		return items;
	}
	
	public Score get(Score sc) {
		
		String whereClause = " Where " +DbSchema.COL_SCORE_IQRO_CODE+" = '"+sc.getIqroId()+"' and "+DbSchema.COL_SCORE_PAGE_CODE+" = '"+sc.getPagesId()+"' and " +DbSchema.COL_SCORE_SECTION_CODE+" = '"+sc.getSectionId()+"' and " +DbSchema.COL_SCORE_IQRO_CHILD_CODE+" = '"+sc.getChildId()+"'";
			
		String selectQuery = " SELECT  s.*,c."+DbSchema.COL_CHILD_NAME+"  FROM " + DbSchema.TBL_SCORE  + " s " + 
				 			" LEFT JOIN "+DbSchema.TBL_CHILD+" c on c."+DbSchema.COL_CHILD_CODE + " = s." +DbSchema.COL_SCORE_IQRO_CHILD_CODE + 
							 whereClause;
		Score item = new Score();
		Cursor c = db.rawQuery(selectQuery, null);
	
		if (c.moveToFirst()) {
			do {
				
				item.setId(c.getInt(c.getColumnIndex(DbSchema.COL_SCORE_CODE)));
				item.setChildId(c.getInt(c.getColumnIndex(DbSchema.COL_SCORE_IQRO_CHILD_CODE)));
				item.setChildName(c.getString(c.getColumnIndex(DbSchema.COL_CHILD_NAME)));
				item.setIqroId(c.getInt(c.getColumnIndex(DbSchema.COL_SCORE_IQRO_CODE)));
				item.setPagesId(c.getInt(c.getColumnIndex(DbSchema.COL_SCORE_PAGE_CODE)));
				item.setSectionId(c.getInt(c.getColumnIndex(DbSchema.COL_SCORE_SECTION_CODE)));
				item.setScore(c.getInt(c.getColumnIndex(DbSchema.COL_SCORE_SCORE)));
				
			} while (c.moveToNext());
		}
		return item;
	}
	
	public ArrayList<Score> getAll(String code) {
		 return this.getAll(code, null);
	}
	
	
	public long insert(Score item)
	{
		ContentValues values = new ContentValues();
		
		values.put(DbSchema.COL_SCORE_IQRO_CHILD_CODE, item.getChildId());
		values.put(DbSchema.COL_SCORE_IQRO_CODE, item.getIqroId());
		values.put(DbSchema.COL_SCORE_PAGE_CODE, item.getPagesId());
		values.put(DbSchema.COL_SCORE_SECTION_CODE, item.getSectionId());
		values.put(DbSchema.COL_SCORE_SCORE, item.getScore());
		
		return db.insert(DbSchema.TBL_SCORE, null, values);
	}
	
	public long update(Score item)
	{
		ContentValues values = new ContentValues();
		values.put(DbSchema.COL_SCORE_IQRO_CHILD_CODE, item.getChildId());
		values.put(DbSchema.COL_SCORE_IQRO_CODE, item.getIqroId());
		values.put(DbSchema.COL_SCORE_PAGE_CODE, item.getPagesId());
		values.put(DbSchema.COL_SCORE_SECTION_CODE, item.getSectionId());
		values.put(DbSchema.COL_SCORE_SCORE, item.getScore());
		
		return db.update(DbSchema.TBL_SCORE, values, DbSchema.COL_SCORE_IQRO_CODE+" = '"+item.getIqroId()+"' and "+DbSchema.COL_SCORE_PAGE_CODE+" = '"+item.getPagesId()+"' and " +DbSchema.COL_SCORE_SECTION_CODE+" = '"+item.getSectionId()+"' and " +DbSchema.COL_SCORE_IQRO_CHILD_CODE+" = '"+item.getChildId()+"'", null);
	}
	
	public int delete(Score item)
	{
		return db.delete(DbSchema.TBL_SCORE,DbSchema.COL_SCORE_IQRO_CODE+" = '"+item.getIqroId()+"' and "+DbSchema.COL_SCORE_PAGE_CODE+" = '"+item.getPagesId()+"' and " +DbSchema.COL_SCORE_SECTION_CODE+" = '"+item.getSectionId()+"' and " +DbSchema.COL_SCORE_IQRO_CHILD_CODE+" = '"+item.getChildId()+"'", null);
	}
	
	public int deleteByChild(int childID)
	{
		return db.delete(DbSchema.TBL_SCORE,DbSchema.COL_SCORE_IQRO_CHILD_CODE+" = '"+childID+"'", null);
	}
	
	public boolean cekCode(Score item) {
		 
		boolean has = false;
		String selectQuery = " SELECT  * FROM " + DbSchema.TBL_SCORE  + 
						      " Where "+DbSchema.COL_SCORE_IQRO_CODE+" = '"+item.getIqroId()+"' and "+DbSchema.COL_SCORE_PAGE_CODE+" = '"+item.getPagesId()+"' and " +DbSchema.COL_SCORE_SECTION_CODE+" = '"+item.getSectionId()+"' and " +DbSchema.COL_SCORE_IQRO_CHILD_CODE+" = '"+item.getChildId()+"'";
		 
		Cursor c = db.rawQuery(selectQuery, null);
		if(c.getCount() > 0)
			has = true;
			
		return has;
	}

}
