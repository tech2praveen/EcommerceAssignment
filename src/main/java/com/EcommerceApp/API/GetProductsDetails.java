package com.EcommerceApp.API;

import com.EcommerceApp.Dao.ShopDaoImpl;
import com.google.gson.Gson;

public class GetProductsDetails{
	


	public static String getProd()
	{
	ShopDaoImpl cat=new ShopDaoImpl();		
    Gson gson = new Gson();
    String jsonInString = gson.toJson(cat.getProdList());
   return jsonInString;
   
}
}
