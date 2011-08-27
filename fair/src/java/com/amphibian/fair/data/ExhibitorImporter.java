package com.amphibian.fair.data;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.web.UserDAO;

public class ExhibitorImporter {
	
	public void importFromFile(File f) {

		int totalInFile = 0;
		int totalImported = 0;
		
		try {
			
			
			UserDAO dao = new UserDAO();
			
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(f);
			
			Element root = doc.getRootElement();
			
			List l = root.getChildren("Exhibitor");
			totalInFile = l.size();
			Iterator it = l.iterator();
			while (it.hasNext()) {
				
				try {
					
					Element e = (Element)it.next();
					
					Element idElement = e.getChild("Id");
					Element fnElement = e.getChild("FirstName");
					Element lnElement = e.getChild("LastName");
					Element onElement = e.getChild("OrgName");
					Element taxIdElement = e.getChild("TaxId");
					Element addrElement = e.getChild("Address");
					Element cityElement = e.getChild("City");
					Element stateElement = e.getChild("State");
					Element zipElement = e.getChild("Zip");
					Element phoneElement = e.getChild("Phone");
					Element uidElement = e.getChild("UserId");
					Element pwdElement = e.getChild("Password");
					Element adminElement = e.getChild("WebAdmin");

					User u = new User();
					
					u.setId(Integer.parseInt(idElement.getText()));
					u.setFirstName(fnElement.getText());
					u.setLastName(lnElement.getText());
					u.setOrgName(onElement.getText());
					u.setTaxId(taxIdElement.getText());
					u.setAddress(addrElement.getText());
					u.setCity(cityElement.getText());
					u.setState(stateElement.getText());
					u.setZip(zipElement.getText());
					u.setPhone(phoneElement.getText());
					u.setUserId(uidElement.getText());
					u.setPassword(pwdElement.getText());
					u.setWebAdmin((Boolean.valueOf(adminElement.getText())).booleanValue());
					
					if (u.getUserId().length() > 0 && dao.userIdInUse(u.getUserId())) {
						System.out.println("--Duplicate email \"" + u.getUserId() + "\" not imported!");
					} else {
						dao.store(u);
					}
					
					totalImported++;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Total users in file  = " + totalInFile);
		System.out.println("Total users imported = " + totalImported);
		
	}
	
	public static void main(String[] args) {
		
		ExhibitorImporter pi = new ExhibitorImporter();
		pi.importFromFile(new File(args[0]));
		
	}
	
}
