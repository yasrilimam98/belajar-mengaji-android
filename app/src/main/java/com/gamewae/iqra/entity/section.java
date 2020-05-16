package com.gamewae.iqra.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class section implements Parcelable{
	private int sectionId;
	private int pageId;
	private int iqroId;
	private String text;
	private String name;
	private String pageName;
	private int score;
	private int status;
	
	public section()
	{
		
	}
	
	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getIqroId() {
		return iqroId;
	}

	public void setIqroId(int iqroId) {
		this.iqroId = iqroId;
	}

	public int getSectionId() {
		return sectionId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
	
		dest.writeInt(sectionId);
		dest.writeInt(pageId);
		dest.writeInt(iqroId);
		dest.writeString(text);
		dest.writeString(name);
		dest.writeInt(score);
		dest.writeInt(status);
		dest.writeString(pageName);
	}
	
	public static final Parcelable.Creator<section> CREATOR = new Parcelable.Creator<section>() {
	    public section createFromParcel(Parcel in) {
	        return new section(in);
	    }

	    public section[] newArray(int size) {
	        return new section[size];
	    }
	};
	
	public section(Parcel in) {
		sectionId = in.readInt();
		pageId = in.readInt();
		iqroId = in.readInt();
		text = in.readString();
		name = in.readString();
		score = in.readInt();
		status = in.readInt();
		pageName = in.readString();
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
}
