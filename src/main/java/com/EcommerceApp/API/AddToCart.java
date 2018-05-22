package com.EcommerceApp.API;

import com.EcommerceApp.CustomExceptions.ItemNotAvail;
import com.EcommerceApp.Dao.ShopDaoImpl;
import com.EcommerceApp.Entity.Cart;

public class AddToCart {
	
	public static void Add(int catID, int productID, int prodDetailsID, int quantity) throws ItemNotAvail
	{
		ShopDaoImpl order=new ShopDaoImpl();	
		Cart c=new Cart(catID,productID,prodDetailsID,quantity);
		order.getCartList(c);
	}

}
