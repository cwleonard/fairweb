package com.amphibian.fair.db.dao.premiums;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;
import com.amphibian.fair.premiums.PremiumsClass;
import com.amphibian.fair.premiums.PremiumsPrize;

public class PrizeDAO extends BaseDAO {

	public ArrayList loadAllByClass(PremiumsClass pc) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		ArrayList ret = loadAllByClass(conn, pc);
		close(conn);
		return ret;
		
	}
	
	public ArrayList loadAllByClass(Connection conn, PremiumsClass pc) {
		
		ArrayList ret = new ArrayList();
		
		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.createStatement();

			String sql = "select id, classid, "
				+ "place, prize "
				+ "from prizes";
			
			if (pc != null) {
				sql += " where classid = " + pc.getId();
			}

			rs = stmt1.executeQuery(sql);
			
			while(rs.next()) {
				
				PremiumsPrize p = new PremiumsPrize();
				p.setId(rs.getInt(1));
				p.setClassId(rs.getInt(2));
				p.setPlace(rs.getInt(3));
				p.setPrize(rs.getString(4));
				
				ret.add(p);
				
			}

			if (ret.size() == 0) {
				if (pc.getParent() > 0) {
					ClassDAO cd = new ClassDAO();
					PremiumsClass parent = cd.loadById(String.valueOf(pc.getParent()), false, conn);
					ret = parent.getPrizes();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
		}
		
		return ret;
		
	}
	
	public ArrayList loadAll() {
		return this.loadAllByClass(null);
	}

	public void delete(int id) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		Statement stmt1 = null;

		try {
			
			String sql = "delete from prizes "
				+ "where id = " + id;
			
			stmt1.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt1);
			close(conn);
		}
		
	}
	
	public void store(PremiumsPrize pp) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		if (prizeExists(pp, conn)) {
			update(pp, conn);
		} else {
			add(pp, conn);
		}
		
		close(conn);
		
	}
	
	private boolean prizeExists(PremiumsPrize pp, Connection conn) {
		
		boolean exists = false;
		
		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {
		
			stmt1 = conn.createStatement();

			if (pp.getId() != -1) {
				String sql = "select id from prizes "
					+ "where id = " + pp.getId();
				rs = stmt1.executeQuery(sql);
				exists = rs.next();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
		}
		
		return exists;
		
	}
	
	private void update(PremiumsPrize pp, Connection conn) {
		
		Statement stmt1 = null;

		try {
			
			String sql = "update prizes set "
				+ "classid = " + pp.getClassId() + ", "
				+ "place = " + pp.getPlace() + ", "
				+ "prize = " + prepString(pp.getPrize()) + " "
				+ "where id = " + pp.getId();
				
			stmt1.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt1);
		}
		
	}
	
	private void add(PremiumsPrize pp, Connection conn) {
		
		boolean setId = false;
		Statement stmt1 = null;
		ResultSet rs = null;

		try {
			
			stmt1 = conn.createStatement();
			
			String sql = "insert into prizes (";
			
			if (pp.getId() != -1) {

				sql += "id, classid, "
					+ "place, prize) values ("
					+ pp.getId() + ", ";
				
			} else {
			
				setId = true;
				sql += "classid, "
					+ "place, prize) values (";
				
			}
			
			sql += pp.getClassId() + ", "
				+ pp.getPlace() + ", "
				+ prepString(pp.getPrize()) + ")";

			stmt1.executeUpdate(sql);

			if (setId) {
				
				sql = "select LAST_INSERT_ID()";
				rs = stmt1.executeQuery(sql);
				if (rs.next()) {
					pp.setId(rs.getInt(1));
				}
				
			}

		} catch (Exception e) {
			e.fillInStackTrace();
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
		}
		
	}
	
}
