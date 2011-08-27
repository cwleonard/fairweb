package com.amphibian.fair.data;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.amphibian.fair.db.dao.premiums.ExtraPrizeDAO;
import com.amphibian.fair.premiums.PremiumsExtraPrize;

public class ExtraPrizeImporter {
	
	public void importFromFile(File f) {

		int totalInFile = 0;
		int totalImported = 0;
		
		try {
			
			
			ExtraPrizeDAO dao = new ExtraPrizeDAO();
			
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(f);
			
			Element root = doc.getRootElement();
			
			List l = root.getChildren("ExtraPrize");
			totalInFile = l.size();
			Iterator it = l.iterator();
			while (it.hasNext()) {
				
				try {
					
					Element e = (Element)it.next();
					
					Element idElement = e.getChild("Id");
					Element classIdElement = e.getChild("ClassId");
					Element sectionIdElement = e.getChild("SectionId");
					Element nameElement = e.getChild("Name");
					Element prizeElement = e.getChild("Prize");
					
					PremiumsExtraPrize p = new PremiumsExtraPrize();
					
					p.setId(Integer.parseInt(idElement.getText()));
					p.setClassId(Integer.parseInt(classIdElement.getText()));
					p.setSectionId(Integer.parseInt(sectionIdElement.getText()));
					p.setName(nameElement.getText());
					p.setPrize(prizeElement.getText());
					
					dao.store(p);
					
					totalImported++;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Total extra prizes in file  = " + totalInFile);
		System.out.println("Total extra prizes imported = " + totalImported);
		
	}
	
	public static void main(String[] args) {
		
		ExtraPrizeImporter pi = new ExtraPrizeImporter();
		pi.importFromFile(new File(args[0]));
		
	}
	
}
