package com.amphibian.fair.premiums;

public class PremiumsEntry {

	private int id;
	
	private int exhibitorId;
	
	private String exhibitorName;
	
	private String entryId;
	
	private String description;
	
	private String DOB;
	
	private String animalName;
	
	private String sire;
	
	private String dam;
	
	private String breeder;
	
	private boolean printed;

	public PremiumsEntry() {
		id = -1;
		printed = false;
	}
	
	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public String getBreeder() {
		return breeder;
	}

	public void setBreeder(String breeder) {
		this.breeder = breeder;
	}

	public String getDam() {
		return dam;
	}

	public void setDam(String dam) {
		this.dam = dam;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dob) {
		DOB = dob;
	}

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	public int getExhibitorId() {
		return exhibitorId;
	}

	public void setExhibitorId(int exhibitorId) {
		this.exhibitorId = exhibitorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPrinted() {
		return printed;
	}

	public void setPrinted(boolean printed) {
		this.printed = printed;
	}

	public String getSire() {
		return sire;
	}

	public void setSire(String sire) {
		this.sire = sire;
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
	
	
	
}
