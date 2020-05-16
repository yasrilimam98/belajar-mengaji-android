package com.gamewae.iqra.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.pdf.PdfDocument.Page;

import com.gamewae.iqra.R;
import com.gamewae.iqra.entity.Score;
import com.gamewae.iqra.entity.iqro;
import com.gamewae.iqra.entity.page;
import com.gamewae.iqra.entity.section;
import com.gamewae.iqra.sqlite.DatabaseManager;
import com.gamewae.iqra.sqlite.ds.ScoreDataSource;

public class jsonParser {
	private String name;
	private Activity mContext;
	private String jsonString;
	
	private ArrayList<section> listallsection;
	private ArrayList<page> listpage;
	private iqro iq;
	private int childID = -1;
	public jsonParser(Activity context,String name,int childID)
	{
		this.name = name;
		this.mContext = context;
		this.listallsection = new ArrayList<section>();
		this.listpage = new ArrayList<page>();
		this.iq = new iqro();
		this.childID = childID;
		this.jsonString = loadJSONFromAsset();
	}
	
	public void parse()
	{
		if(this.jsonString == null)
			return;
		
		iqro iq = new iqro();
		try {
			JSONObject obj = new JSONObject(this.jsonString);
			iq.setId(obj.getInt("iqro_id"));
			iq.setName("IQRO "+obj.getInt("iqro_id"));
			
			if(iq.getId() == 1)
				iq.setCover(R.drawable.cover_1);
			else if(iq.getId() == 2)
				iq.setCover(R.drawable.cover_2);
			else if(iq.getId() == 3)
				iq.setCover(R.drawable.cover_3);
			else if(iq.getId() == 4)
				iq.setCover(R.drawable.cover_4);
			else if(iq.getId() == 5)
				iq.setCover(R.drawable.cover_5);
			else if(iq.getId() == 6)
				iq.setCover(R.drawable.cover_6);
			
			ArrayList<page> pglist = new ArrayList<page>();
			JSONArray pgarray = obj.getJSONArray("pages");
			
			for (int i = 0; i < pgarray.length(); i++) {
				
				page p = new page();
				p.setPageId(pgarray.getJSONObject(i).getInt("page_id"));
				p.setName(pgarray.getJSONObject(i).getString("name"));
				p.setLabel((i+1)+"");
				p.setIqroId(iq.getId());
				
				ArrayList<section> sclist = new ArrayList<section>();
				
				JSONArray scarray = pgarray.getJSONObject(i).getJSONArray("sections");
				
				int pageScore = 0;
				for (int j = 0; j < scarray.length(); j++) {
					section s = new section();
					s.setSectionId(scarray.getJSONObject(j).getInt("section_id"));
					s.setText(scarray.getJSONObject(j).getString("text"));
					s.setName(""+(listallsection.size()+1));
					s.setIqroId(iq.getId());
					s.setPageId(p.getPageId());
					s.setPageName(p.getName());
					
					Score SC = new Score();
					SC.setScore(0);
					SC.setIqroId(s.getIqroId());
					SC.setPagesId(s.getPageId());
					SC.setSectionId(s.getSectionId());
					SC.setChildId(childID);
					
					if(childID != -1)
					{
						SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
						ScoreDataSource DS = new ScoreDataSource(db);
						SC = DS.get(SC);
						DatabaseManager.getInstance().closeDatabase();
					}
					
					s.setScore(SC.getScore());
					
					pageScore += s.getScore();
					sclist.add(s);
					listallsection.add(s);
				}
				
				p.setScore(Math.round(pageScore/sclist.size()));
				p.setSections(sclist);
				pglist.add(p);
			}
			
			
			
			iq.setPages(pglist);
			this.listpage = pglist;
			
			this.iq = iq;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String loadJSONFromAsset() {
	    String json = "";
	    try {
	        InputStream is = mContext.getAssets().open("content/"+this.name);
	        int size = is.available();
	        byte[] buffer = new byte[size];
	        is.read(buffer);
	        is.close();
	        json = new String(buffer, "UTF-8");
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	    return json;
	}
	
	public iqro getIqro()
	{
		return this.iq;
	}
	
	public ArrayList<section> getAllSection()
	{
		return this.listallsection;
	}
	
	
	public ArrayList<page> getAllPage()
	{
		return this.listpage;
	}
	
	public ArrayList<section> getSectionPerpage(int pageid)
	{
		ArrayList<section> s = new ArrayList<section>();
		for (int i = 0; i < this.listpage.size(); i++) {
			if(this.listpage.get(i).getPageId() == pageid)
			{
				s =  this.listpage.get(i).getSections();
				break;
			}
		}
		
		return s;
	}

}
