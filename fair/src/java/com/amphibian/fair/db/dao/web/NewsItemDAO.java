package com.amphibian.fair.db.dao.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.amphibian.fair.beans.NewsItem;
import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;

public class NewsItemDAO extends BaseDAO {

	private final static String DELETE_ITEM =
		"DELETE FROM newsitems WHERE id = ?";
	
	private final static String LOAD_BY_ID =
		"SELECT date_format(postdate, '%m/%d/%Y'), text "
		+ "FROM newsitems WHERE id =?";
	
	private final static String LOAD_ALL =
		"select id, date_format(postdate, '%m/%d/%Y'), "
		+ "text from newsitems order by postdate desc";
	
	private final static String CHECK_FOR_ITEM =
		"SELECT id FROM newsitems WHERE id = ?";
	
	private final static String UPDATE =
		"UPDATE newsitems SET postdate = STR_TO_DATE(?, '%m/%d/%Y'), "
		+ "text = ? WHERE id =?";

	private final static String INSERT =
		"insert into newsitems (postdate, text) "
		+ "values (curdate(), ?)";
	
	private final static String GET_ID = "select LAST_INSERT_ID()";

	/**
	 * Loads an individual news item given its unique id
	 * number.
	 * 
	 * @param id the id number of the item to load
	 * @return the NewsItem, or null if it does not exist
	 */
	public NewsItem loadById(int id) {
		
		NewsItem ret = null;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_BY_ID);
			stmt1.setInt(1, id);

			rs = stmt1.executeQuery();
			
			if(rs.next()) {
				
				ret = new NewsItem();
				ret.setId(id);
				ret.setPostDate(rs.getString(1));
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
	public ArrayList loadAll() {
		
		ArrayList<NewsItem> ret = new ArrayList<NewsItem>();
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_ALL);
			rs = stmt1.executeQuery();
			
			while(rs.next()) {
				
				NewsItem n = new NewsItem();
				n.setId(rs.getInt(1));
				n.setPostDate(rs.getString(2));
				n.setText(rs.getString(3));
				ret.add(n);
				
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
	
	public void store(NewsItem n) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		
		try {
		
			boolean setId = false;
			if (itemExists(n, conn)) {
				stmt1 = conn.prepareStatement(UPDATE);
				stmt1.setString(1, n.getPostDate());
				stmt1.setString(2, n.getText());
				stmt1.setInt(3, n.getId());
			} else {
				setId = true;
				stmt1 = conn.prepareStatement(INSERT);
				stmt1.setString(1, n.getText());
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

	private boolean itemExists(NewsItem item, Connection conn) {
		
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
