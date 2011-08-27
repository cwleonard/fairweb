package com.amphibian.fair.db.dao.premiums;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;
import com.amphibian.fair.premiums.PremiumsClass;
import com.amphibian.fair.premiums.PremiumsHonorableMention;

public class HonorableMentionDAO extends BaseDAO {

	public PremiumsHonorableMention loadByClass(Connection conn, PremiumsClass pc) {
		
		PremiumsHonorableMention p = null;

		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.createStatement();

			String sql = "select id, classid, "
				+ "number, prize "
				+ "from honorablemention "
				+ "where classid = " + pc.getId();

			rs = stmt1.executeQuery(sql);
			
			if(rs.next()) {
				
				p = new PremiumsHonorableMention();
				p.setId(rs.getInt(1));
				p.setClassId(rs.getInt(2));
				p.setNumber(rs.getInt(3));
				p.setPrize(rs.getString(4));
				
			} else {
				
				if (pc.getParent() > 0) {
					ClassDAO cd = new ClassDAO();
					PremiumsClass parent = cd.loadById(String.valueOf(pc.getParent()), false, conn);
					p = parent.getHonorableMention();
				}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
		}

		return p;
		
	}
	
	public ArrayList loadAllByClass(PremiumsClass pc) {
		
		ArrayList ret = new ArrayList();
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.createStatement();

			String sql = "select id, classid, "
				+ "number, prize "
				+ "from honorablemention";
			
			if (pc != null) {
				sql += " where classid = " + pc.getId();
			}

			rs = stmt1.executeQuery(sql);
			
			while(rs.next()) {
				
				PremiumsHonorableMention p = new PremiumsHonorableMention();
				p.setId(rs.getInt(1));
				p.setClassId(rs.getInt(2));
				p.setNumber(rs.getInt(3));
				p.setPrize(rs.getString(4));
				
				ret.add(p);
				
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
		return this.loadAllByClass(null);
	}

	public void delete(int id) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		Statement stmt1 = null;

		try {
			
			String sql = "delete from honorablemention "
				+ "where id = " + id;
			
			stmt1.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt1);
			close(conn);
		}
		
	}
	
	public void store(PremiumsHonorableMention phm) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		if (prizeExists(phm, conn)) {
			update(phm, conn);
		} else {
			add(phm, conn);
		}
		
		close(conn);
		
	}
	
	private boolean prizeExists(PremiumsHonorableMention phm, Connection conn) {
		
		boolean exists = false;
		
		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {
		
			stmt1 = conn.createStatement();

			if (phm.getId() != -1) {
				String sql = "select id from honorablemention "
					+ "where id = " + phm.getId();
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
	
	private void update(PremiumsHonorableMention phm, Connection conn) {
		
		Statement stmt1 = null;

		try {
			
			String sql = "update honorablemention set "
				+ "classid = " + phm.getClassId() + ", "
				+ "number = " + phm.getNumber() + ", "
				+ "prize = " + prepString(phm.getPrize()) + " "
				+ "where id = " + phm.getId();
				
			stmt1.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt1);
		}
		
	}
	
	private void add(PremiumsHonorableMention phm, Connection conn) {
		
		boolean setId = false;
		Statement stmt1 = null;
		ResultSet rs = null;

		try {
			
			stmt1 = conn.createStatement();
			
			String sql = "insert into honorablemention (";
			
			if (phm.getId() != -1) {

				sql += "id, classid, "
					+ "number, prize) values ("
					+ phm.getId() + ", ";
				
			} else {
			
				setId = true;
				sql += "classid, "
					+ "number, prize) values (";
				
			}
			
			sql += phm.getClassId() + ", "
				+ phm.getNumber() + ", "
				+ prepString(phm.getPrize()) + ")";

			stmt1.executeUpdate(sql);

			if (setId) {
				
				sql = "select LAST_INSERT_ID()";
				rs = stmt1.executeQuery(sql);
				if (rs.next()) {
					phm.setId(rs.getInt(1));
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
