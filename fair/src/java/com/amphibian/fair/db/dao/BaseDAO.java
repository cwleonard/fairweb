package com.amphibian.fair.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDAO {
	
	protected final static String SELECT_LAST_ID =
		"SELECT LAST_INSERT_ID()";
	
	protected void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				// ignore
			}
		}
	}

	protected void close(Statement s) {
		if (s != null) {
			try {
				s.close();
			} catch (Exception e) {
				// ignore
			}
		}
	}
	
	protected void close(Connection c) {
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {
				// ignore
			}
		}
	}
	
	protected void rollback(Connection c) {
		if (c != null) {
			try {
				c.rollback();
			} catch (Exception e) {
				// ignore
			}
		}
	}
	
	protected int getLastId(Connection conn) {
		
		int ret = -1;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.prepareStatement(SELECT_LAST_ID);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				ret = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return ret;
		
	}
	
	protected String prepString(String s) {
		
		String ret = "null";
		if (s != null) {
			ret = s.replaceAll("'", "''");
			ret = "'" + ret + "'";
		}
		return ret;
		
	}
	
	public static void main(String[] args) {
		
		BaseDAO b = new BaseDAO();
		System.out.println(b.prepString("casey's description"));
		
	}
}
