package com.amphibian.fair.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.amphibian.fair.db.dao.premiums.DepartmentDAO;
import com.amphibian.fair.premiums.PremiumsDepartment;

public class DepartmentExporter {

	public void doStuff(File f) {
		
		try {
			
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			
			o.write("<Departments>");
			
			DepartmentDAO dao = new DepartmentDAO();
			
			ArrayList list = dao.loadAll();
			
			Iterator it = list.iterator();
			while(it.hasNext()) {
				PremiumsDepartment d = (PremiumsDepartment)it.next();
				o.write(d.toStringXML());
				o.newLine();
			}

			o.write("</Departments>");
			o.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		
		DepartmentExporter de = new DepartmentExporter();
		de.doStuff(new File(args[0]));
		
	}

}
