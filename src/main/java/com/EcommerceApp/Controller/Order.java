package com.EcommerceApp.Controller;

import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.EcommerceApp.API.AddToCart;
import com.EcommerceApp.CustomExceptions.ItemNotAvail;
import com.EcommerceApp.Dao.ShopDaoImpl;
import com.EcommerceApp.Entity.Cart;
import com.google.gson.Gson;

@Path("order")
public class Order {
	  @POST
	    @Path("/addtocart")
	   @Produces(MediaType.TEXT_PLAIN)
	    public void addUser(
	    		@FormParam  ("productID") int productID,
	    		@FormParam  ("catID") int catID,
	    		@FormParam("prodDetailsID") int prodDetailsID,
	    		@FormParam("quantity") int quantity
	    		) throws SQLException, ItemNotAvail  {
			AddToCart.Add(catID,productID,prodDetailsID,quantity);
	  }
}
