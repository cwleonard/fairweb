package com.amphibian.fair.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class FairDay {

	private final static int BASE_YEAR = 1854;
	
	public final static String DEFAULT_ADMISSION = "$3.00";
	
	private int day;
	
	private int year;
	
	private String title;
	
	private String sponsor;
	
	private String admission;
	
	private String startTime;
	
	private ArrayList items;
	
	private ArrayList images;
	
	private Calendar calendar;
	
	public FairDay(int d) {
		
		items = new ArrayList();
		images = new ArrayList();
		admission = DEFAULT_ADMISSION;
		
		day = d;
		if (day > 8) day = 8;
		if (day < 1) day = 1;
		year = -1;
		calculateDate();
		
	}
	
	public static int getDayOfFair(Calendar cal) {
		
		FairDay dayOne = new FairDay(1);
		dayOne.setYear(cal.get(Calendar.YEAR));
		
		int todayNum = cal.get(Calendar.DAY_OF_YEAR);
		int fairDayNum = dayOne.calendar.get(Calendar.DAY_OF_YEAR);
		
		return 1 + (todayNum - fairDayNum);		
	
	}
	
	private void calculateDate() {

		calendar = new GregorianCalendar();
		if (year != -1) {
			calendar.set(Calendar.YEAR, year);
		}
		calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		while(calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);		
		}
		
		// calendar now represents the first monday in september, Labor Day
		// subtract day-3 to get the day of the fair we want
		calendar.add(Calendar.DATE , day-3);

	}
	
	public int daysUntil() {
		
		Calendar today = new GregorianCalendar();
		
		int todayNum = today.get(Calendar.DAY_OF_YEAR);
		int fairDayNum = calendar.get(Calendar.DAY_OF_YEAR);
		
		return fairDayNum - todayNum;
		
	}
	
	public static String getAnnuity(int year) {
		
		String annuity = String.valueOf(year - BASE_YEAR);
		String numSuffix = "th";
		if (annuity.endsWith("1")) {
			numSuffix = "st";
		} else if (annuity.endsWith("2")) {
			numSuffix = "nd";
		} else if (annuity.endsWith("3")) {
			numSuffix = "rd";
		}
		
		return annuity + numSuffix;

	}
	
	public static String getAnnuity() {
		
		Calendar today = new GregorianCalendar();
		int year = today.get(Calendar.YEAR);
		return getAnnuity(year);
		
	}
	
	/**
	 * @return the sponsor
	 */
	public String getSponsor() {
		return sponsor;
	}

	/**
	 * @param sponsor the sponsor to set
	 */
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
		calculateDate();
	}
	
	public void setYear(int year) {
		this.year = year;
		calculateDate();
	}
	
	public int getYear() {
		return this.calendar.get(Calendar.YEAR);
	}
	
	public String getAdmission() {
		return admission;
	}

	public void setAdmission(String admission) {
		this.admission = admission;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = new ArrayList(items);
	}
	
	public void addItem(ScheduleItem item) {
		this.items.add(item);
	}
	
	public void addImage(String image) {
		this.images.add(image);
	}
	
	public Iterator getImagesIterator() {
		return this.images.iterator();
	}
	
	public List getImages() {
		return this.images;
	}
	
	public Iterator getItemsIterator() {
		return this.items.iterator();
	}

	public void addDays(int days) {
		calendar.add(Calendar.DAY_OF_YEAR, days);
	}
	
	public void subtractDays(int days) {
		calendar.add(Calendar.DAY_OF_YEAR, -days);
	}
	
	public String getMonth() {
		
		String monthString = null;
		int month = calendar.get(Calendar.MONTH);
		if (month == Calendar.AUGUST) {
			monthString = "August";
		} else if (month == Calendar.SEPTEMBER) {
			monthString = "September";
		} else {
			monthString = "Impossible";
		}

		return monthString;
		
	}
	
	public int getDayOfMonth() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		int weekday = calendar.get(Calendar.DAY_OF_WEEK);
		if (weekday == Calendar.MONDAY) {
			sb.append("Monday");
		} else if (weekday == Calendar.TUESDAY) {
			sb.append("Tuesday");
		} else if (weekday == Calendar.WEDNESDAY) {
			sb.append("Wednesday");
		} else if (weekday == Calendar.THURSDAY) {
			sb.append("Thursday");
		} else if (weekday == Calendar.FRIDAY) {
			sb.append("Friday");
		} else if (weekday == Calendar.SATURDAY) {
			sb.append("Saturday");
		} else if (weekday == Calendar.SUNDAY) {
			sb.append("Sunday");
		} else {
			sb.append("Nonexistantday");
		}
		
		sb.append(", ");
		sb.append(this.getMonth());
		
		sb.append(" ");
		sb.append(this.getDayOfMonth());
		
		return sb.toString();
		
	}

	public static void main(String[] args) {
		
		FairDay d = new FairDay(1);
		System.out.println(d);
		
		d = new FairDay(8);
		System.out.println(d);
		
	}
	
	
}
