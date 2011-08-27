package com.amphibian.fair.db.dao.np;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;
import com.amphibian.fair.premiums.PremiumsClass;

public class ClassDAO extends BaseDAO {

	
	private static final String LOAD_BY_PARENT =
		"select unique_id, identifier, code, description, parent_id, "
			+ "rules, allow_entry, max_entry, livestock "
			+ "from classes "
			+ "where parent_id = ?";

	private static final String LOAD_ALL_FLAT =
		"select unique_id, identifier, code, description, parent_id, "
			+ "rules, allow_entry, max_entry, livestock "
			+ "from classes";
	
	
	
	public PremiumsClass loadAll() {
		
		PremiumsClass root = new PremiumsClass();
		root.setId(0);
		root.setDescription("Class Root");
		
		try {
			
			DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
			Connection conn = dbcm.getConnection();
			this.loadAllByParent(root, conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return root;
		
	}
	
	private ArrayList loadAllByParent(PremiumsClass pc, Connection conn) {
		
		ArrayList ret = null;
		
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_BY_PARENT);
			stmt1.setInt(1, pc.getId());
			rs = stmt1.executeQuery();
			
			while(rs.next()) {
				
				if (ret == null) {
					ret = new ArrayList();
				}
				
				PremiumsClass c = new PremiumsClass();
				
				c.setId(rs.getInt(1));
				c.setNumber(rs.getString(2));
				c.setCode(rs.getString(3));
				c.setDescription(rs.getString(4));
				c.setParent(rs.getInt(5));
				c.setRules(rs.getString(6));
				c.setAllowEntry(rs.getBoolean(7));
				c.setMaxEntry(rs.getInt(8));
				c.setLivestock(rs.getBoolean(9));
				
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
	
	private PremiumsClass loadAllFlat() {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		HashMap classMap = new HashMap();
		
		try {
			
			DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
			conn = dbcm.getConnection();

			stmt = conn.prepareStatement(LOAD_ALL_FLAT);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				PremiumsClass c = new PremiumsClass();
				c.setId(rs.getInt(1));
				c.setNumber(rs.getString(2));
				c.setCode(rs.getString(3));
				c.setDescription(rs.getString(4));
				c.setParent(rs.getInt(5));
				c.setRules(rs.getString(6));
				c.setAllowEntry(rs.getBoolean(7));
				c.setMaxEntry(rs.getInt(8));
				c.setLivestock(rs.getBoolean(9));
				classMap.put(new Integer(c.getId()), c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}
		
		PremiumsClass root = new PremiumsClass();
		root.setId(0);
		root.setParent(-1);
		root.setDescription("Class Root");
		classMap.put(new Integer(0), root);
		buildClassTree(classMap);
		
		return root;
		
		
		
	}
	
	private void buildClassTree(HashMap map) {
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext()) {
			PremiumsClass c = (PremiumsClass)map.get(it.next());
			PremiumsClass p = (PremiumsClass)map.get(new Integer(c.getParent()));
			if (p != null) {
				p.addChild(c);
			}
		}

	}
	
	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		ClassDAO dao = new ClassDAO();
		PremiumsClass root = dao.loadAllFlat();
		long finish = System.currentTimeMillis();
		
		System.out.println("load all took " + (finish - start) + " ms");
	
		String xml = root.toStringXML();
		File file = new File("c:\\all_classes_xml.xml");
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(xml);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
