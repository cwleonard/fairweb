package com.amphibian.fair.db.dao.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;

public class UserDAO extends BaseDAO {

	private final static String SELECT_ALL =
		"SELECT id, firstname, lastname, orgname, "
		+ "taxid, address, city, state, zip, "
		+ "phone, userid, password, email, webadmin FROM exhibitors";

	private final static String SELECT_BY_USERID =
		SELECT_ALL + " WHERE userid = ?";
	
	private final static String SELECT_BY_ID =
		SELECT_ALL + " WHERE id = ?";

	private final static String SELECT_BY_USERID_AND_PW =
		SELECT_ALL + " WHERE userid = ? AND password = ?";

	private final static String UPDATE_USER =
		"UPDATE allexhibitors SET "
		+ "firstname = ?, lastname = ?, orgname = ?, "
		+ "taxid = ?, address = ?, city = ?, state = ?, "
		+ "zip = ?, phone = ?, userid = ?, "
		+ "email = ?, webadmin = ? WHERE id = ?";
	
	private final static String CHECK_USERID =
		"SELECT id FROM allexhibitors WHERE userid = ?";

	private final static String DELETE =
		"insert into deletedexhibitors "
		+ "(exhibitorid) values (?)";
	
	private final static String UNDELETE =
		"DELETE FROM deletedexhibitors WHERE exhibitorid = ?";

	private final static String USER_EXISTS =
		"select id from allexhibitors where id = ?";
	
	private final static String SELECT_LAST_ID =
		"SELECT LAST_INSERT_ID()";
	
	public ArrayList loadAll() {
		
		ArrayList<User> ret = new ArrayList<User>();
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(SELECT_ALL);
			rs = stmt1.executeQuery();
			
			while(rs.next()) {
				ret.add(createUserFromResultSet(rs));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
			close(conn);
		}

		Collections.sort(ret);
		return ret;
		
	}
	
	public User loadByUserId(String userid) {
		
		User user = null;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(SELECT_BY_USERID);
			stmt1.setString(1, userid);

			rs = stmt1.executeQuery();
			
			if(rs.next()) {
				user = createUserFromResultSet(rs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
			close(conn);
		}
		
		return user;
		
	}

	public User loadById(String id) {
		
		User user = null;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(SELECT_BY_ID);
			stmt1.setString(1, id);

			rs = stmt1.executeQuery();
			
			if(rs.next()) {
				user = createUserFromResultSet(rs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
			close(conn);
		}
		
		return user;
		
	}
	
	public User load(String userId, String password) {
		
		User user = null;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {

			stmt1 = conn.prepareStatement(SELECT_BY_USERID_AND_PW);
			stmt1.setString(1, userId);
			stmt1.setString(2, password);
			
			rs = stmt1.executeQuery();
			
			if(rs.next()) {
				user = createUserFromResultSet(rs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
			close(conn);
		}
		
		return user;
		
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

	public boolean userIdInUse(String userid) {

		boolean used = false;
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.prepareStatement(CHECK_USERID);
			stmt.setString(1, userid);
			
			rs = stmt.executeQuery();
			used = rs.next();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}
		
		return used;
		
	}
	
	public void store(User user) {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		if (userExists(user, conn)) {
			update(user, conn);
		} else {
			add(user, conn);
		}
		
		close(conn);
		
	}
	
	private boolean userExists(User user, Connection conn) {
		
		boolean exists = false;
		
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		
		try {
			
			stmt1 = conn.prepareStatement(USER_EXISTS);
			stmt1.setInt(1, user.getId());
			
			if (user.getId() != -1) {
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

	private void update(User user, Connection conn) {
		
		PreparedStatement s = null;

		try {
			
			s = conn.prepareStatement(UPDATE_USER);

			s.setString(1, user.getFirstName());
			s.setString(2, user.getLastName());
			s.setString(3, user.getOrgName());
			s.setString(4, user.getTaxId());
			s.setString(5, user.getAddress());
			s.setString(6, user.getCity());
			s.setString(7, user.getState());
			s.setString(8, user.getZip());
			s.setString(9, user.getPhone());
			s.setString(10, user.getUserId());
			s.setString(11, user.getEmail());
			s.setBoolean(12, user.isWebAdmin());
			s.setInt(13, user.getId());
			
			s.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(s);
		}
		
	}

	private void add(User user, Connection conn) {

		boolean setId = false;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "insert into allexhibitors (";
			
			if (user.getId() != -1) {
				sql += "id, ";
			} else {
				setId = true;
			}
			
			sql += "firstname, lastname, "
				+ "orgname, taxid, address, "
				+ "city, state, zip, phone, "
				+ "userid, password, email, webadmin) values (";

			if (!setId) {
				sql += "?, ";
			}
			
			sql += "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			stmt1 = conn.prepareStatement(sql);
			
			int i = 1;
			if (!setId) {
				stmt1.setInt(i++, user.getId());
			}
			
			stmt1.setString(i++, user.getFirstName());
			stmt1.setString(i++, user.getLastName());
			stmt1.setString(i++, user.getOrgName());
			stmt1.setString(i++, user.getTaxId());
			stmt1.setString(i++, user.getAddress());
			stmt1.setString(i++, user.getCity());
			stmt1.setString(i++, user.getState());
			stmt1.setString(i++, user.getZip());
			stmt1.setString(i++, user.getPhone());
			stmt1.setString(i++, user.getUserId());
			stmt1.setString(i++, user.getPassword());
			stmt1.setString(i++, user.getEmail());
			stmt1.setBoolean(i++, user.isWebAdmin());
			
			stmt1.executeUpdate();
			
			if (setId) {

				stmt2 = conn.prepareStatement(SELECT_LAST_ID);
				rs = stmt2.executeQuery();
				if (rs.next()) {
					user.setId(rs.getInt(1));
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt1);
			close(stmt2);
		}
		
	}
	
	private void normalizePhoneNumbers() {
		
		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();

		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.createStatement();
			
			String sql = "select id, phone from allexhibitors";
			rs = stmt.executeQuery(sql);
			
			HashMap<String, String> map = new HashMap<String, String>();
			while(rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
			}
			
			close(rs);
			
			Iterator i = map.keySet().iterator();
			while(i.hasNext()) {
				String key = (String)i.next();
				String phone = (String)map.get(key);
				if (phone == null) phone = "";
				
				StringBuffer numsOnly = new StringBuffer();
				for (int c = 0; c < phone.length(); c++) {
					char x = phone.charAt(c);
					if (x >= 48 && x <= 57) {
						numsOnly.append(x);
					}
				}
				
				if (numsOnly.length() == 10) {
					numsOnly.insert(3, '-');
					numsOnly.insert(7, '-');
					System.out.println("converted " + phone + " to " + numsOnly);
				} else if (numsOnly.length() == 7) {
					numsOnly.insert(3, '-');
					numsOnly.insert(0, "717-");
					System.out.println("no area code...converted " + phone + " to " + numsOnly);
				} else {
					System.out.println("bad number: " + phone);
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}
		
	}
	
	private User createUserFromResultSet(ResultSet rs)
	throws SQLException
	{
		
		User user = new User();
		user.setId(rs.getInt(1));
		user.setFirstName(rs.getString(2));
		user.setLastName(rs.getString(3));
		user.setOrgName(rs.getString(4));
		user.setTaxId(rs.getString(5));
		user.setAddress(rs.getString(6));
		user.setCity(rs.getString(7));
		user.setState(rs.getString(8));
		user.setZip(rs.getString(9));
		user.setPhone(rs.getString(10));
		user.setUserId(rs.getString(11));
		user.setPassword(rs.getString(12));
		user.setEmail(rs.getString(13));
		user.setWebAdmin(rs.getBoolean(14));
		
		return user;
		
	}
	
	public static void main(String[] args) {
		
		UserDAO dao = new UserDAO();
		dao.normalizePhoneNumbers();
		
	}
	
}
