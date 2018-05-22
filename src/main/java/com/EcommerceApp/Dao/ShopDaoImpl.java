package com.EcommerceApp.Dao;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.EcommerceApp.CustomExceptions.ItemNotAvail;
import com.EcommerceApp.Entity.*;
import com.mysql.jdbc.*;

public class ShopDaoImpl implements ShopDao {

	private static Logger logger = LogManager.getLogger();

	private static final String GETCATEGORIES = "select * FROM categories";

	private static final String GETBYCAT = "select productName from products where catID=?;";

	private static final String UPDATEQTY = "update productDetails set quatityAvail=? where proddetailsID=?;";

	private static final String GETQTY = "select quatityAvail FROM productDetails where proddetailsID=?";

	private static final String GETCATEGORIESBYID = "select * FROM categories where catID=?";

	private static final String DISPPRODUCTS = "select p.productID,p.productName,c.catID,c.catName ,pp.prodDetailsID ,"
			+ "pp.quatityAvail,pp.price,pp.brand from products p ,"
			+ " categories c,productDetails pp where c.catID=p.catID and pp.productID=p.productID ;";

	private static final String AddtoCart = "insert into cartItems (catID,quantity,productID,totalPrice,proddetailsID) values(?,?,?,?,?);";

	private static final String AddPRODUCTS = "select p.productID,p.productName,c.catID,c.catName ,pp.prodDetailsID ,"
			+ " pp.quatityAvail,pp.price,pp.brand from products p ,"
			+ "  categories c,productDetails pp where pp.prodDetailsID=? and c.catID=p.catID and pp.productID=p.productID;";

	private static Connection getConnection() {
		logger.info("Creating Connection");
		Properties configFile = new Properties();
		try {
			configFile.load(ShopDaoImpl.class.getClassLoader().getResourceAsStream("config.properties"));
			String drivername = configFile.getProperty("DRIVER_NAME");
			String user = configFile.getProperty("user");
			String pass = configFile.getProperty("password");
			String db_url = configFile.getProperty("db_url");

			Class.forName(drivername);
			return (Connection) DriverManager.getConnection(db_url, user, pass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ProductDetails> getProdList() {
		logger.info("getting products List");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Object> records = new LinkedList<Object>();
		List<ProductDetails> list = new ArrayList<ProductDetails>();
		conn = getConnection();
		try {
			stmt = conn.prepareStatement(DISPPRODUCTS);
			rs = stmt.executeQuery();
			/*
			 * int cols = rs.getMetaData().getColumnCount(); while(rs.next()){ Object[] arr
			 * = new Object[cols]; for(int i=0; i<cols; i++){ arr[i] = rs.getObject(i+1); }
			 * records.add(arr); } return records;
			 */
			while (rs.next()) {
				Categories cat = new Categories(rs.getInt(3), rs.getString(4));
				Products prod = new Products(rs.getInt(1), rs.getString(2), rs.getInt(3));
				list.add(new ProductDetails(cat, prod, rs.getInt(5), rs.getString(8), rs.getInt(7), rs.getInt(6)));

			}
			return list;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return list;
	}

	@Override
	public List<Categories> getCatList() {

		logger.info("getting Categories List");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Categories> list = new ArrayList<Categories>();
		conn = getConnection();
		try {
			stmt = conn.prepareStatement(GETCATEGORIES);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Categories(rs.getInt(1), rs.getString(2)));
			}
			return list;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return list;

	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getCartList(Cart c) throws ItemNotAvail {

		logger.info("Adding to Cart");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int qty = 0;
		int prodDetailsID = 0;
		int totalPrice = 0;
		int productID = 0;
		int catID = 0;
		int price = 0;
		List<Cart> list = new ArrayList<Cart>();
		logger.info(c.toString());
		logger.info(c.getCartID());
		conn = getConnection();
		try {

			logger.info("inside try");
			stmt = conn.prepareStatement(AddPRODUCTS);

			stmt.setInt(1, c.getProdDetailsID());
			rs = stmt.executeQuery();
			while (rs.next()) {
				qty = rs.getInt(6);
				prodDetailsID = rs.getInt(5);
				price = rs.getInt(7);
				productID = rs.getInt(1);
				catID = rs.getInt(3);
			}
			logger.info("Quantity" + qty + ">" + c.getQuantity());
			logger.info("ProductID:" + c.getProductID() + "==" + prodDetailsID);
			logger.info("ProductDetailsID:" + c.getProdDetailsID() + "==" + prodDetailsID);
			if (qty < c.getQuantity() || c.getProductID() != prodDetailsID || c.getProdDetailsID() != prodDetailsID) {
				logger.info("Item Not Avaialble Throwing custom Exception");
				throw new ItemNotAvail("Product not available");

			} else {
				logger.info("Adding to Cart");
				Cart newCart = new Cart(catID, productID, prodDetailsID, c.getQuantity());
				totalPrice = price * c.getQuantity();
				newCart.setTotalPrice(totalPrice);

				stmt = conn.prepareStatement(AddtoCart);
				stmt.setInt(1, newCart.getCatID());
				stmt.setInt(2, newCart.getQuantity());
				stmt.setInt(3, newCart.getProductID());
				stmt.setInt(4, newCart.getTotalPrice());
				stmt.setInt(5, newCart.getProdDetailsID());
				stmt.execute();
				logger.info("Updating Quantity");

				stmt = conn.prepareStatement(UPDATEQTY);
				int newQty = qty - c.getQuantity();
				stmt.setInt(1, newQty);
				stmt.setInt(2, newCart.getProdDetailsID());
				stmt.execute();

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	@Override
	public List<String> getByCat(int id) throws ItemNotAvail {

		logger.info("getting Product List bt Categories");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		conn = getConnection();
		try {
			stmt = conn.prepareStatement(GETBYCAT);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next() == false) {
				throw new ItemNotAvail();
			} else {
				do {
					Products prod = new Products(rs.getString(1));
					list.add(prod.getProductName());
				} while (rs.next());
			}
			return list;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return list;

	}

}
