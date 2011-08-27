package com.amphibian.fair.beans;

public class PageText {

	private int id;
	
	private String section;

	private String text;
	
	/**
	 * Standard Constructor.
	 */
	public PageText() {
		id = -1;
		section = null;
		text = null;
	}
	
	public String toString() {
		return text;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	public String toStringXML() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append("<PageText id=\"");
		buf.append(id);
		buf.append("\">");
		buf.append("<Section>");
		if (section != null) {
			buf.append(section.replaceAll("&", "&amp;"));
		}
		buf.append("</Section>");
		buf.append("<Text>");
		if (text != null) {
			buf.append(text.replaceAll("&", "&amp;"));
		}
		buf.append("</Text>");
		buf.append("</PageText>");
		
		return buf.toString();
		
	}

	
}
