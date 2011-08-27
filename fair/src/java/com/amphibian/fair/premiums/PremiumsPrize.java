package com.amphibian.fair.premiums;

import java.text.NumberFormat;
import java.util.Currency;

public class PremiumsPrize {

	private int id;
	
	private int classId;
	
	private int place;
	
	private String prize;

	public PremiumsPrize() {
		id = -1;
		classId = 0;
		place = 0;
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

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}
	
	public String toString() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append(place);
		String pstr = String.valueOf(place);
		if (pstr.endsWith("1")) {
			buf.append("st");
		} else if (pstr.endsWith("2")) {
			buf.append("nd");
		} else if (pstr.endsWith("3")) {
			buf.append("rd");
		} else {
			buf.append("th");
		}
		buf.append(" Place: ");
		
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
		
		buf.append("<Prize>");
		buf.append("<Id>").append(id).append("</Id>");
		buf.append("<ClassId>").append(classId).append("</ClassId>");
		buf.append("<Place>").append(place).append("</Place>");
		buf.append("<Prize>").append(prize.replaceAll("&", "&amp;")).append("</Prize>");
		buf.append("</Prize>");

		return buf.toString();
		
	}
	
	
}
