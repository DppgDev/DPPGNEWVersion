package com.smssendapi.dbconfig;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DBconnection {
	static Logger logger = LogManager.getLogger(DBconnection.class);
	public static Connection OpenConnection() {
		Connection con = null;
		Properties properties = new Properties();
		InputStream input = null;
		try {
			input = (InputStream) DBconnection.class.getResourceAsStream("/application.properties");
			properties.load(input);
			Class.forName(properties.getProperty("db.driverClassName"));
			con = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"),
					properties.getProperty("db.password"));
			con.setAutoCommit(false);
			logger.info("CON -> " + con);
		} catch (Exception ne) {
			logger.info("Error--->" + ne);
		}
		// System.out.println("test-->"+l_con);
		return con;
	}
}
