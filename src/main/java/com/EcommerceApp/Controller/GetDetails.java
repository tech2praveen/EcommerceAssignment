package com.EcommerceApp.Controller;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.EcommerceApp.API.GetByCat;
import com.EcommerceApp.API.GetCategories;
import com.EcommerceApp.API.GetProductsDetails;
import com.EcommerceApp.CustomExceptions.ItemNotAvail;
import com.EcommerceApp.Dao.ShopDaoImpl;
import com.google.gson.Gson;

@Path("getdetails")
public class GetDetails {
	 @GET
     @Path("/getcat")
    @Produces(MediaType.TEXT_PLAIN)
     public String getcat() throws SQLException {
		 return GetCategories.getCat();
   }
	 @GET
     @Path("/getproducts")
    @Produces(MediaType.TEXT_PLAIN)
     public String getproduct() throws SQLException {
 	 return GetProductsDetails.getProd();
   }
	 
	 @GET
     @Path("/getbycat")
    @Produces(MediaType.TEXT_PLAIN)
	 public String getbyId(@QueryParam ("id") int id) throws SQLException, ItemNotAvail {
			return GetByCat.get(id);
		  }
}
