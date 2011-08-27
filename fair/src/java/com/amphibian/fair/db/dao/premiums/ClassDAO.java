package com.amphibian.fair.db.dao.premiums;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;
import com.amphibian.fair.premiums.PremiumsClass;
import com.amphibian.fair.premiums.PremiumsDepartment;
import com.amphibian.fair.premiums.PremiumsSection;

public class ClassDAO extends BaseDAO {

	private final static String LOAD_BY_CODE =
		"select id, number, code, "
		+ "description, section, parent, "
		+ "rules, allowentry, maxentry "
		+ "from class where code = ?";
	
	private final static String LOAD_BY_ID =
		"select id, number, code, "
		+ "description, section, parent, "
		+ "rules, allowentry, maxentry "
		+ "from class where id = ?";

	
	public PremiumsClass loadByCode(String code) {
		
		PremiumsClass c = null;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PrizeDAO pDAO = new PrizeDAO();
		ExtraPrizeDAO epDAO = new ExtraPrizeDAO();
		HonorableMentionDAO hmDAO = new HonorableMentionDAO();
		
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_BY_CODE);
			stmt1.setString(1, code);
			
			rs = stmt1.executeQuery();
			
			while(rs.next()) {
				
				c = new PremiumsClass();
				c.setId(rs.getInt(1));
				c.setNumber(rs.getString(2));
				c.setCode(rs.getString(3));
				c.setDescription(rs.getString(4));
				c.setSection(rs.getInt(5));
				c.setParent(rs.getInt(6));
				c.setRules(rs.getString(7));
				c.setAllowEntry(rs.getBoolean(8));
				c.setMaxEntry(rs.getInt(9));
				
				c.setLivestock(isLivestock(c));
				
				c.setPrizes(pDAO.loadAllByClass(conn, c));
				c.setExtraPrizes(epDAO.loadAllByClass(conn, c));
				c.setHonorableMention(hmDAO.loadByClass(conn, c));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
			close(conn);
		}

		return c;
		
	}
	
	public PremiumsClass loadById(int id) {
		return this.loadById(String.valueOf(id));
	}
	
	public PremiumsClass loadById(String id) {
		return this.loadById(id, true);
	}

	public PremiumsClass loadById(String id, boolean loadChildren) {

		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		PremiumsClass ret = loadById(id, loadChildren, conn);
		close(conn);
		return ret;
		
	}
	
	public PremiumsClass loadById(String id, boolean loadChildren, Connection conn) {
		
		PremiumsClass c = null;
		
		PrizeDAO pDAO = new PrizeDAO();
		ExtraPrizeDAO epDAO = new ExtraPrizeDAO();
		HonorableMentionDAO hmDAO = new HonorableMentionDAO();
		
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_BY_ID);
			stmt1.setString(1, id);
			
			rs = stmt1.executeQuery();
			
			while(rs.next()) {
				
				c = new PremiumsClass();
				c.setId(rs.getInt(1));
				c.setNumber(rs.getString(2));
				c.setCode(rs.getString(3));
				c.setDescription(rs.getString(4));
				c.setSection(rs.getInt(5));
				c.setParent(rs.getInt(6));
				c.setRules(rs.getString(7));
				c.setAllowEntry(rs.getBoolean(8));
				c.setMaxEntry(rs.getInt(9));
				
				if (loadChildren) {
					c.setChildren(this.loadAllByParent(c, conn));
				}
				
				c.setLivestock(isLivestock(c));
				
				c.setPrizes(pDAO.loadAllByClass(conn, c));
				c.setExtraPrizes(epDAO.loadAllByClass(conn, c));
				c.setHonorableMention(hmDAO.loadByClass(conn, c));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
		}

		return c;
	
	}
	
	public ArrayList loadAllBySection(PremiumsSection sect) {
		
		ArrayList ret = new ArrayList();
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.createStatement();

			String sql = "select id, number, code, "
				+ "description, section, parent, "
				+ "rules, allowentry, maxentry "
				+ "from class";
			
			if (sect != null) {
				sql += " where section = " + sect.getId();
			}

			rs = stmt1.executeQuery(sql);
			
			while(rs.next()) {
				
				PremiumsClass c = new PremiumsClass();
				
				c.setId(rs.getInt(1));
				c.setNumber(rs.getString(2));
				c.setCode(rs.getString(3));
				c.setDescription(rs.getString(4));
				c.setSection(rs.getInt(5));
				c.setParent(rs.getInt(6));
				c.setRules(rs.getString(7));
				c.setAllowEntry(rs.getBoolean(8));
				c.setMaxEntry(rs.getInt(9));

				if (sect != null) {
					c.setLivestock(sect.isLivestock());
				} else {
					c.setLivestock(isLivestock(c));
				}

				c.setChildren(this.loadAllByParent(c, conn));
				
				ret.add(c);
				
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
	
	public ArrayList loadAll() {
		
		return loadAllBySection(null);
		
	}
	
	private ArrayList loadAllByParent(PremiumsClass pc, Connection conn) {
		
		ArrayList ret = null;
		
		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.createStatement();

			String sql = "select id, number, code, "
				+ "description, section, parent, "
				+ "rules, allowentry, maxentry "
				+ "from class "
				+ "where parent = " + pc.getId();
			
			rs = stmt1.executeQuery(sql);
			
			while(rs.next()) {
				
				if (ret == null) {
					ret = new ArrayList();
				}
				
				PremiumsClass c = new PremiumsClass();
				
				c.setId(rs.getInt(1));
				c.setNumber(rs.getString(2));
				c.setCode(rs.getString(3));
				c.setDescription(rs.getString(4));
				c.setSection(rs.getInt(5));
				c.setParent(rs.getInt(6));
				c.setRules(rs.getString(7));
				c.setAllowEntry(rs.getBoolean(8));
				c.setMaxEntry(rs.getInt(9));
				
				c.setLivestock(isLivestock(c));
				
				c.setChildren(this.loadAllByParent(c, conn));
				
				ret.add(c);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
		}
		
		return ret;
		
	}
	
	private boolean isLivestock(PremiumsClass c) {
		
		boolean ls = false;

		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		try {
			
			String[] codeParts = c.getCode().split("\\.");

			if (codeParts.length > 1) {
			
				DepartmentDAO ddao = new DepartmentDAO();
				PremiumsDepartment dept = ddao.loadByNumber(conn, codeParts[0]);
				
				SectionDAO sdao = new SectionDAO();
				PremiumsSection sect = sdao.loadByDepartmentAndNumber(conn, dept, codeParts[1]);
				
				if (dept != null && sect != null) {
					ls = dept.isLivestock() || sect.isLivestock();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		return ls;
		
	}
	
	public void store(PremiumsClass c) {
		
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

			String sql = "select id from class where id = " + c.getId();
			
			rs = stmt1.executeQuery(sql);
			if (rs.next()) {
				exists = true;
			}
			if (c.getId() > -1) {
				replace = true;
			}
			
			if (replace && exists) {
				
				sql = "update class set "
					+ "number = '" + c.getNumber().replaceAll("'", "''") + "', "
					+ "code = '" + c.getCode().replaceAll("'", "''") + "', "
					+ "description = '" + c.getDescription().replaceAll("'", "''") + "', "
					+ "section = " + c.getSection() + ", "
					+ "parent = " + c.getParent() + ", "
					+ "rules = '" + c.getRules().replaceAll("'", "''") + "', "
					+ "allowentry = " + c.isAllowEntry() + ", "
					+ "maxentry = " + c.getMaxEntry() + " "
					+ "where id = " + c.getId();
				
			} else if (!replace) {
				
				updateid = true;
				sql = "insert into class (number, code, description, section, parent, rules, allowentry, maxentry) "
					+ "values ('" + c.getNumber().replaceAll("'", "''") + "', "
					+ "'" + c.getCode().replaceAll("'", "''") + "', "
					+ "'" + c.getDescription().replaceAll("'", "''") + "', "
					+ c.getSection() + ", "
					+ c.getParent() + ", "
					+ "'" + c.getRules().replaceAll("'", "''") + "', "
					+ c.isAllowEntry() + ", "
					+ c.getMaxEntry() + ")";
	
			} else {
				
				sql = "insert into class (id, number, code, description, section, parent, rules, allowentry, maxentry) "
					+ "values (" + c.getId() + ", '"
					+ c.getNumber().replaceAll("'", "''") + "', "
					+ "'" + c.getCode().replaceAll("'", "''") + "', "
					+ "'" + c.getDescription().replaceAll("'", "''") + "', "
					+ c.getSection() + ", "
					+ c.getParent() + ", "
					+ "'" + c.getRules().replaceAll("'", "''") + "', "
					+ c.isAllowEntry() + ", "
					+ c.getMaxEntry() + ")";
				
			}
		
			stmt1.executeUpdate(sql);
		
			if (updateid) {
				
				close(rs);
				
				sql = "select LAST_INSERT_ID()";
				
				rs = stmt1.executeQuery(sql);
				if (rs.next()) {
					c.setId(rs.getInt(1));
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
