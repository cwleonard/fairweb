package com.amphibian.fair.db.dao.web;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.amphibian.fair.db.dao.BaseDAO;
import com.amphibian.fair.db.util.DatabaseConnectionManager;

public class LivestockEntryReportDAO extends BaseDAO {

	
	private static ArrayList livetockClasses;
	
	static {
		
		livetockClasses = new ArrayList();
		livetockClasses.add("2");
		livetockClasses.add("3");
		livetockClasses.add("4");
		livetockClasses.add("6");
		livetockClasses.add("9");
		livetockClasses.add("10.A-2");
		livetockClasses.add("10.A-3");
		livetockClasses.add("10.A-4");
		livetockClasses.add("10.A-6");
		livetockClasses.add("10.A-9");
		
	}
	

	public String getReport() {

		DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
		Connection conn = dbcm.getConnection();
		Statement stmt1 = null;
		Statement stmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuffer ret = new StringBuffer();
		
		try {

			Iterator it = livetockClasses.iterator();
			while(it.hasNext()) {

				String classPrefix = (String)it.next();

				ret.append("<b>Entries for Department ");
				ret.append(classPrefix);
				ret.append("</b><br/>\n\n");

				StringBuffer sql = new StringBuffer();
				sql.append("select id, if(length(orgname) > 0, orgname, concat(firstname, ' ', lastname)) as name, ");
				sql.append("concat(address, ', ', city, ' PA ', zip) as address ");
				sql.append("from exhibitors e ");
				sql.append("where id in (");
				sql.append("select exhibitorid ");
				sql.append("from exhibitorentries h ");
				sql.append("where h.entryid like '");
				sql.append(classPrefix);
				sql.append(".%')");

				try {

					stmt1 = conn.createStatement();
					rs1 = stmt1.executeQuery(sql.toString());
					while(rs1.next()) {

						String id = rs1.getString(1);
						String name = rs1.getString(2);
						String addr = rs1.getString(3);

						ret.append("<p>Exhibitor ");
						ret.append(id);
						ret.append(": ");
						ret.append(name);
						ret.append(", ");
						ret.append(addr);
						ret.append("</p>");
						ret.append("<blockquote>");

						String sql2 =
							"select animalname, dob, sire, dam, breeder " +
							"from exhibitorentries h " +
							"where exhibitorid = " + id;

						stmt2 = conn.createStatement();
						rs2 = stmt2.executeQuery(sql2);
						while(rs2.next()) {

							ret.append("<b>Animal Name:</b>&nbsp;");
							ret.append(rs2.getString(1));
							ret.append("<br/>");

							ret.append("<b>DOB: </b>&nbsp;");
							ret.append(rs2.getString(2));
							ret.append("<br/>");

							ret.append("<b>Sire:</b>&nbsp;");
							ret.append(rs2.getString(3));
							ret.append("<br/>");

							ret.append("<b>Dam:</b>&nbsp;");
							ret.append(rs2.getString(4));
							ret.append("<br/>");

							ret.append("<b>Breeder:</b>&nbsp;");
							ret.append(rs2.getString(5));
							ret.append("<br/>");
							ret.append("<br/>");

						}

						ret.append("</blockquote>");

					}
					
					ret.append("<hr/>");

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close(rs1);
					close(rs2);
					close(stmt1);
					close(stmt2);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		
		return ret.toString();
		
	}
	
}
