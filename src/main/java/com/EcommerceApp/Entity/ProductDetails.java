package com.EcommerceApp.Entity;

public class ProductDetails {
	private int prodDetailsID;
	private int quantityAvail;
	private int price;
	private String brand;
	private Products Products;
	private Categories Categories;
	
		
	public ProductDetails(Categories Categories,Products Products,int prodDetailsID, String brand,int price, int quantityAvail ) {
		super();
		this.Categories=Categories;
		this.Products=Products;
		this.prodDetailsID=prodDetailsID;
		this.quantityAvail = quantityAvail;
		this.price = price;
		this.brand = brand;
	}
	public int getProdDetailsID() {
		return prodDetailsID;
	}
	public void setProdDetailsID(int prodDetailsID) {
		this.prodDetailsID = prodDetailsID;
	}

	public int getQuantityAvail() {
		return quantityAvail;
	}
	public void setQuantityAvail(int quantityAvail) {
		this.quantityAvail = quantityAvail;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	

}
