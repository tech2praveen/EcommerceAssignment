package com.EcommerceApp.API;

import com.EcommerceApp.CustomExceptions.ItemNotAvail;
import com.EcommerceApp.Dao.ShopDaoImpl;
import com.google.gson.Gson;

public class GetByCat {
	
	public static String get(int id) throws ItemNotAvail
	{
		ShopDaoImpl cat=new ShopDaoImpl();	
        Gson gson = new Gson();
        String jsonInString = gson.toJson(cat.getByCat(id));
       return jsonInString;
	}

}
