package com.amphibian.fair.db.dao.premiums;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;
import com.amphibian.fair.premiums.PremiumsClass;
import com.amphibian.fair.premiums.PremiumsExtraPrize;
import com.amphibian.fair.premiums.PremiumsSection;

public class ExtraPrizeDAO extends BaseDAO {

	public ArrayList loadAllBySection(PremiumsSection ps) {

		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		ArrayList ret = loadAllBySection(conn, ps);
		close(conn);
		return ret;
		
	}
	
	public ArrayList loadAllBySection(Connection conn, PremiumsSection ps) {
		
		ArrayList ret = new ArrayList();
		
		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.createStatement();

			String sql = "select id, classid, "
				+ "sectionid, name, prize "
				+ "from extraprizes";
			
			if (ps != null) {
				sql += " where sectionid = " + ps.getId();
			}

			rs = stmt1.executeQuery(sql);
			
			while(rs.next()) {
				
				PremiumsExtraPrize p = new PremiumsExtraPrize();
				p.setId(rs.getInt(1));
				p.setClassId(rs.getInt(2));
				p.setSectionId(rs.getInt(3));
				p.setName(rs.getString(4));
				p.setPrize(rs.getString(5));
				
				ret.add(p);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
		}
		
		return ret;

		
	}
	
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
				+ "sectionid, name, prize "
				+ "from extraprizes";
			
			if (pc != null) {
				sql += " where classid = " + pc.getId();
			}

			rs = stmt1.executeQuery(sql);
			
			while(rs.next()) {
				
				PremiumsExtraPrize p = new PremiumsExtraPrize();
				p.setId(rs.getInt(1));
				p.setClassId(rs.getInt(2));
				p.setSectionId(rs.getInt(3));
				p.setName(rs.getString(4));
				p.setPrize(rs.getString(5));
				
				ret.add(p);
				
			}
			

			
			if (ret.size() == 0) {
				
				if (pc.getParent() > 0) {
					ClassDAO cd = new ClassDAO();
					PremiumsClass parent = cd.loadById(String.valueOf(pc.getParent()), false, conn);
					ret = parent.getExtraPrizes();
				} else if (pc.getSection() > 0) {
					SectionDAO sd = new SectionDAO();
					PremiumsSection parent = sd.loadById(pc.getSection());
					ret = loadAllBySection(conn, parent);
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
			
			String sql = "delete from extraprizes "
				+ "where id = " + id;
			
			stmt1.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt1);
			close(conn);
		}
		
	}
	
	public void store(PremiumsExtraPrize pep) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		if (prizeExists(pep, conn)) {
			update(pep, conn);
		} else {
			add(pep, conn);
		}
		
		close(conn);
		
	}
	
	private boolean prizeExists(PremiumsExtraPrize pep, Connection conn) {
		
		boolean exists = false;
		
		Statement stmt1 = null;
		ResultSet rs = null;
		
		try {
		
			stmt1 = conn.createStatement();

			if (pep.getId() != -1) {
				String sql = "select id from extraprizes "
					+ "where id = " + pep.getId();
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
	
	private void update(PremiumsExtraPrize pep, Connection conn) {
		
		Statement stmt1 = null;

		try {
			
			String sql = "update extraprizes set "
				+ "classid = " + pep.getClassId() + ", "
				+ "sectionid = " + pep.getSectionId() + ", "
				+ "name = " + prepString(pep.getName()) + ", "
				+ "prize = " + prepString(pep.getPrize()) + " "
				+ "where id = " + pep.getId();
				
			stmt1.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt1);
		}
		
	}
	
	private void add(PremiumsExtraPrize pep, Connection conn) {
		
		boolean setId = false;
		Statement stmt1 = null;
		ResultSet rs = null;

		try {
			
			stmt1 = conn.createStatement();
			
			String sql = "insert into extraprizes (";
			
			if (pep.getId() != -1) {

				sql += "id, classid, sectionid, "
					+ "name, prize) values ("
					+ pep.getId() + ", ";
				
			} else {
			
				setId = true;
				sql += "classid, sectionid, "
					+ "place, prize) values (";
				
			}
			
			sql += pep.getClassId() + ", "
				+ pep.getSectionId() + ", "
				+ prepString(pep.getName()) + ", "
				+ prepString(pep.getPrize()) + ")";

			stmt1.executeUpdate(sql);

			if (setId) {
				
				sql = "select LAST_INSERT_ID()";
				rs = stmt1.executeQuery(sql);
				if (rs.next()) {
					pep.setId(rs.getInt(1));
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
