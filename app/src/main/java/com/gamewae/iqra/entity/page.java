package com.gamewae.iqra.entity;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class page implements Parcelable {
	private int pageId;
	private String name;
	private int cover;
	private ArrayList<section> sections;
	private int iqroId;
	private int score;
	private String label;
	private int status;
	public page()
	{
		
	}
	
	public int getIqroId() {
		return iqroId;
	}
	public void setIqroId(int iqroId) {
		this.iqroId = iqroId;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCover() {
		return cover;
	}
	public void setCover(int cover) {
		this.cover = cover;
	}
	public ArrayList<section> getSections() {
		return sections;
	}
	public void setSections(ArrayList<section> sections) {
		this.sections = sections;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
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
	
		dest.writeInt(pageId);
		dest.writeString(name);
		dest.writeInt(cover);
		dest.writeList(sections);
		dest.writeInt(iqroId);
		dest.writeInt(score);
		dest.writeString(label);
		dest.writeInt(status);
	}
	
	public static final Parcelable.Creator<page> CREATOR = new Parcelable.Creator<page>() {
	    public page createFromParcel(Parcel in) {
	        return new page(in);
	    }

	    public page[] newArray(int size) {
	        return new page[size];
	    }
	};
	
	public page(Parcel in) {
		pageId = in.readInt();
		name = in.readString();
		cover = in.readInt();
		sections =  (ArrayList<section>) in.readSerializable();
		iqroId = in.readInt();
		score = in.readInt();
		label = in.readString();
		status = in.readInt();
	}
	
}
