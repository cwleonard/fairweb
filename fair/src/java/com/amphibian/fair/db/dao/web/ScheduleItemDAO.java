package com.amphibian.fair.db.dao.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import com.amphibian.fair.beans.ScheduleItem;
import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;

public class ScheduleItemDAO extends BaseDAO {

	private final static String CHECK_FOR_ITEM = "select id from schedule where id = ?";
	
	private final static String UPDATE = "update schedule set day = ?, description = ?, "
		+ "start_time = ?, stop_time = ?, all_day = ? where id = ?";
	
	private final static String INSERT = "insert into schedule (day, description, "
		+ "start_time, stop_time, all_day) values (?, ?, ?, ?, ?)";
	
	private final static String DELETE = "delete from schedule where id = ?";
	
	private final static String LOAD_BY_DAY =
		"SELECT id, day, description, "
		+ "date_format(start_time, '%l:%i %p'), "
		+ "date_format(stop_time, '%l:%i %p'), "
		+ "all_day "
		+ "FROM schedule WHERE day = ? "
		+ "order by all_day, start_time";

	
	private final static String LOAD_BY_ID =
		"SELECT day, description, "
		+ "date_format(start_time, '%l:%i %p'), "
		+ "date_format(stop_time, '%l:%i %p'), "
		+ "all_day FROM schedule WHERE id = ?";
	
	private final static String GET_ID = "select LAST_INSERT_ID()";

	
	public ScheduleItem loadById(String id) {
		
		ScheduleItem ret = null;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.prepareStatement(LOAD_BY_ID);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				ret = new ScheduleItem();
				
				ret.setId(id);
				ret.setDay(rs.getInt(1));
				ret.setDescription(rs.getString(2));
				ret.setStartTime(rs.getString(3));
				ret.setStopTime(rs.getString(4));
				ret.setAllDay(rs.getBoolean(5));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}

		return ret;
		
	}

	public ArrayList loadByDay(int day) {

		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = null;
		ArrayList ret = null;
		try {
			conn = dbcm.getConnection();
			ret = loadByDay(day, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		return ret;
	}
		
	public ArrayList loadByDay(int day, Connection conn) {
		
		ArrayList<ScheduleItem> ret = new ArrayList<ScheduleItem>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.prepareStatement(LOAD_BY_DAY);
			stmt.setInt(1, day);

			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				ScheduleItem item = new ScheduleItem();
				
				item.setId(rs.getString(1));
				item.setDay(rs.getInt(2));
				item.setDescription(rs.getString(3));
				item.setStartTime(rs.getString(4));
				item.setStopTime(rs.getString(5));
				item.setAllDay(rs.getBoolean(6));
				
				ret.add(item);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}

		return ret;
		
	}

	public boolean delete(String id) {
		
		boolean ret = true;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		
		try {
		
			stmt1 = conn.prepareStatement(DELETE);
			stmt1.setString(1, id);
			stmt1.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		} finally {
			close(stmt1);
			close(conn);
		}
		
		return ret;
		
	}
	
	public String store(ScheduleItem item) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		
		try {
		
			conn.setAutoCommit(false);

			if (itemExists(item, conn)) {
				// update
				stmt1 = conn.prepareStatement(UPDATE);
				stmt1.setInt(1, item.getDay());
				stmt1.setString(2, item.getDescription());
				if (item.getStartTime() == null || item.getStartTime().length() == 0) {
					stmt1.setNull(3, Types.TIME);	
				} else {
					stmt1.setString(3, item.getStartTime());
				}
				if (item.getStopTime() == null || item.getStopTime().length() == 0) {
					stmt1.setNull(4, Types.TIME);
				} else {
					stmt1.setString(4, item.getStopTime());
				}
				stmt1.setBoolean(5, item.isAllDay());
				stmt1.setString(6, item.getId());
			} else {
				// insert
				stmt1 = conn.prepareStatement(INSERT);
				stmt1.setInt(1, item.getDay());
				stmt1.setString(2, item.getDescription());
				if (item.getStartTime() == null || item.getStartTime().length() == 0) {
					stmt1.setNull(3, Types.TIME);	
				} else {
					stmt1.setString(3, item.getStartTime());
				}
				if (item.getStopTime() == null || item.getStopTime().length() == 0) {
					stmt1.setNull(4, Types.TIME);
				} else {
					stmt1.setString(4, item.getStopTime());
				}
				stmt1.setBoolean(5, item.isAllDay());
			}
			
			stmt1.executeUpdate();
			
			if (item.getId() == null) {
				stmt2 = conn.prepareStatement(GET_ID);
				rs = stmt2.executeQuery();
				if (rs.next()) {
					item.setId(rs.getString(1));
				}
			}
			
			conn.commit();
			
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(stmt1);
			close(conn);
		}
		
		return item.getId();
		
	}
	
	private boolean itemExists(ScheduleItem item, Connection conn) {

		boolean ret = false;
		
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {
		
			stmt1 = conn.prepareStatement(CHECK_FOR_ITEM);
			stmt1.setString(1, item.getId());
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
