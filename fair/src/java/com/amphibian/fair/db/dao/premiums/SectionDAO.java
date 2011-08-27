package com.amphibian.fair.db.dao.premiums;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;
import com.amphibian.fair.premiums.PremiumsDepartment;
import com.amphibian.fair.premiums.PremiumsSection;

public class SectionDAO extends BaseDAO {

	private final static String LOAD_BY_ID =
		"SELECT id, number, description, department, "
		+ "rules, livestock FROM section WHERE id = ?";

	private final static String LOAD_BY_DEPT_AND_NUM =
		"SELECT id, number, description, department, "
		+ "rules, livestock FROM section "
		+ "WHERE department = ? AND number = ?";

	private final static String LOAD_ALL =
		"SELECT id, number, description, department, "
		+ "rules, livestock FROM section";

	private final static String LOAD_ALL_BY_DEPT =
		"SELECT id, number, description, department, "
		+ "rules, livestock FROM section "
		+ "WHERE department = ?";

	
	public PremiumsSection loadById(int id) {
		return this.loadById(String.valueOf(id));
	}
	
	public PremiumsSection loadById(String id) {
		
		PremiumsSection ret = null;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_BY_ID);
			stmt1.setString(1, id);
			
			rs = stmt1.executeQuery();
			
			if(rs.next()) {
				
				ret = new PremiumsSection();
				ret.setId(rs.getInt(1));
				ret.setNumber(rs.getString(2));
				ret.setDescription(rs.getString(3));
				ret.setDepartment(rs.getInt(4));
				ret.setRules(rs.getString(5));
				ret.setLivestock(rs.getBoolean(6));
				
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
	
	public PremiumsSection loadByDepartmentAndNumber(Connection conn, PremiumsDepartment dept, String num) {
		
		PremiumsSection sect = null;
		
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_BY_DEPT_AND_NUM);
			stmt1.setInt(1, dept.getId());
			stmt1.setString(2, num);
			
			rs = stmt1.executeQuery();
			
			if(rs.next()) {
				
				sect = new PremiumsSection();
				sect.setId(rs.getInt(1));
				sect.setNumber(rs.getString(2));
				sect.setDescription(rs.getString(3));
				sect.setDepartment(rs.getInt(4));
				sect.setRules(rs.getString(5));
				sect.setLivestock(rs.getBoolean(6));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
		}
		
		return sect;
		
	}
	
	public ArrayList loadAllByDepartment(PremiumsDepartment dept) {
		
		ArrayList<PremiumsSection> ret = new ArrayList<PremiumsSection>();

		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			if (dept != null) {
				stmt1 = conn.prepareStatement(LOAD_ALL_BY_DEPT);
				stmt1.setInt(1, dept.getId());
				
			} else {
				stmt1 = conn.prepareStatement(LOAD_ALL);
			}

			rs = stmt1.executeQuery();
			
			while(rs.next()) {
				
				PremiumsSection s = new PremiumsSection();
				s.setId(rs.getInt(1));
				s.setNumber(rs.getString(2));
				s.setDescription(rs.getString(3));
				s.setDepartment(rs.getInt(4));
				s.setRules(rs.getString(5));
				s.setLivestock(rs.getBoolean(6));
				
				ret.add(s);
				
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
		
		return loadAllByDepartment(null);
		
	}

	public void store(PremiumsSection s) {
		
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

			String sql = "select id from section where id = " + s.getId();
			
			rs = stmt1.executeQuery(sql);
			if (rs.next()) {
				exists = true;
			}
			if (s.getId() > -1) {
				replace = true;
			}
			
			if (replace && exists) {
				
				sql = "update section set "
					+ "number = '" + s.getNumber().replaceAll("'", "''") + "', "
					+ "description = '" + s.getDescription().replaceAll("'", "''") + "', "
					+ "department = " + s.getDepartment() + ", "
					+ "rules = '" + s.getRules().replaceAll("'", "''") + "', "
					+ "livestock = " + s.isLivestock() + " "
					+ "where id = " + s.getId();
				
			} else if (!replace) {
				
				updateid = true;
				sql = "insert into section (number, description, department, rules, livestock) "
					+ "values ('" + s.getNumber().replaceAll("'", "''") + "', "
					+ "'" + s.getDescription().replaceAll("'", "''") + "', "
					+ s.getDepartment() + ", "
					+ "'" + s.getRules().replaceAll("'", "''") + "', "
					+ s.isLivestock() + ")";
	
			} else {
				
				sql = "insert into section (id, number, description, department, rules, livestock) "
					+ "values (" + s.getId() + ", '"
					+ s.getNumber().replaceAll("'", "''") + "', "
					+ "'" + s.getDescription().replaceAll("'", "''") + "', "
					+ s.getDepartment() + ", "
					+ "'" + s.getRules().replaceAll("'", "''") + "', "
					+ s.isLivestock() + ")";
				
			}
		
			stmt1.executeUpdate(sql);
		
			if (updateid) {
				s.setId(getLastId(conn));
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
