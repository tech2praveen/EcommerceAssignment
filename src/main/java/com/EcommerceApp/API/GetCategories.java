package com.EcommerceApp.API;

import com.EcommerceApp.Dao.ShopDaoImpl;
import com.google.gson.Gson;

public class GetCategories {
	
	public static String getCat()
	{
		ShopDaoImpl cat=new ShopDaoImpl();		
        Gson gson = new Gson();
        String jsonInString = gson.toJson(cat.getCatList());
       return jsonInString;	
	}

}
