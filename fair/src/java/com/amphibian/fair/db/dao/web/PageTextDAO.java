package com.amphibian.fair.db.dao.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.amphibian.fair.beans.PageText;
import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;

public class PageTextDAO extends BaseDAO {

	private final static String DELETE_ITEM =
		"DELETE FROM pagetexts WHERE id = ?";
	
	private final static String LOAD_BY_ID =
		"SELECT section, text "
		+ "FROM pagetexts WHERE id =?";
	
	private final static String LOAD_ALL =
		"SELECT id, section, text "
		+ "FROM pagetexts";
	
	private final static String CHECK_FOR_ITEM =
		"SELECT id FROM pagetexts WHERE id = ?";
	
	private final static String UPDATE =
		"UPDATE pagetexts SET section = ?, "
		+ "text = ? WHERE id =?";

	private final static String INSERT =
		"INSERT INTO pagetexts (section, text) "
		+ "values (?, ?)";
	
	private final static String GET_ID = "select LAST_INSERT_ID()";

	/**
	 * Loads an individual news item given its unique id
	 * number.
	 * 
	 * @param id the id number of the item to load
	 * @return the NewsItem, or null if it does not exist
	 */
	public PageText loadById(int id) {
		
		PageText ret = null;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_BY_ID);
			stmt1.setInt(1, id);

			rs = stmt1.executeQuery();
			
			if(rs.next()) {
				
				ret = new PageText();
				ret.setId(id);
				ret.setSection(rs.getString(1));
				ret.setText(rs.getString(2));
				
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
	
	/**
	 * Loads the list of all news items from the database.
	 * 
	 * @return an ArrayList of NewsItem objects
	 */
	public Map loadAll() {
		
		Map ret = new HashMap();
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_ALL);
			rs = stmt1.executeQuery();
			
			while(rs.next()) {
				
				PageText n = new PageText();
				n.setId(rs.getInt(1));
				n.setSection(rs.getString(2));
				n.setText(rs.getString(3));
				ret.put(n.getSection(), n);
				
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

	/**
	 * Removes a news item from the database, given the
	 * item's unique id number.
	 * 
	 * @param id the id number of the item to be removed
	 */
	public void remove(int id) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;

		try {
			
			stmt1 = conn.prepareStatement(DELETE_ITEM);
			stmt1.setInt(1, id);
			
			stmt1.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt1);
			close(conn);
		}
		
	}
	
	public void store(PageText n) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		
		try {
		
			boolean setId = false;
			if (itemExists(n, conn)) {
				stmt1 = conn.prepareStatement(UPDATE);
				stmt1.setString(1, n.getSection());
				stmt1.setString(2, n.getText());
				stmt1.setInt(4, n.getId());
			} else {
				setId = true;
				stmt1 = conn.prepareStatement(INSERT);
				stmt1.setString(1, n.getSection());
				stmt1.setString(2, n.getText());
			}
			
			stmt1.executeUpdate();

			if (setId) {
				stmt2 = conn.prepareStatement(GET_ID);
				rs = stmt2.executeQuery();
				if (rs.next()) {
					n.setId(rs.getInt(1));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt2);
			close(stmt1);
			close(conn);
		}
		
	}

	private boolean itemExists(PageText item, Connection conn) {
		
		boolean ret = false;
		
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {
		
			stmt1 = conn.prepareStatement(CHECK_FOR_ITEM);
			stmt1.setInt(1, item.getId());
			rs = stmt1.executeQuery();
			if (rs.next()) {
				ret = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
		}
		
		return ret;
		
	}
}
