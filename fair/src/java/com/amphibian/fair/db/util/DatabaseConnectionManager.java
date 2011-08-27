package com.amphibian.fair.db.util;

import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DatabaseConnectionManager {

	private Connection conn;

	public Connection getConnection() {

		if (conn == null) {
			this.connect();
		}

		return this.conn;

	}

	private void connect() {

		try {

			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MySQLDB");
			conn = ds.getConnection();

		} catch (Exception e) {

			System.err.println("context lookup failed, trying the old-fashioned way...");

		    try {
		    	
//				String userName = "sa";
//				String password = "ibc1919";
//				String url = "jdbc:jtds:sqlserver://192.168.0.102:1433/JCF2005";
//				Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

		    	String userName = "jcf2";
		    	String password = "whatnot";
		    	String url = "jdbc:mysql://amphibian.com/jcf2";
		    	Class.forName("com.mysql.jdbc.Driver").newInstance();

				conn = DriverManager.getConnection(url, userName, password);

		    } catch (Exception e2) {
		    	e2.printStackTrace();
		    }
			
		}

	}

}
