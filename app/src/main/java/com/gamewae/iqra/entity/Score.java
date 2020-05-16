package com.gamewae.iqra.entity;

public class Score {
	private int id;
	private int childId;
	private String childName;
	private int iqroId;
	private int sectionId;
	private int pagesId;
	private int score;
	
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChildId() {
		return childId;
	}
	public void setChildId(int childId) {
		this.childId = childId;
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
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public int getPagesId() {
		return pagesId;
	}
	public void setPagesId(int pagesId) {
		this.pagesId = pagesId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
