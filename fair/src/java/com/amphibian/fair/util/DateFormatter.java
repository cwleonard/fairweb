package com.amphibian.fair.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.amphibian.fair.beans.FairDay;

public class DateFormatter {

	public static String format(String s) {
		
		StringBuffer buf = new StringBuffer();
		
		int startFrom = 0;
		int idx = s.indexOf("${");
		while (idx != -1) {

			try {
				
				buf.append(s.substring(startFrom, idx));
				
				int endBrace = s.indexOf("}", idx);
				
				String code = s.substring(idx+2, endBrace);
				
				//System.out.println("code = \"" + code + "\"");
				
				if (code.startsWith("YEAR")) {

					Calendar calendar = new GregorianCalendar();
					int currentYear = calendar.get(Calendar.YEAR);

					if (code.length() > 5) {
						
						char op = code.charAt(4);
						int val = Integer.parseInt(code.substring(5, code.length()));
					
						if (op == '-') {
							currentYear = currentYear - val;
						} else if (op == '+') {
							currentYear = currentYear + val;
						}
					
						buf.append(currentYear);
						
					} else {
						buf.append(currentYear);
					}
					
				} else if (code.startsWith("FAIRDAY")) {
					
					Calendar calendar = new GregorianCalendar();
					int currentYear = calendar.get(Calendar.YEAR);

					if (code.length() > 9) {
						
						String day = code.substring(7, 8);
						int d = Integer.parseInt(day);
						
						FairDay fday = new FairDay(d);

						char op = code.charAt(8);
						int val = Integer.parseInt(code.substring(9, code.length()));
						if (op == '-') {
							fday.subtractDays(val);
						} else if (op == '+') {
							fday.addDays(val);
						}
						
						buf.append(fday.toString());
						buf.append(", ");
						buf.append(currentYear);
						
					} else if (code.length() == 8) {
						
						String day = code.substring(7, 8);
						int d = Integer.parseInt(day);
						
						FairDay fday = new FairDay(d);
						buf.append(fday.toString());
						buf.append(", ");
						buf.append(currentYear);
						
					}
					
				}
				
				idx = s.indexOf("${", idx+1);
				
				if (idx == -1) {
					buf.append(s.substring(endBrace+1, s.length()));
				}
				
				startFrom = endBrace+1;
				
			} catch (Exception e) {
				e.printStackTrace();
				idx = -1;
			}
			
		}
		
		if (buf.length() == 0) {
			buf.append(s);
		}
		
		return buf.toString();
		
	}
	
	
	public static void main(String[] args) {
		
		//String blah = "rules rules whatnot whatnot ${YEAR-3} blah blah ${YEAR-1} blah something";
		//String blah = "no dates inside";
		
		String blah = "blah blah ${FAIRDAY1-2} whatnot";
		
		String formatted = DateFormatter.format(blah);
		
		System.out.println(formatted);
		
	}
	
}
