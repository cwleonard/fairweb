package com.amphibian.fair.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.amphibian.fair.db.dao.premiums.PrizeDAO;
import com.amphibian.fair.premiums.PremiumsPrize;

public class PrizeExporter {
	
	public void doStuff(File f) {
		
		try {
			
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			
			o.write("<Prizes>");
			
			PrizeDAO dao = new PrizeDAO();
			
			ArrayList list = dao.loadAll();
			
			Iterator it = list.iterator();
			while(it.hasNext()) {
				PremiumsPrize p = (PremiumsPrize)it.next();
				o.write(p.toStringXML());
				o.newLine();
			}

			o.write("</Prizes>");
			o.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		PrizeExporter pe = new PrizeExporter();
		pe.doStuff(new File(args[0]));
		
	}
	
}
