package com.amphibian.fair.db.dao.premiums;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;
import com.amphibian.fair.premiums.PremiumsEntry;

public class EntryDAO extends BaseDAO {

	private final static String LOAD_BY_CODE_AND_EXHIBITOR =
		"SELECT id, entryid, description, "
		+ "dob, animalname, sire, "
		+ "dam, breeder, printed "
		+ "FROM exhibitorentries "
		+ "WHERE entryid = ? "
		+ "AND exhibitorid = ?";

	private final static String LOAD_BY_ID =
		"SELECT id, entryid, description, "
		+ "dob, animalname, sire, "
		+ "dam, breeder, printed "
		+ "FROM exhibitorentries "
		+ "WHERE id = ?";

	private final static String DELETE =
		"INSERT INTO deletedentries (id) VALUES (?)";

	private final static String UNDELETE =
		"DELETE FROM deletedentries WHERE id = ?";
	
	private final static String CHECK_EXISTS =
		"SELECT id FROM allexhibitorentries WHERE id = ?";
	
	private final static String UPDATE =
		"UPDATE allexhibitorentries SET "
			+ "exhibitorid = ?, entryid = ?, "
			+ "description = ?, dob = ?, "
			+ "animalname = ?, sire = ?, "
			+ "dam = ?, breeder = ?, printed = ? "
			+ "where id = ?";

	private final static String INSERT_WITH_ID =
		"INSERT INTO allexhibitorentries ("
		+ "id, exhibitorid, entryid, description, "
		+ "dob, animalname, sire, "
		+ "dam, breeder, printed) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String INSERT_WITHOUT_ID =
		"INSERT INTO allexhibitorentries ("
		+ "exhibitorid, entryid, description, "
		+ "dob, animalname, sire, "
		+ "dam, breeder, printed) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private final static String LOAD_ALL_WITH_NAMES =
		"SELECT e1.id, e1.exhibitorid, "
		+ "e1.entryid, e1.description, "
		+ "e1.dob, e1.animalname, e1.sire, "
		+ "e1.dam, e1.breeder, e1.printed, "
		+ "e2.firstname, e2.lastname, e2.orgname "
		+ "FROM exhibitorentries e1, exhibitors e2 "
		+ "WHERE e1.exhibitorid = e2.id";

	private final static String LOAD_ALL_WITHOUT_NAMES =
		"SELECT id, exhibitorid, entryid, description, "
		+ "dob, animalname, sire, dam, breeder, printed "
		+ "FROM exhibitorentries";

	
	public ArrayList loadAllWithNames() {
		return this.loadAllByExhibitor(null, true, null);
	}

	public ArrayList loadAllWithNames(String filter) {
		return this.loadAllByExhibitor(null, true, filter);
	}
	
	public ArrayList loadAllByExhibitor(User exhibitor) {
		return this.loadAllByExhibitor(exhibitor, false, null);
	}
	
	public ArrayList loadAllByExhibitor(User exhibitor, boolean loadNames, String filter) {
		
		ArrayList<PremiumsEntry> ret = new ArrayList<PremiumsEntry>();
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			String sql = null;
			
			if (loadNames) {
				sql = LOAD_ALL_WITH_NAMES;
				if (exhibitor != null) {
					sql += " and e1.exhibitorid = ?";
				}
				if (filter != null) {
					sql += " and e1.entryid like '" + filter + "%'";
				}
			} else {
				sql = LOAD_ALL_WITHOUT_NAMES;
				if (exhibitor != null) {
					sql += " where exhibitorid = ?";
				}
				if (filter != null) {
					if (exhibitor == null) {
						sql += " where";
					} else {
						sql += " and";
					}
					sql += " entryid like '" + filter + "%'";
				}
			}

			stmt1 = conn.prepareStatement(sql);
			if (exhibitor != null) {
				stmt1.setInt(1, exhibitor.getId());
			}

			rs = stmt1.executeQuery();
			
			while(rs.next()) {
				
				PremiumsEntry e = new PremiumsEntry();
				e.setId(rs.getInt(1));
				e.setExhibitorId(rs.getInt(2));
				e.setEntryId(rs.getString(3));
				e.setDescription(rs.getString(4));
				e.setDOB(rs.getString(5));
				e.setAnimalName(rs.getString(6));
				e.setSire(rs.getString(7));
				e.setDam(rs.getString(8));
				e.setBreeder(rs.getString(9));
				e.setPrinted(rs.getBoolean(10));

				if (loadNames) {
					
					String fname = rs.getString(11);
					String lname = rs.getString(12);
					String oname = rs.getString(13);
					
					if (oname != null && oname.length() > 0) {
						e.setExhibitorName(oname);
					} else {
						e.setExhibitorName(fname + " " + lname);
					}
					
				}
				
				ret.add(e);
				
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
		return this.loadAllByExhibitor(null);
	}

	public ArrayList loadByCodeAndExhibitor(String code, User user) {
		
		ArrayList<PremiumsEntry> ret = new ArrayList<PremiumsEntry>();
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_BY_CODE_AND_EXHIBITOR);
			stmt1.setString(1, code);
			stmt1.setInt(2, user.getId());
			
			rs = stmt1.executeQuery();
			
			while(rs.next()) {
				
				PremiumsEntry pe = new PremiumsEntry();
				pe.setId(rs.getInt(1));
				pe.setEntryId(rs.getString(2));
				pe.setDescription(rs.getString(3));
				pe.setDOB(rs.getString(4));
				pe.setAnimalName(rs.getString(5));
				pe.setSire(rs.getString(6));
				pe.setDam(rs.getString(7));
				pe.setBreeder(rs.getString(8));
				pe.setPrinted(rs.getBoolean(9));
				
				ret.add(pe);
				
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
	
	public PremiumsEntry loadById(int id) {
		
		PremiumsEntry pe = null;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(LOAD_BY_ID);
			stmt1.setInt(1, id);
			
			rs = stmt1.executeQuery();
			
			while(rs.next()) {
				
				pe = new PremiumsEntry();
				pe.setId(rs.getInt(1));
				pe.setEntryId(rs.getString(2));
				pe.setDescription(rs.getString(3));
				pe.setDOB(rs.getString(4));
				pe.setAnimalName(rs.getString(5));
				pe.setSire(rs.getString(6));
				pe.setDam(rs.getString(7));
				pe.setBreeder(rs.getString(8));
				pe.setPrinted(rs.getBoolean(9));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
			close(conn);
		}

		return pe;
		
	}
	
	public void delete(int id) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		PreparedStatement stmt1 = null;

		try {
			
			stmt1 = conn.prepareStatement(DELETE);
			stmt1.setInt(1, id);
			
			stmt1.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt1);
			close(conn);
		}
		
	}
	
	public void unDelete(int id) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		PreparedStatement stmt1 = null;

		try {
			
			stmt1 = conn.prepareStatement(UNDELETE);
			stmt1.setInt(1, id);
			
			stmt1.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt1);
			close(conn);
		}
		
	}
	
	public void store(PremiumsEntry pe) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		if (entryExists(pe, conn)) {
			update(pe, conn);
		} else {
			add(pe, conn);
		}
		
		close(conn);
		
	}
	
	private boolean entryExists(PremiumsEntry pe, Connection conn) {
		
		boolean exists = false;
		
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {
			
			if (pe.getId() != -1) {
				stmt1 = conn.prepareStatement(CHECK_EXISTS);
				stmt1.setInt(1, pe.getId());
				rs = stmt1.executeQuery();
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
	
	private void update(PremiumsEntry pe, Connection conn) {
		
		PreparedStatement stmt1 = null;

		try {
			
			stmt1 = conn.prepareStatement(UPDATE);
			
			stmt1.setInt(1, pe.getExhibitorId());
			stmt1.setString(2, pe.getEntryId());
			stmt1.setString(3, pe.getDescription());
			stmt1.setString(4, pe.getDOB());
			stmt1.setString(5, pe.getAnimalName());
			stmt1.setString(6, pe.getSire());
			stmt1.setString(7, pe.getDam());
			stmt1.setString(8, pe.getBreeder());
			stmt1.setBoolean(9, pe.isPrinted());
			stmt1.setInt(10, pe.getId());
			
			stmt1.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt1);
		}
		
	}
	
	private void add(PremiumsEntry pe, Connection conn) {
		
		boolean setId = false;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;

		try {
			
			String sql = null;
			if (pe.getId() != -1) {
				sql = INSERT_WITH_ID;
			} else {
				sql = INSERT_WITHOUT_ID;
				setId = true;
			}
			
			stmt1 = conn.prepareStatement(sql);

			int index = 1;
			if (!setId) {
				stmt1.setInt(index++, pe.getId());
			}
			stmt1.setInt(index++, pe.getExhibitorId());
			stmt1.setString(index++, pe.getEntryId());
			stmt1.setString(index++, pe.getDescription());
			stmt1.setString(index++, pe.getDOB());
			stmt1.setString(index++, pe.getAnimalName());
			stmt1.setString(index++, pe.getSire());
			stmt1.setString(index++, pe.getDam());
			stmt1.setString(index++, pe.getBreeder());
			stmt1.setBoolean(index++, pe.isPrinted());
			
			stmt1.executeUpdate();

			if (setId) {
				pe.setId(getLastId(conn));
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
