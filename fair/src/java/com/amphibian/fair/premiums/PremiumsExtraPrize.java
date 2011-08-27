package com.amphibian.fair.premiums;

import java.text.NumberFormat;
import java.util.Currency;

public class PremiumsExtraPrize {

	private int id;
	
	private int classId;
	
	private int sectionId;
	
	private String name;
	
	private String prize;

	public PremiumsExtraPrize() {
		id = -1;
		classId = 0;
		sectionId = 0;
	}
	
	/**
	 * @return Returns the classId.
	 */
	public int getClassId() {
		return classId;
	}

	/**
	 * @param classId The classId to set.
	 */
	public void setClassId(int classId) {
		this.classId = classId;
	}

	/**
	 * @return Returns the id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}

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
	 * @return Returns the prize.
	 */
	public String getPrize() {
		return prize;
	}

	/**
	 * @param prize The prize to set.
	 */
	public void setPrize(String prize) {
		this.prize = prize;
	}

	/**
	 * @return Returns the sectionId.
	 */
	public int getSectionId() {
		return sectionId;
	}

	/**
	 * @param sectionId The sectionId to set.
	 */
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String toString() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append(name).append(": ");
		
		try {
			
			double np = Double.parseDouble(prize);

			NumberFormat nf = NumberFormat.getCurrencyInstance();
			Currency newCurrency = Currency.getInstance("USD");
			nf.setCurrency(newCurrency);
			String formattedValue = nf.format(np);

			buf.append(formattedValue);
			
		} catch (NumberFormatException e) {
			buf.append(prize);
		}

		return buf.toString();
		
	}
	
	public String toStringXML() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append("<ExtraPrize>");
		buf.append("<Id>").append(id).append("</Id>");
		buf.append("<ClassId>").append(classId).append("</ClassId>");
		buf.append("<SectionId>").append(sectionId).append("</SectionId>");
		buf.append("<Name>").append(name).append("</Name>");
		buf.append("<Prize>").append(prize.replaceAll("&", "&amp;")).append("</Prize>");
		buf.append("</ExtraPrize>");

		return buf.toString();
		
	}
	
	
}
