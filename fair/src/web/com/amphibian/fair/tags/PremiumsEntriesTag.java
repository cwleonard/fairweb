package com.amphibian.fair.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.premiums.ClassDAO;
import com.amphibian.fair.db.dao.premiums.EntryDAO;
import com.amphibian.fair.premiums.PremiumsClass;
import com.amphibian.fair.premiums.PremiumsEntry;

public class PremiumsEntriesTag extends TagSupport {

	private static final long serialVersionUID = 2025905349717674803L;

	public int doStartTag() {
		
		try {

			JspWriter out = pageContext.getOut();
			HttpSession session = pageContext.getSession();
			User user = (User)session.getAttribute("UserObj");
			
			if (user != null) {
				
				EntryDAO dao = new EntryDAO();
				ClassDAO cDAO = new ClassDAO();
				ArrayList entries = dao.loadAllByExhibitor(user);
				
				if (entries.size() == 0) {
					
					out.println("<p>You have not entered anyting.</p>");
					
				} else {
					
					out.println("<ul>");
					Iterator it = entries.iterator();
					while(it.hasNext()) {
						PremiumsEntry e = (PremiumsEntry)it.next();
						PremiumsClass c = cDAO.loadByCode(e.getEntryId());
						out.print("<li>");
						out.print("<a href=\"classDetail.do?id=" + c.getId() + "\">");
						out.print(e.getEntryId());
						out.print("</a>");
						out.println("</li>");
					}
					
					out.println("</ul>");
					
				}
				
				out.println("<p><a href=\"editAccount.do\">Edit Your Info</a></p>");
				
			} else {
				
				out.println("<form name=\"LoginForm\" method=\"post\" action=\"login.do\">");
				out.println("<input type=\"hidden\" name=\"returnToSender\" value=\"true\" />");

				out.println("<p>Already entered something? Log in with your email address and password ");
				out.println("to see your list of entries.</p>");
				      
				out.println("<p><input type=\"text\" name=\"userId\" /><br>");
				out.println("Email address</p>");

				out.println("<p><input type=\"password\" name=\"password\" /><br>");
				out.println("Password</p>");

				out.println("<p><input type=\"submit\" value=\"Submit\" /></p>");

				out.println("<p><a href=\"editAccount.do\">Create an Account</a></p>");

				out.println("<p><a href=\"forgotPassword.do\">Forgot your password? Click Here</a></p>");

				out.println("</form>");
				
			}
			
			
		} catch(IOException ioe) {
			System.out.println("Error in PremiumsEntries Start Tag: " + ioe);
		}
		
		return(SKIP_BODY);
		
	}
	
	
}
