package com.amphibian.fair.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PipeBoxTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;

	private int width;
	
	private String title = "";
	
	public void setWidth(String w) {
		width = Integer.parseInt(w);
	}
	
	public void setTitle(String t) {
		title = t;
	}
	
	public void setTitle(Object o) {
		title = o.toString();
	}
	
	public int doStartTag() {
		
		try {
			
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			String contextRoot = request.getContextPath();
			
			JspWriter out = pageContext.getOut();

			out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"" + width + "\">");
			out.println("<tbody><tr>");
			out.println("<td width=\"15\"><img alt=\"\" src=\"" + contextRoot + "/images/top_left.gif\" border=\"0\" height=\"15\" width=\"15\" /></td>");
			out.println("<td class=\"top\" width=\"" + (width - 30) + "\"><img alt=\"\" src=\"" + contextRoot + "/images/top.gif\" border=\"0\" height=\"15\" width=\"2\" /></td>");
			out.println("<td width=\"15\"><img alt=\"\" src=\"" + contextRoot + "/images/top_right.gif\" border=\"0\" height=\"15\" width=\"15\" /></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"lmiddle\" width=\"15\"><img alt=\"\" src=\"" + contextRoot + "/images/middle_left.gif\" border=\"0\" height=\"2\" width=\"15\" /></td>");
			out.println("<td align=\"center\" width=\"" + (width - 30) + "\"><span class=\"boxtitle\">" + title + "</span>");

			if (title.length() > 0) {
				
				out.println("<div align=\"center\">");
				out.println("<center>");
				out.println("<table bgcolor=\"#87bae5\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
				out.println("<tbody><tr>");
				out.println("<td width=\"100%\"><img alt=\"\" src=\"" + contextRoot + "/images/transpixel.gif\" border=\"0\" height=\"3\" width=\"3\" /></td>");
				out.println("</tr>");
				out.println("</tbody></table>");
				out.println("</center>");
				out.println("</div>");
				
			}
			
		} catch(IOException ioe) {
			System.out.println("Error in PipeBox Start Tag: " + ioe);
		}
		
		return(EVAL_BODY_INCLUDE);
		
	}
	
	public int doEndTag() {
		
		try {
			
			JspWriter out = pageContext.getOut();

			out.println("</td>");
			out.println("<td class=\"rmiddle\" width=\"15\"><img alt=\"\" src=\"images/middle_right.gif\" border=\"0\" height=\"2\" width=\"15\" /></td>");
			out.println("</tr>");
		    out.println("<tr>");
		    out.println("<td width=\"15\"><img alt=\"\" src=\"images/bottom_left.gif\" border=\"0\" height=\"15\" width=\"15\" /></td>");
		    out.println("<td class=\"bottom\" width=\"" + (width - 30) + "\"><img alt=\"\" src=\"images/bottom.gif\" border=\"0\" height=\"15\" width=\"2\" /></td>");
		    out.println("<td width=\"15\"><img alt=\"\" src=\"images/bottom_right.gif\" border=\"0\" height=\"15\" width=\"15\" /></td>");
		    out.println("</tr>");
		    out.println("</tbody></table>");
			
		} catch(IOException ioe) {
			System.out.println("Error in PipeBox End Tag: " + ioe);
		}
		
		return(EVAL_PAGE);
		
	}
	
}
