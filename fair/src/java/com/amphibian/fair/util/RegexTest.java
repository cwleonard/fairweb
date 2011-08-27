package com.amphibian.fair.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String[] args) {
		
		String x = "14. In selecting vegetables for exhibition, choose such specimens that would bring the highest market price. Do not select the largest; select medium size specimens. If at all possible, do not mix varieties.";

		String regex = "^([0-9]+\\.\\s).*";
//		String blah = "^([0-9]+\\.\\s)";
//		
//		Pattern p = Pattern.compile(regex);
//		Matcher m = p.matcher(x);
//		boolean b = m.matches();
//		
		
		
		System.out.println(x.matches(regex));
		
	}
	
}
