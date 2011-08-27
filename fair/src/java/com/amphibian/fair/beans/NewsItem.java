package com.amphibian.fair.beans;

public class NewsItem {

	private int id;
	
	private String postDate;
	
	private String text;

	/**
	 * Standard Constructor.
	 */
	public NewsItem() {
		id = -1;
		postDate = null;
		text = null;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		return postDate + " - " + text;
	}
	
	public String toStringXML() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append("<NewsItem id=\"");
		buf.append(id);
		buf.append("\">");
		buf.append("<PostDate>");
		buf.append(postDate);
		buf.append("</PostDate>");
		buf.append("<Text>");
		buf.append(text.replaceAll("&", "&amp;"));
		buf.append("</Text>");
		buf.append("<DisplayText>");
		buf.append(toString().replaceAll("&", "&amp;"));
		buf.append("</DisplayText>");
		buf.append("</NewsItem>");
		
		return buf.toString();
		
	}
	
}
