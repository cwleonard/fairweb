/*
 * Check.java
 */
package com.amphibian.fair.beans;

public class Check {

	private int number;
	
	private int exhibitorId;
	
	private String exhibitorName;
	
	private String exhibitorAddress;
	
	private String exhibitorCityStateZip;

	private double amount;

	private String filter;

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the exhibitorAddress
	 */
	public String getExhibitorAddress() {
		return exhibitorAddress;
	}

	/**
	 * @param exhibitorAddress the exhibitorAddress to set
	 */
	public void setExhibitorAddress(String exhibitorAddress) {
		this.exhibitorAddress = exhibitorAddress;
	}

	/**
	 * @return the exhibitorCityStateZip
	 */
	public String getExhibitorCityStateZip() {
		return exhibitorCityStateZip;
	}

	/**
	 * @param exhibitorCityStateZip the exhibitorCityStateZip to set
	 */
	public void setExhibitorCityStateZip(String exhibitorCityStateZip) {
		this.exhibitorCityStateZip = exhibitorCityStateZip;
	}

	/**
	 * @return the exhibitorId
	 */
	public int getExhibitorId() {
		return exhibitorId;
	}

	/**
	 * @param exhibitorId the exhibitorId to set
	 */
	public void setExhibitorId(int exhibitorId) {
		this.exhibitorId = exhibitorId;
	}

	/**
	 * @return the exhibitorName
	 */
	public String getExhibitorName() {
		return exhibitorName;
	}

	/**
	 * @param exhibitorName the exhibitorName to set
	 */
	public void setExhibitorName(String exhibitorName) {
		this.exhibitorName = exhibitorName;
	}

	/**
	 * @return the filter
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
}
