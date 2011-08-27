package com.amphibian.fair.premiums;

public class PremiumsSection {

	private int id;
	
	private String number;
	
	private String description;
	
	private int department;
	
	private String rules;
	
	private boolean livestock;

	public PremiumsSection() {
		id = -1;
		livestock = false;
	}
	
	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
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
	
	public String toStringXML() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append("<Section>");
		buf.append("<Id>").append(id).append("</Id>");
		buf.append("<Number>").append(number).append("</Number>");
		buf.append("<Description>").append(description.replaceAll("&", "&amp;")).append("</Description>");
		buf.append("<Department>").append(department).append("</Department>");
		if (rules != null) {
			buf.append("<Rules>").append(rules.replaceAll("&", "&amp;")).append("</Rules>");
		} else {
			buf.append("<Rules>").append(rules).append("</Rules>");
		}
		buf.append("<Livestock>").append(livestock).append("</Livestock>");
		buf.append("</Section>");

		return buf.toString();
		
	}
	
}
