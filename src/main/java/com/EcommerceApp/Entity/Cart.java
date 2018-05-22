package com.EcommerceApp.Entity;

public class Cart {
	
	private int cartID;
	//private int userID;
	private int prodDetailsID;
	private int quantity;
	private int productID;
	private int totalPrice;
	private int catID;
	
	
	public int getCatID() {
		return catID;
	}



	public void setCatID(int catID) {
		this.catID = catID;
	}

	public Cart(int catID,int productID,int prodDetailsID,int quantity) {
		super();
		this.catID=catID;
		this.quantity = quantity;
		this.productID = productID;
		this.prodDetailsID = prodDetailsID;
	}

	

	public void setProdDetailsID(int prodDetailsID) {
		this.prodDetailsID = prodDetailsID;
	}

	public int getCartID() {
		return cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getProdDetailsID() {
		return prodDetailsID;
	}



	@Override
	public String toString() {
		return "Cart [cartID=" + cartID + ", prodDetailsID=" + prodDetailsID + ", quantity=" + quantity + ", productID="
				+ productID + ", totalPrice=" + totalPrice + "]";
	}

}
