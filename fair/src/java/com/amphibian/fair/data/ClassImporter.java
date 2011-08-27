package com.amphibian.fair.data;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.amphibian.fair.db.dao.premiums.ClassDAO;
import com.amphibian.fair.premiums.PremiumsClass;

public class ClassImporter {
	
	public void importFromFile(File f) {

		int totalInFile = 0;
		int totalImported = 0;
		
		try { 
			
			
			ClassDAO dao = new ClassDAO();
			
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(f);
			
			Element root = doc.getRootElement();
			
			List l = root.getChildren("Class");
			totalInFile = l.size();
			Iterator it = l.iterator();
			while (it.hasNext()) {
				
				try {
					
					Element e = (Element)it.next();
					
					Element idElement = e.getChild("Id");
					Element numberElement = e.getChild("Number");
					Element codeElement = e.getChild("Code");
					Element descriptionElement = e.getChild("Description");
					Element sectionElement = e.getChild("Section");
					Element parentElement = e.getChild("Parent");
					Element rulesElement = e.getChild("Rules");
					Element allowEntryElement = e.getChild("AllowEntry");
					Element maxEntryElement = e.getChild("MaxEntry");
					
					PremiumsClass c = new PremiumsClass();
					
					c.setId(Integer.parseInt(idElement.getText()));
					c.setNumber(numberElement.getText());
					c.setCode(codeElement.getText());
					c.setDescription(descriptionElement.getText());
					c.setSection(Integer.parseInt(sectionElement.getText()));
					c.setParent(Integer.parseInt(parentElement.getText()));
					c.setRules(rulesElement.getText());
					c.setAllowEntry((new Boolean(allowEntryElement.getText())).booleanValue());
					c.setMaxEntry(Integer.parseInt(maxEntryElement.getText()));
					
					dao.store(c);
					
					totalImported++;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Total classes in file  = " + totalInFile);
		System.out.println("Total classes imported = " + totalImported);
		
	}
	
	
	public void doStuff() {
		
		PremiumsClass c = new PremiumsClass();
		
		//c.setId(3000);
		c.setAllowEntry(true);
		c.setCode("1.2.3.5");
		c.setDescription("test description");
		c.setNumber("5");
		c.setParent(44);
		c.setRules("rules text");
		c.setSection(0);
		
		
		System.out.println("new id is = " + c.getId());
		
	}
	
	public static void main(String[] args) {
		
		ClassImporter ci = new ClassImporter();
		ci.importFromFile(new File(args[0]));
		
	}
	
}
