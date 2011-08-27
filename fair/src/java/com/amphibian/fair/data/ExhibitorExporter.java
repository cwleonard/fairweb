package com.amphibian.fair.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.web.UserDAO;

public class ExhibitorExporter {
	
	public void doStuff(File f) {
		
		try {
			
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			
			o.write("<Exhibitors>");
			
			UserDAO dao = new UserDAO();
			
			ArrayList list = dao.loadAll();
			
			Iterator it = list.iterator();
			while(it.hasNext()) {
				User u = (User)it.next();
				o.write(u.toStringXML());
				o.newLine();
			}

			o.write("</Exhibitors>");
			o.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		ExhibitorExporter pe = new ExhibitorExporter();
		pe.doStuff(new File(args[0]));
		
	}
	
}
