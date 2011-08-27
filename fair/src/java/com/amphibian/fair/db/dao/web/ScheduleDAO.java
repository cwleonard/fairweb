package com.amphibian.fair.db.dao.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.amphibian.fair.beans.FairDay;
import com.amphibian.fair.beans.ScheduleItem;
import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;

public class ScheduleDAO extends BaseDAO {

	private final static String LOAD_ALL =
		"SELECT day, title, admission, start_time, sponsor "
		+ "FROM dayinfo ORDER BY day ASC";
	
	private final static String LOAD_PICS =
		"SELECT id, image FROM schedulepics WHERE day = ?";
	
	private final static String LOAD_BY_DAY =
		"SELECT day, title, admission, start_time, sponsor "
		+ "FROM dayinfo WHERE day = ?";
	
	private final static String UPDATE_DAY =
		"UPDATE dayinfo SET title = ?, admission = ?, "
		+ "start_time = ?, sponsor = ? WHERE day = ?";
	
	
	
	public FairDay loadByDay(int day) {
		
		FairDay ret = null;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_BY_DAY);
			stmt1.setInt(1, day);

			rs = stmt1.executeQuery();

			if(rs.next()) {

				ScheduleItemDAO itemDAO = new ScheduleItemDAO();
				
				ret = new FairDay(rs.getInt(1));
				
				ret.setTitle(rs.getString(2));
				ret.setAdmission(rs.getString(3));
				ret.setStartTime(rs.getString(4));
				ret.setSponsor(rs.getString(5));

				Iterator itemIt = itemDAO.loadByDay(ret.getDay(), conn).iterator();
				while(itemIt.hasNext()) {
					ret.addItem((ScheduleItem)itemIt.next());
				}

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
		
		ArrayList<FairDay> ret = new ArrayList<FairDay>();
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_ALL);
			rs = stmt1.executeQuery();

			stmt2 = conn.prepareStatement(LOAD_PICS);
			
			ScheduleItemDAO itemDAO = new ScheduleItemDAO();

			while(rs.next()) {
				
				FairDay d = new FairDay(rs.getInt(1));
				
				d.setTitle(rs.getString(2));
				d.setAdmission(rs.getString(3));
				d.setStartTime(rs.getString(4));
				d.setSponsor(rs.getString(5));

				Iterator itemIt = itemDAO.loadByDay(d.getDay(), conn).iterator();
				while(itemIt.hasNext()) {
					d.addItem((ScheduleItem)itemIt.next());
				}

				stmt2.setInt(1, d.getDay());
				rs2 = stmt2.executeQuery();
				while(rs2.next()) {
					d.addImage(rs2.getString(2));
				}
				close(rs2);
				
				ret.add(d);
				
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

	
	public void store(FairDay day) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		
		try {
		
			stmt1 = conn.prepareStatement(UPDATE_DAY);
			stmt1.setString(1, day.getTitle());
			stmt1.setString(2, day.getAdmission());
			stmt1.setString(3, day.getStartTime());
			stmt1.setString(4, day.getSponsor());
			stmt1.setInt(5, day.getDay());

			stmt1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt1);
			close(conn);
		}
		
	}
	
	
}
