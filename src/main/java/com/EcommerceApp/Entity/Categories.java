package com.EcommerceApp.Entity;

public class Categories {
	
	private int catId;
	private String catName;
	
	public Categories(String catName) {
		super();
		this.catName = catName;
	}
	public Categories(int catId) {
		super();
		this.catId = catId;
	}
	public Categories(int catId, String catName) {
		super();
		this.catId = catId;
		this.catName = catName;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	

}
