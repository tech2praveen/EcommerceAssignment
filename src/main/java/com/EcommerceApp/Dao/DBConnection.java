package com.EcommerceApp.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.Statement;

public final class DBConnection {
	private static Logger logger = LogManager.getLogger();
	Properties configFile = new Properties();
	public Connection conn;
	private Statement statement;
	public static DBConnection db;

	private DBConnection() throws IOException {
		configFile.load(DBConnection.class.getClassLoader().getResourceAsStream("config.properties"));
		String drivername = configFile.getProperty("DRIVER_NAME");
		String user = configFile.getProperty("user");
		String pass = configFile.getProperty("password");
		String db_url = configFile.getProperty("db_url");
		logger.info("Creating Connection");
		try {
			 Class.forName(drivername).newInstance();
			this.conn = (Connection) DriverManager.getConnection(db_url, user, pass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static synchronized DBConnection getDbCon() throws IOException {
		if (db == null) {
			db = new DBConnection();
		}
		return db;
	}
}
