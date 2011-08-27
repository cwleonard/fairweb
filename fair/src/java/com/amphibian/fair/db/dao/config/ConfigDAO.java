package com.amphibian.fair.db.dao.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;

public class ConfigDAO extends BaseDAO {
	
	public boolean getBooleanValue(String key) {
		return Boolean.parseBoolean(this.getValue(key));
	}
	
	public int getIntValue(String key) {
		return Integer.parseInt(this.getValue(key));
	}
	
	/**
	 * Get a value for the given key
	 * 
	 * @param key
	 * @return value
	 */
	public String getValue(String key) {
		
		String val = null;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "select v from config where k = '" + key + "'";
			
			stmt1 = conn.createStatement();
			rs = stmt1.executeQuery(sql);
			if (rs.next()) {
				val = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
			close(conn);
		}
		
		return val;
		
	}
	
	public void setValue(String key, String value) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		Statement stmt1 = null;
		
		try {
			
			String sql = null;
			
			String oldVal = this.getValue(key);
			if (oldVal == null) {
				sql = "insert into config (k, v) values ('" + key
						+ "', '" + value + "'";
			} else {
				sql = "update config set v = '" + value + "' "
						+ "where k = '" + key + "'";
			}
			
			stmt1 = conn.createStatement();
			stmt1.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt1);
			close(conn);
		}
		
	}
	
}
