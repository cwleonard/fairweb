package com.amphibian.fair.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.amphibian.fair.db.dao.premiums.SectionDAO;
import com.amphibian.fair.premiums.PremiumsSection;

public class SectionExporter {

	public void doStuff(File f) {
		
		try {
			
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			
			o.write("<Sections>");
			
			SectionDAO dao = new SectionDAO();
			
			ArrayList list = dao.loadAll();
			
			Iterator it = list.iterator();
			while(it.hasNext()) {
				PremiumsSection s = (PremiumsSection)it.next();
				o.write(s.toStringXML());
				o.newLine();
			}

			o.write("</Sections>");
			o.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		
		SectionExporter se = new SectionExporter();
		se.doStuff(new File(args[0]));
		
	}

}
