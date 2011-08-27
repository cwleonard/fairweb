package com.amphibian.fair.db.dao.premiums;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;
import com.amphibian.fair.premiums.PremiumsDepartment;

public class DepartmentDAO extends BaseDAO {

	public PremiumsDepartment loadByNumber(String num) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		PremiumsDepartment dept = loadByNumber(conn, num);
		close(conn);
		return dept;
	
	}
	
	public PremiumsDepartment loadByNumber(Connection conn, String num) {
		
		PremiumsDepartment ret = null;
		
		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.createStatement();

			String sql = "select id, number, "
				+ "description, "
				+ "rules, livestock "
				+ "from department "
				+ "where number = '" + num + "'";

			rs = stmt1.executeQuery(sql);
			
			if(rs.next()) {
				
				ret = new PremiumsDepartment();
				ret.setId(rs.getInt(1));
				ret.setNumber(rs.getString(2));
				ret.setDescription(rs.getString(3));
				ret.setRules(rs.getString(4));
				ret.setLivestock(rs.getBoolean(5));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
		}
		
		return ret;
		
	}
	
	public PremiumsDepartment loadById(int id) {
		return this.loadById(String.valueOf(id));
	}
	
	public PremiumsDepartment loadById(String id) {
		
		PremiumsDepartment ret = null;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.createStatement();

			String sql = "select id, number, "
				+ "description, "
				+ "rules, livestock "
				+ "from department "
				+ "where id = " + id;

			rs = stmt1.executeQuery(sql);
			
			if(rs.next()) {
				
				ret = new PremiumsDepartment();
				ret.setId(rs.getInt(1));
				ret.setNumber(rs.getString(2));
				ret.setDescription(rs.getString(3));
				ret.setRules(rs.getString(4));
				ret.setLivestock(rs.getBoolean(5));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
			close(conn);
		}
		
		return ret;
		
	}
	
	public ArrayList loadAll() {
		
		ArrayList ret = new ArrayList();
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.createStatement();

			String sql = "select id, number, "
				+ "description, "
				+ "rules, livestock "
				+ "from department";

			rs = stmt1.executeQuery(sql);
			
			while(rs.next()) {
				
				PremiumsDepartment d = new PremiumsDepartment();
				d.setId(rs.getInt(1));
				d.setNumber(rs.getString(2));
				d.setDescription(rs.getString(3));
				d.setRules(rs.getString(4));
				d.setLivestock(rs.getBoolean(5));
				
				ret.add(d);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
			close(conn);
		}
		
		Collections.sort(ret);
		
		return ret;
		
	}

	public void store(PremiumsDepartment d) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {
		
			conn.setAutoCommit(false);

			boolean replace = false;
			boolean exists = false;
			boolean updateid = false;
			
			stmt1 = conn.createStatement();

			String sql = "select id from department where id = " + d.getId();
			
			rs = stmt1.executeQuery(sql);
			if (rs.next()) {
				exists = true;
			}
			if (d.getId() > -1) {
				replace = true;
			}
			
			if (replace && exists) {
				
				sql = "update department set "
					+ "number = '" + d.getNumber().replaceAll("'", "''") + "', "
					+ "description = '" + d.getDescription().replaceAll("'", "''") + "', "
					+ "rules = '" + d.getRules().replaceAll("'", "''") + "', "
					+ "livestock = " + d.isLivestock() + " "
					+ "where id = " + d.getId();
				
			} else if (!replace) {
				
				updateid = true;
				sql = "insert into department (number, description, rules, livestock) "
					+ "values ('" + d.getNumber().replaceAll("'", "''") + "', "
					+ "'" + d.getDescription().replaceAll("'", "''") + "', "
					+ "'" + d.getRules().replaceAll("'", "''") + "', "
					+ d.isLivestock() + ")";
	
			} else {
				
				sql = "insert into department (id, number, description, rules, livestock) "
					+ "values (" + d.getId() + ", '"
					+ d.getNumber().replaceAll("'", "''") + "', "
					+ "'" + d.getDescription().replaceAll("'", "''") + "', "
					+ "'" + d.getRules().replaceAll("'", "''") + "', "
					+ d.isLivestock() + ")";
				
			}
		
			stmt1.executeUpdate(sql);
		
			if (updateid) {
				
				rs.close();
				
				sql = "select LAST_INSERT_ID()";
				
				rs = stmt1.executeQuery(sql);
				if (rs.next()) {
					d.setId(rs.getInt(1));
				}
				
			}
			
			conn.commit();
			
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
			close(conn);
		}
		
	}
	
}
