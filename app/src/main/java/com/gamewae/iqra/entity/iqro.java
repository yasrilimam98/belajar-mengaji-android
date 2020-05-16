package com.gamewae.iqra.entity;

import java.util.ArrayList;

public class iqro {
	private int id;
	private String name;
	private int cover;
	private ArrayList<page> pages;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public ArrayList<page> getPages() {
		return pages;
	}
	public void setPages(ArrayList<page> pages) {
		this.pages = pages;
	}
	
}
