package com.amphibian.fair.db.dao.premiums;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.amphibian.fair.beans.Check;
import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;

public class CheckDAO extends BaseDAO {

	public ArrayList loadAll() {
		
		ArrayList ret = new ArrayList();
		
		Connection conn = null;
		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {

			DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
			conn = dbcm.getConnection();

			stmt1 = conn.createStatement();

			String sql = "select checknumber, exhibitorid, "
				+ "exhibitorname, exhibitoraddress, "
				+ "exhibitorcitystatezip, amount, filter "
				+ "from printedchecks order by checknumber";
			
			rs = stmt1.executeQuery(sql);
			
			while(rs.next()) {
				
				Check chk = new Check();
				chk.setNumber(rs.getInt(1));
				chk.setExhibitorId(rs.getInt(2));
				chk.setExhibitorName(rs.getString(3));
				chk.setExhibitorAddress(rs.getString(4));
				chk.setExhibitorCityStateZip(rs.getString(5));
				chk.setAmount(rs.getDouble(6));
				chk.setFilter(rs.getString(7));
				ret.add(chk);
				
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
	
	public void delete(int number) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		Statement stmt1 = null;

		try {
			
			String sql = "delete from printedchecks "
				+ "where checknumber = " + number;
			
			stmt1.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt1);
			close(conn);
		}
		
	}
	
	
}
