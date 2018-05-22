package com.EcommerceApp.Entity;

public class Products {
	private int productid;
	private String productName;
	private int catID;
	
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Products(int productid, String productName) {
		super();
		this.productid = productid;
		this.productName = productName;
	}

	public Products(String productName) {
		super();
		this.productName = productName;
	}
	public int getCatID() {
		return catID;
	}
	public void setCatID(int catID) {
		this.catID = catID;
	}
	public Products(int productid, String productName, int catID) {
		super();
		this.productid = productid;
		this.productName = productName;
		this.catID = catID;
	}
	

}
