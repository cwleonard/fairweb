package com.amphibian.fair.premiums.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.amphibian.fair.premiums.PremiumsEntry;

public class EntryForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String entryId;
	
	private String[] id;
	
	private String[] description;
	
	private String[] DOB;
	
	private String[] animalName;
	
	private String[] sire;
	
	private String[] dam;
	
	private String[] breeder;

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	/**
	 * @return Returns the animalName.
	 */
	public String[] getAnimalName() {
		return animalName;
	}

	/**
	 * @param animalName The animalName to set.
	 */
	public void setAnimalName(String[] animalName) {
		this.animalName = animalName;
	}

	/**
	 * @return Returns the breeder.
	 */
	public String[] getBreeder() {
		return breeder;
	}

	/**
	 * @param breeder The breeder to set.
	 */
	public void setBreeder(String[] breeder) {
		this.breeder = breeder;
	}

	/**
	 * @return Returns the dam.
	 */
	public String[] getDam() {
		return dam;
	}

	/**
	 * @param dam The dam to set.
	 */
	public void setDam(String[] dam) {
		this.dam = dam;
	}

	/**
	 * @return Returns the description.
	 */
	public String[] getDescription() {
		return description;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String[] description) {
		this.description = description;
	}

	/**
	 * @return Returns the dOB.
	 */
	public String[] getDOB() {
		return DOB;
	}

	/**
	 * @param dob The dOB to set.
	 */
	public void setDOB(String[] dob) {
		DOB = dob;
	}

	/**
	 * @return Returns the id.
	 */
	public String[] getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String[] id) {
		this.id = id;
	}

	/**
	 * @return Returns the sire.
	 */
	public String[] getSire() {
		return sire;
	}

	/**
	 * @param sire The sire to set.
	 */
	public void setSire(String[] sire) {
		this.sire = sire;
	}

	public ArrayList getEntryObjects() {
		
		ArrayList entries = new ArrayList();
		
		for (int i = 0; i < id.length; i++) {
			
			PremiumsEntry entry = new PremiumsEntry();
			if (id[i].length() > 0) {
				try {
					entry.setId(Integer.parseInt(id[i]));
				} catch (NumberFormatException e) {
					// ignore
				}
			}
			
			entry.setAnimalName(animalName[i]);
			entry.setBreeder(breeder[i]);
			entry.setSire(sire[i]);
			entry.setDam(dam[i]);
			entry.setDescription(description[i]);
			entry.setDOB(DOB[i]);
			entry.setEntryId(entryId);
			
			entries.add(entry);
			
		}
		
		return entries;
		
	}

}
