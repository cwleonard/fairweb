package com.amphibian.fair.premiums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import com.amphibian.fair.util.DateFormatter;

public class PremiumsClass implements Comparable {

	private int id;
	
	private String number;
	
	private String code;
	
	private String description;
	
	private int section;
	
	private int parent;
	
	private String rules;
	
	private ArrayList children;
	
	private ArrayList prizes;
	
	private ArrayList extraPrizes;
	
	private PremiumsHonorableMention honorableMention;
	
	private boolean allowEntry;
	
	private int maxEntry;
	
	private boolean livestock;
	
	public PremiumsClass() {
		id = -1;
		maxEntry = 1;
	}
	
	public boolean isAllowEntry() {
		return allowEntry;
	}

	public void setAllowEntry(boolean allowEntry) {
		this.allowEntry = allowEntry;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortCode() {
		String sc = code;
		int firstDot = sc.indexOf('.');
		if (firstDot != -1) {
			int secondDot = sc.indexOf('.', firstDot+1);
			if (secondDot != -1) {
				sc = sc.substring(secondDot+1, sc.length());
			}
		}
		return sc;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = DateFormatter.format(description);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxEntry() {
		return maxEntry;
	}

	public void setMaxEntry(int maxEntry) {
		this.maxEntry = maxEntry;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public String getRules() {
		return rules;
	}
	
	public String getRules(boolean format) {

		if (!format || rules == null) {
			return rules;
		} else {
			
			String s = DateFormatter.format(rules);
			s = s.replaceAll("\\r", "");

			String[] paragraphs = s.split("\\n\\n");
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

			return buf.toString();
			
		}
		
		
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}
	
	public ArrayList getChildren() {
		return children;
	}

	public void setChildren(ArrayList children) {
		this.children = children;
		if (this.children != null) {
			Collections.sort(this.children);
		}
	}

	public void addChild(PremiumsClass c) {
		if (this.children == null) {
			this.children = new ArrayList();
		}
		this.children.add(c);
		Collections.sort(this.children);
	}
	
	/**
	 * @return Returns the prizes.
	 */
	public ArrayList getPrizes() {
		return prizes;
	}

	/**
	 * @param prizes The prizes to set.
	 */
	public void setPrizes(ArrayList prizes) {
		this.prizes = prizes;
	}

	/**
	 * @return Returns the honorableMention.
	 */
	public PremiumsHonorableMention getHonorableMention() {
		return honorableMention;
	}

	/**
	 * @param honorableMention The honorableMention to set.
	 */
	public void setHonorableMention(PremiumsHonorableMention honorableMention) {
		this.honorableMention = honorableMention;
	}

	/**
	 * @return Returns the extraPrizes.
	 */
	public ArrayList getExtraPrizes() {
		return extraPrizes;
	}

	/**
	 * @param extraPrizes The extraPrizes to set.
	 */
	public void setExtraPrizes(ArrayList extraPrizes) {
		this.extraPrizes = extraPrizes;
	}

	/**
	 * @return Returns the livestock.
	 */
	public boolean isLivestock() {
		return livestock;
	}

	/**
	 * @param livestock The livestock to set.
	 */
	public void setLivestock(boolean livestock) {
		this.livestock = livestock;
	}

	
	
	public boolean equals(Object other) {
		
		if (other instanceof PremiumsClass) {

			PremiumsClass o = (PremiumsClass)other;
			
			if (this.code != null && o.code != null) {
				return this.code.equals(o.code);
			} else {
				return false;
			}

		} else {
			return false;
		}
		
	}
	
	public int hashCode() {
		
		if (this.code != null) {
			return this.code.hashCode();
		} else {
			return 0;
		}
		
	}
	
	public String toStringXML() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append("<Class>");
		buf.append("<Id>").append(id).append("</Id>");
		buf.append("<Number>").append(number).append("</Number>");
		buf.append("<Code>").append(code).append("</Code>");
		buf.append("<Description>").append(description.replaceAll("&", "&amp;")).append("</Description>");
		buf.append("<Section>").append(section).append("</Section>");
		buf.append("<Parent>").append(parent).append("</Parent>");
		if (rules != null) {
			buf.append("<Rules>").append(rules.replaceAll("&", "&amp;")).append("</Rules>");
		} else {
			buf.append("<Rules>").append(rules).append("</Rules>");
		}
		buf.append("<AllowEntry>").append(allowEntry).append("</AllowEntry>");
		buf.append("<MaxEntry>").append(maxEntry).append("</MaxEntry>");
		if (this.children != null && this.children.size() > 0) {
			buf.append("<Children>");
			Iterator it = this.children.iterator();
			while(it.hasNext()) {
				PremiumsClass c = (PremiumsClass)it.next();
				buf.append(c.toStringXML());
			}
			buf.append("</Children>");
		}
		buf.append("</Class>");

		return buf.toString();
		
	}

	public int compareTo(Object other) {

		PremiumsClass o = (PremiumsClass)other;
		
		if (this.number != null && o.number != null) {
			
			if (this.number.equals(o.number)) {
				return 0;
			} else {
				
				try {
					
					Integer myNum = new Integer(this.number);
					Integer oNum = new Integer(o.number);
					return myNum.compareTo(oNum);
					
				} catch (NumberFormatException nfe) {
					
					return this.number.compareTo(o.number);
					
				}
				
			}
			
		} else {

			if (this.number == null) {
				return -1;
			} else {
				return 1;
			}
			
		}
		
	}
	
}
