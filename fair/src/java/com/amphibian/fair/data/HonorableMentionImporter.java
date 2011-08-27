package com.amphibian.fair.data;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.amphibian.fair.db.dao.premiums.HonorableMentionDAO;
import com.amphibian.fair.premiums.PremiumsHonorableMention;

public class HonorableMentionImporter {
	
	public void importFromFile(File f) {

		int totalInFile = 0;
		int totalImported = 0;
		
		try {
			
			
			HonorableMentionDAO dao = new HonorableMentionDAO();
			
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(f);
			
			Element root = doc.getRootElement();
			
			List l = root.getChildren("HonorableMention");
			totalInFile = l.size();
			Iterator it = l.iterator();
			while (it.hasNext()) {
				
				try {
					
					Element e = (Element)it.next();
					
					Element idElement = e.getChild("Id");
					Element classIdElement = e.getChild("ClassId");
					Element numberElement = e.getChild("Number");
					Element prizeElement = e.getChild("Prize");
					
					PremiumsHonorableMention p = new PremiumsHonorableMention();
					
					p.setId(Integer.parseInt(idElement.getText()));
					p.setClassId(Integer.parseInt(classIdElement.getText()));
					p.setNumber(Integer.parseInt(numberElement.getText()));
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
		
		System.out.println("Total honorable mentions in file  = " + totalInFile);
		System.out.println("Total honorable mentions imported = " + totalImported);
		
	}
	
	public static void main(String[] args) {
		
		HonorableMentionImporter pi = new HonorableMentionImporter();
		pi.importFromFile(new File(args[0]));
		
	}
	
}
