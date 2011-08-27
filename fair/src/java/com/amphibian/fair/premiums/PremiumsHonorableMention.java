package com.amphibian.fair.premiums;

import java.text.NumberFormat;
import java.util.Currency;

public class PremiumsHonorableMention {

	private int id;
	
	private int classId;
	
	private int number;
	
	private String prize;

	public PremiumsHonorableMention() {
		id = -1;
		classId = 0;
		number = 0;
	}
	
	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}
	
	public String toString() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append(number);
		buf.append(" Honorable Mention");

		if (number > 1) {
			buf.append("s, each worth ");
		} else {
			buf.append(", worth ");
		}
		
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
		
		buf.append("<HonorableMention>");
		buf.append("<Id>").append(id).append("</Id>");
		buf.append("<ClassId>").append(classId).append("</ClassId>");
		buf.append("<Number>").append(number).append("</Number>");
		buf.append("<Prize>").append(prize.replaceAll("&", "&amp;")).append("</Prize>");
		buf.append("</HonorableMention>");

		return buf.toString();
		
	}
	
	
}
