package com.amphibian.fair.tags;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.amphibian.fair.premiums.PremiumsClass;

public class ClassesDisplayTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;

	private static final String ENTRY_URL = "classDetail.do";
	
	private PremiumsClass pClass;
	
	public void setPremiumsClass(Object o) {
		pClass = (PremiumsClass)o;
	}
	
	private void writeHTML(JspWriter out, PremiumsClass c)
	throws IOException
	{
		
		if (c.getChildren() == null) {
			out.println("<a href=\"" + ENTRY_URL + "?id=" + c.getId() + "\">" + c.getNumber() + ". " + c.getDescription() + "</a><br>");
		} else { 
			out.print("<a name=\"class" + c.getShortCode() + "\" />");
			out.println(c.getNumber() + ". " + c.getDescription() + "<br>");
			ArrayList list = c.getChildren();
			out.println("<blockquote>");
			for (int i = 0; i < list.size(); i++) {
				writeHTML(out, (PremiumsClass)list.get(i));
			}
			out.println("</blockquote>");
		}
		
	}
	
	public int doStartTag() {
	
		try {
			
			JspWriter out = pageContext.getOut();

			if (pClass.getChildren() == null) {
				out.println("<p><b>Class " + pClass.getNumber() + "</b> : <a href=\"" + ENTRY_URL + "?id=" + pClass.getId() + "\">" + pClass.getDescription() + "</a></p>");
			} else {
				out.println("<p><a name=\"class" + pClass.getNumber() + "\" /><b>Class " + pClass.getNumber() + "</b> : " + pClass.getDescription() + "</p>");
				ArrayList list = pClass.getChildren();
				out.println("<blockquote>");
				if (pClass.getRules() != null && pClass.getRules().length() > 0) {
					out.println(pClass.getRules(true));
				}
				for (int i = 0; i < list.size(); i++) {
					writeHTML(out, (PremiumsClass)list.get(i));
				}
				out.println("</blockquote>");
			}
			
		} catch(IOException ioe) {
			System.out.println("Error in PipeBox Start Tag: " + ioe);
		}
		
		return(SKIP_BODY);
		
	}
	
}
