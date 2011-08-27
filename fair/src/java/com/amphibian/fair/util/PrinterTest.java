package com.amphibian.fair.util;

import java.awt.print.PrinterJob;

public class PrinterTest {

	
	public static void main(String[] args) {
		
		PrinterJob printJob = PrinterJob.getPrinterJob();
		
		 if (printJob.printDialog()) {
		      try {
		         printJob.print();
		      } 
		      catch (Exception PrintException) {
		         PrintException.printStackTrace();
		      }
		   }
		
		
	}
	
}
