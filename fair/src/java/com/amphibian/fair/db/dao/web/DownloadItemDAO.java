package com.amphibian.fair.db.dao.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.amphibian.fair.beans.DownloadItem;
import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;

public class DownloadItemDAO extends BaseDAO {

	private final static String DELETE_ITEM =
		"UPDATE downloads SET deleted = 1 WHERE id = ?";
	
	private final static String LOAD_BY_ID =
		"SELECT description, fileData, fileName "
		+ "FROM downloads WHERE id =?";
	
	private final static String LOAD_ALL =
		"SELECT id, description "
		+ "FROM downloads WHERE deleted = 0 ORDER BY description";
	
	private final static String CHECK_FOR_ITEM =
		"SELECT id FROM downloads WHERE id = ?";
	
	private final static String UPDATE =
		"UPDATE downloads SET description = ?, "
		+ "file = ? WHERE id =?";

	private final static String INSERT =
		"INSERT INTO downloads (description, file) "
		+ "values (?, ?)";

	private final static String INSERT_STREAM =
		"INSERT INTO downloads (description, fileData, fileName) "
		+ "values (?, ?, ?)";

	/**
	 * Loads an individual news item given its unique id
	 * number.
	 * 
	 * @param id the id number of the item to load
	 * @return the NewsItem, or null if it does not exist
	 */
	public DownloadItem loadById(int id) {
		
		DownloadItem ret = null;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_BY_ID);
			stmt1.setInt(1, id);

			rs = stmt1.executeQuery();
			
			if(rs.next()) {
				
				ret = new DownloadItem();
				ret.setId(id);
				ret.setDescription(rs.getString(1));
				
				byte[] buffer = new byte[1];
				InputStream is = rs.getBinaryStream(2);
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				while (is.read(buffer) > 0) {
					os.write(buffer);
				}
				os.close();
				
				ret.setInputStream(new ByteArrayInputStream(os.toByteArray()));
				
				ret.setFileName(rs.getString(3));
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
		
		ArrayList<DownloadItem> ret = new ArrayList<DownloadItem>();
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_ALL);
			rs = stmt1.executeQuery();
			
			while(rs.next()) {
				
				DownloadItem n = new DownloadItem();
				n.setId(rs.getInt(1));
				n.setDescription(rs.getString(2));
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
	
	public void store(DownloadItem n) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		
		try {
		
			boolean setId = false;
			if (itemExists(n, conn)) {
				stmt1 = conn.prepareStatement(UPDATE);
				stmt1.setString(1, n.getDescription());
				stmt1.setString(2, n.getFile());
				stmt1.setInt(4, n.getId());
			} else {
				setId = true;
				
				if (n.getInputStream() == null) {
					stmt1 = conn.prepareStatement(INSERT);
					stmt1.setString(1, n.getDescription());
					stmt1.setString(2, n.getFile());
				} else {
					stmt1 = conn.prepareStatement(INSERT_STREAM);
					stmt1.setString(1, n.getDescription());
					stmt1.setBinaryStream(2, n.getInputStream(), n.getFileLength());
					stmt1.setString(3, n.getFileName());
				}
			}
			
			stmt1.executeUpdate();

			if (setId) {
				n.setId(getLastId(conn));
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

	private boolean itemExists(DownloadItem item, Connection conn) {
		
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
