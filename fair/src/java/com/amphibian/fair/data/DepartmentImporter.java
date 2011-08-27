package com.amphibian.fair.data;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.amphibian.fair.db.dao.premiums.DepartmentDAO;
import com.amphibian.fair.premiums.PremiumsDepartment;

public class DepartmentImporter {

	public void importFromFile(File f) {

		int totalInFile = 0;
		int totalImported = 0;
		
		try {
			
			
			DepartmentDAO dao = new DepartmentDAO();
			
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(f);
			
			Element root = doc.getRootElement();
			
			List l = root.getChildren("Department");
			totalInFile = l.size();
			Iterator it = l.iterator();
			while (it.hasNext()) {
				
				try {
					
					Element e = (Element)it.next();
					
					Element idElement = e.getChild("Id");
					Element numberElement = e.getChild("Number");
					Element descriptionElement = e.getChild("Description");
					Element rulesElement = e.getChild("Rules");
					Element livestockElement = e.getChild("Livestock");
					
					PremiumsDepartment d = new PremiumsDepartment();
					
					d.setId(Integer.parseInt(idElement.getText()));
					d.setNumber(numberElement.getText());
					d.setDescription(descriptionElement.getText());
					d.setRules(rulesElement.getText());
					d.setLivestock((new Boolean(livestockElement.getText())).booleanValue());
					
					dao.store(d);
					
					totalImported++;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Total departments in file  = " + totalInFile);
		System.out.println("Total departments imported = " + totalImported);
		
	}
	
	public static void main(String[] args) {
		
		DepartmentImporter di = new DepartmentImporter();
		di.importFromFile(new File(args[0]));
		
	}

}
