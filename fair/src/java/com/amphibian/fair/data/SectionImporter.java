package com.amphibian.fair.data;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.amphibian.fair.db.dao.premiums.SectionDAO;
import com.amphibian.fair.premiums.PremiumsSection;

public class SectionImporter {

	public void importFromFile(File f) {

		int totalInFile = 0;
		int totalImported = 0;
		
		try {
			
			
			SectionDAO dao = new SectionDAO();
			
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(f);
			
			Element root = doc.getRootElement();
			
			List l = root.getChildren("Section");
			totalInFile = l.size();
			Iterator it = l.iterator();
			while (it.hasNext()) {
				
				try {
					
					Element e = (Element)it.next();
					
					Element idElement = e.getChild("Id");
					Element numberElement = e.getChild("Number");
					Element descriptionElement = e.getChild("Description");
					Element departmentElement = e.getChild("Department");
					Element rulesElement = e.getChild("Rules");
					Element livestockElement = e.getChild("Livestock");
					
					PremiumsSection s = new PremiumsSection();
					
					s.setId(Integer.parseInt(idElement.getText()));
					s.setNumber(numberElement.getText());
					s.setDescription(descriptionElement.getText());
					s.setDepartment(Integer.parseInt(departmentElement.getText()));
					s.setRules(rulesElement.getText());
					s.setLivestock((new Boolean(livestockElement.getText())).booleanValue());
					
					dao.store(s);
					
					totalImported++;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Total sections in file  = " + totalInFile);
		System.out.println("Total sections imported = " + totalImported);
		
	}
	
	public static void main(String[] args) {
		
		SectionImporter si = new SectionImporter();
		si.importFromFile(new File(args[0]));
		
	}

}
