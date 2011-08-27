package com.amphibian.fair.premiums;

public class PremiumsDepartment implements Comparable{

	private int id;
	
	private String number;
	
	private String description;
	
	private String rules;
	
	private boolean livestock;

	public PremiumsDepartment() {
		id = -1;
		livestock = false;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isLivestock() {
		return livestock;
	}

	public void setLivestock(boolean livestock) {
		this.livestock = livestock;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}
	
	public boolean equals(Object o) {
		
		if (!(o instanceof PremiumsDepartment)) {
			return false;
		}
		
		PremiumsDepartment other = (PremiumsDepartment)o;
		
		return number.equals(other.number);
		
	}
	
	public int compareTo(Object o) {
		
		int ret = 0;
		PremiumsDepartment other = (PremiumsDepartment)o;
		
		String oNum = other.getNumber();
		try {
			int myIntNum = Integer.parseInt(number);
			int oIntNum = Integer.parseInt(oNum);
			ret = myIntNum - oIntNum;
		} catch (NumberFormatException e) {
			// must not be integers
			ret = number.compareTo(oNum);
		}
		
		return ret;
		
	}
	
	public String toStringXML() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append("<Department>");
		buf.append("<Id>").append(id).append("</Id>");
		buf.append("<Number>").append(number).append("</Number>");
		buf.append("<Description>").append(description.replaceAll("&", "&amp;")).append("</Description>");
		if (rules != null) {
			buf.append("<Rules>").append(rules.replaceAll("&", "&amp;")).append("</Rules>");
		} else {
			buf.append("<Rules>").append(rules).append("</Rules>");
		}
		buf.append("<Livestock>").append(livestock).append("</Livestock>");
		buf.append("</Department>");

		return buf.toString();
		
	}

	
}
