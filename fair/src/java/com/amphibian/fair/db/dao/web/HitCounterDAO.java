package com.amphibian.fair.db.dao.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;

public class HitCounterDAO extends BaseDAO {
	
	private final static String SELECT_COUNT = 
		"SELECT hits FROM hitcounters WHERE id = ?";
	
	private final static String INSERT_NEW =
		"INSERT INTO hitcounters (id, hits) VALUES (?, 0)";

	private final static String UPDATE_COUNT =
		"UPDATE hitcounters SET hits = hits + 1 WHERE id = ?";
	
	public int hit(int page) {
		
		int hits = 0;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		
		try {
			
			ps1 = conn.prepareStatement(SELECT_COUNT);
			ps1.setInt(1, page);
			rs = ps1.executeQuery();
			if (rs.next()) {
				hits = rs.getInt(1) + 1;
			} else {
				// must be the first time this page was hit
				ps2 = conn.prepareStatement(INSERT_NEW);
				ps2.setInt(1, page);
				ps2.executeUpdate();
				hits = 1;
			}
			
			ps3 = conn.prepareStatement(UPDATE_COUNT);
			ps3.setInt(1, page);
			ps3.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps1);
			close(ps2);
			close(ps3);
			close(conn);
		}
		
		return hits;
		
	}
	
}
