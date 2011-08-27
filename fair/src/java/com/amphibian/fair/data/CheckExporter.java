package com.amphibian.fair.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;

import com.amphibian.fair.beans.Check;
import com.amphibian.fair.db.dao.premiums.CheckDAO;

public class CheckExporter {

	private final static String ACCOUNT_NAME = "Omega-Premium Account";
	
	public void doStuff(File f) {
		
		try {
			
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			
			CheckDAO dao = new CheckDAO();
			
			ArrayList list = dao.loadAll();
			
			DecimalFormat nf = (DecimalFormat)NumberFormat.getNumberInstance();
			
			nf.applyPattern("#.00");
			//nf.setMinimumFractionDigits(2);
			//nf.setDecimalSeparatorAlwaysShown(true);
			
			Iterator it = list.iterator();
			while(it.hasNext()) {
				
				Check chk = (Check)it.next();

				o.write("!TRNS\tTRNSID\tTRNSTYPE\tDATE\tACCNT\tNAME\tCLASS\tAMOUNT\tDOCNUM\tCLEAR\tTOPRINT\tADDR5");
				o.newLine();
				
				o.write("!SPL\tSPLID\tTRNSTYPE\tDATE\tACCNT\tNAME\tCLASS\tAMOUNT\tDOCNUM\tCLEAR\tQNTY\tREIMBEXP");
				o.newLine();
				
				o.write("!ENDTRNS");
				o.newLine();
				
				o.write("TRNS\t\tCHECK\t9/7/2006\t");
				o.write(ACCOUNT_NAME);
				o.write("\t");
				o.write(chk.getExhibitorName());
				o.write("\t\t");
				o.write("-" + nf.format(chk.getAmount()) + "\t");
				o.write(chk.getNumber() + "\t");
				o.write("N\tN\t");
				o.newLine();
				
				o.write("SPL\t\tCHECK\t9/7/2006\t");
				o.write("Premiums\t");
				o.write("Premiums Award\t");
				o.write("Premiums Class\t");
				o.write(nf.format(chk.getAmount()) + "\t");
				o.write(chk.getNumber() + "\t");
				o.write("N\t\tNOTHING");
				o.newLine();
				
				o.write("ENDTRNS");
				o.newLine();
				
			}

			o.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		CheckExporter ce = new CheckExporter();
		ce.doStuff(new File(args[0]));
		
	}

}
