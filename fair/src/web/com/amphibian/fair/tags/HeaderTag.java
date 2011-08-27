package com.amphibian.fair.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.amphibian.fair.beans.FairDay;
import com.amphibian.fair.beans.User;

public class HeaderTag extends TagSupport {
	
	private final static long serialVersionUID = 1L;

	private String navTab;
	
	/**
	 * @return the navTab
	 */
	public String getNavTab() {
		return navTab;
	}

	/**
	 * @param navTab the navTab to set
	 */
	public void setNavTab(String navTab) {
		this.navTab = navTab;
	}

	public int doStartTag() {
	
		try {
			
			HttpSession session = pageContext.getSession();
			User user = (User)session.getAttribute("UserObj");
			
			//TODO: replace with load from DB
			LinkedHashMap map = new LinkedHashMap();
			map.put("Home", "home.do");
			map.put("Schedule", "schedule.do");
			map.put("Premiums", "premiums.do");

			if (user != null) {
				if (user.isWebAdmin()) {
					map.put("Users", "accountManager.do");
				} else {
					map.put("You", "editAccount.do");
				}
			} else {
				map.put("You", "login.do");
			}
			
			map.put("About", "about.do");
			
			
			FairDay day1 = new FairDay(1);
			FairDay day8 = new FairDay(8);

			String dateRange = null;
			if (day1.getMonth().equals(day8.getMonth())) {
				dateRange = day1.getMonth() + " " + day1.getDayOfMonth()
						+ " - " + day8.getDayOfMonth() + ", " + day1.getYear();
			} else {
				dateRange = day1.getMonth() + " " + day1.getDayOfMonth()
				+ " - " + day8.getMonth() + " " + day8.getDayOfMonth() + ", " + day1.getYear();
			}
			
			String fairNumber = FairDay.getAnnuity();
			
			JspWriter out = pageContext.getOut();

			out.write("<div align=\"center\"> <!-- navigation -->\n");
			out.write("  <div align=\"center\" style=\"width: 100%;\">\n");
			out.write("    <div align=\"left\" id=\"annual\">The " + fairNumber + " Annual</div>\n");
			out.write("    <div align=\"left\" id=\"title\">Juniata County Fair</div>\n");			    
			out.write("    <div align=\"right\" id=\"dates\">" + dateRange + "</div>\n");
			out.write("  </div>\n");
			out.write("  <div id=\"navcontainer\" align=\"left\" valign=\"bottom\">\n");
			out.write("    <ul id=\"nav\">\n");
			
			Iterator it = map.keySet().iterator();
			while(it.hasNext()) {
				String key = (String)it.next();
				String val = (String)map.get(key);
				out.write("      <li");
				if (key.equalsIgnoreCase(navTab)) {
					out.write(" class=\"activelink\"");
				}
				out.write("><a href=\"" + val + "\">" + key + "</a></li>\n");
			}
			out.write("    </ul>\n");
			out.write("  </div>\n");
			out.write("</div> <!-- end navigation -->\n");
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return(SKIP_BODY);
		
	}
	
}
