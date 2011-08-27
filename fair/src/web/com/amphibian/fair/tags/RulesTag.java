package com.amphibian.fair.tags;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;

import com.amphibian.fair.util.DateFormatter;

public class RulesTag extends TagSupport {

	private static final long serialVersionUID = -3445323255862877769L;

	private String name;
	
	private String property;
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the property.
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param property The property to set.
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	public int doStartTag()
	throws JspTagException
	{
		
		try {
			
			JspWriter out = pageContext.getOut();

			ServletRequest request = pageContext.getRequest();
			HttpSession session = pageContext.getSession();
			
			Object obj = request.getAttribute(name);
			if (obj == null) {
				obj = pageContext.getAttribute(name);
				if (obj == null) {
					obj = session.getAttribute(name);
					if (obj == null) {
						throw new JspTagException("Object " + name + " not found in any scope");
					}
				}
			}
			
			String strValue = null;
			
			if (property == null || property.length() == 0) {
				strValue = obj.toString();
			} else {
				try {
					Object obj2 = PropertyUtils.getSimpleProperty(obj, property);
					if (obj2 != null) {
						strValue = obj2.toString();
					} else {
						strValue = "";
					}
				} catch (Exception e) {
					throw new JspTagException("Object " + name + " does not have field " + property);
				}
			}

			if (strValue != null) {
				strValue = DateFormatter.format(strValue);
			}
			
			strValue = strValue.replaceAll("\\r", "");

			String[] paragraphs = strValue.split("\\n\\n");
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < paragraphs.length; i++) {
				String p = paragraphs[i].replaceAll("\\n", "<br/>");
				if (p.matches("^([0-9]+\\.\\s).*")) {
					buf.append("<p class=\"outdent\">");
				} else {
					buf.append("<p>");
				}
				buf.append(p);
				buf.append("</p>\n");
			}

			out.println(buf.toString());
			
		} catch(IOException ioe) {
			System.out.println("Error in Rules Start Tag: " + ioe);
		}
		
		return(SKIP_BODY);
		
	}

	
	
}
