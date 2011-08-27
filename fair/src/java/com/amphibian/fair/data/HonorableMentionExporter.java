package com.amphibian.fair.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.amphibian.fair.db.dao.premiums.HonorableMentionDAO;
import com.amphibian.fair.premiums.PremiumsHonorableMention;

public class HonorableMentionExporter {
	
	public void doStuff(File f) {
		
		try {
			
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			
			o.write("<HonorableMentions>");
			
			HonorableMentionDAO dao = new HonorableMentionDAO();
			
			ArrayList list = dao.loadAll();
			
			Iterator it = list.iterator();
			while(it.hasNext()) {
				PremiumsHonorableMention p = (PremiumsHonorableMention)it.next();
				o.write(p.toStringXML());
				o.newLine();
			}

			o.write("</HonorableMentions>");
			o.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		HonorableMentionExporter pe = new HonorableMentionExporter();
		pe.doStuff(new File(args[0]));
		
	}
	
}
