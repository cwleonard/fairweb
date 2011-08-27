package com.amphibian.fair.beans;

import java.io.InputStream;

public class DownloadItem {

	private int id;
	
	private String description;

	private String file;
	
	private InputStream is;
	
	private int fileLength;
	
	private String fileName;
	
	/**
	 * Standard Constructor.
	 */
	public DownloadItem() {
		id = -1;
		description = null;
		file = null;
		is = null;
	}
	
	public String toString() {
		return description + " (" + file + ")";
	}
	
	public String toStringXML() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append("<DownloadItem id=\"");
		buf.append(id);
		buf.append("\">");
		buf.append("<Description>");
		if (description != null) {
			buf.append(description.replaceAll("&", "&amp;"));
		}
		buf.append("</Description>");
		buf.append("</DownloadItem>");
		
		return buf.toString();
		
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
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
	 * @return the is
	 */
	public InputStream getInputStream() {
		return is;
	}

	/**
	 * @param is the is to set
	 */
	public void setInputStream(InputStream is) {
		this.is = is;
	}

	/**
	 * @return the fileLength
	 */
	public int getFileLength() {
		return fileLength;
	}

	/**
	 * @param fileLength the fileLength to set
	 */
	public void setFileLength(int fileLength) {
		this.fileLength = fileLength;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
