package com.amphibian.fair.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.amphibian.fair.db.dao.premiums.ClassDAO;
import com.amphibian.fair.premiums.PremiumsClass;

public class ClassExporter {
	
	public void doStuff(File f) {
		
		try {
			
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			
			o.write("<Classes>");
			
			ClassDAO dao = new ClassDAO();
			
			ArrayList list = dao.loadAll();
			
			Iterator it = list.iterator();
			while(it.hasNext()) {
				PremiumsClass c = (PremiumsClass)it.next();
				o.write(c.toStringXML());
				o.newLine();
			}

			o.write("</Classes>");
			o.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		ClassExporter ce = new ClassExporter();
		ce.doStuff(new File(args[0]));
		
	}
	
}
