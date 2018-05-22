package com.EcommerceApp.Dao;

import java.sql.SQLException;
import java.util.*;

import com.EcommerceApp.CustomExceptions.ItemNotAvail;
import com.EcommerceApp.Entity.*;

public interface ShopDao {

	


	public List<ProductDetails> getProdList();
	public List<Categories> getCatList() throws SQLException;
	public List<User> getUserList();
	public void getCartList(Cart c) throws ItemNotAvail;
	public List<String> getByCat(int id) throws ItemNotAvail;
	
	
}
