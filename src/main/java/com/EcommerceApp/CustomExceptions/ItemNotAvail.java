package com.EcommerceApp.CustomExceptions;

public class ItemNotAvail extends Exception {
	
	   public ItemNotAvail()   
	           {
	   System.out.println("Item No Available");
	           }
        public ItemNotAvail(String message)	   
	           {	   
	               super(message);	   
	           }

}
