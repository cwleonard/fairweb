package com.amphibian.fair.tags;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.premiums.EntryDAO;

public class PersonalTag extends TagSupport {

	private static final long serialVersionUID = 2025905349717674803L;

	public int doStartTag() {
		
		try {

			JspWriter out = pageContext.getOut();
			HttpSession session = pageContext.getSession();
			User user = (User)session.getAttribute("UserObj");

			out.println("<div id=\"checklogin\">");

			if (user != null) {

				out.println("<p>Logged in as " + user + ".</p>");

				out.println("<p><a href=\"logout.do\">Click here to log out.</a></p>");

				EntryDAO edao = new EntryDAO();
				ArrayList entries = edao.loadAllByExhibitor(user);
				if (entries.size() > 0) {
					
					out.println("<p>You currently have " + entries.size() + " items entered. <a href=\"yourEntries.do\">Click here to view your entries.</a></p>");
					
				}
				
				
			} else {

				out.println("<p>You are not currently logged in.</p>");
				out.println("<p>If you'd like, you can <a href=\"login.do?returnToSender=true\">log in now</a> or <a href=\"editAccount.do\">create an account</a>.</p>");			      
				
			}
			
			out.println("</div>");

		} catch(IOException ioe) {
			System.out.println("Error in PremiumsEntries Start Tag: " + ioe);
		}
		
		return(SKIP_BODY);
		
	}
	
	
}
