package com.amphibian.fair.data;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.amphibian.fair.db.dao.premiums.PrizeDAO;
import com.amphibian.fair.premiums.PremiumsPrize;

public class PrizeImporter {
	
	public void importFromFile(File f) {

		int totalInFile = 0;
		int totalImported = 0;
		
		try {
			
			
			PrizeDAO dao = new PrizeDAO();
			
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(f);
			
			Element root = doc.getRootElement();
			
			List l = root.getChildren("Prize");
			totalInFile = l.size();
			Iterator it = l.iterator();
			while (it.hasNext()) {
				
				try {
					
					Element e = (Element)it.next();
					
					Element idElement = e.getChild("Id");
					Element classIdElement = e.getChild("ClassId");
					Element placeElement = e.getChild("Place");
					Element prizeElement = e.getChild("Prize");
					
					PremiumsPrize p = new PremiumsPrize();
					
					p.setId(Integer.parseInt(idElement.getText()));
					p.setClassId(Integer.parseInt(classIdElement.getText()));
					p.setPlace(Integer.parseInt(placeElement.getText()));
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
		
		System.out.println("Total prizes in file  = " + totalInFile);
		System.out.println("Total prizes imported = " + totalImported);
		
	}
	
	public static void main(String[] args) {
		
		PrizeImporter pi = new PrizeImporter();
		pi.importFromFile(new File(args[0]));
		
	}
	
}
