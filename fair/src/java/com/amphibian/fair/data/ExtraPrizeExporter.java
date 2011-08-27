package com.amphibian.fair.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.amphibian.fair.db.dao.premiums.ExtraPrizeDAO;
import com.amphibian.fair.premiums.PremiumsExtraPrize;

public class ExtraPrizeExporter {
	
	public void doStuff(File f) {
		
		try {
			
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			
			o.write("<ExtraPrizes>");
			
			ExtraPrizeDAO dao = new ExtraPrizeDAO();
			
			ArrayList list = dao.loadAll();
			
			Iterator it = list.iterator();
			while(it.hasNext()) {
				PremiumsExtraPrize p = (PremiumsExtraPrize)it.next();
				o.write(p.toStringXML());
				o.newLine();
			}

			o.write("</ExtraPrizes>");
			o.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		ExtraPrizeExporter pe = new ExtraPrizeExporter();
		pe.doStuff(new File(args[0]));
		
	}
	
}
