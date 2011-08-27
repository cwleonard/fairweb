package com.amphibian.fair.beans;

public class ScheduleItem {

	private String id;
	
	private int day;
	
	private String description;
	
	private String startTime;
	
	private String stopTime;
	
	private boolean allDay;

	/**
	 * @return the allDay
	 */
	public boolean isAllDay() {
		return allDay;
	}

	/**
	 * @param allDay the allDay to set
	 */
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the stopTime
	 */
	public String getStopTime() {
		return stopTime;
	}

	/**
	 * @param stopTime the stopTime to set
	 */
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String toString() {
		
		String ret = "";
		
		if (!allDay && startTime != null) {
			ret += startTime;
			if (stopTime != null) {
				ret += " - " + stopTime;
			}
			ret += ", ";
		}
		
		ret += description;
		
		return ret;
		
	}
	
	public String getRdfString() {
		
		String ret = "";
		
		if (!allDay && startTime != null) {
			ret += "<span property=\"v:startDate\" content=\"\">" + startTime + "</span>";
			if (stopTime != null) {
				ret += " - " + stopTime;
			}
			ret += ", ";
		}
		
		ret += "<span property=\"v:description\">" + description + "</span>";
		
		return ret;
		
	}
	
	public String toStringXML() {
		
		StringBuffer buf = new StringBuffer();

		buf.append("<ScheduleItem day=\"");
	    buf.append(day);
		buf.append("\" id=\"");
		buf.append(id);
		buf.append("\">");
		buf.append("<Description>");
		if (description != null) {
			buf.append(description.replaceAll("&", "&amp;"));
		}
		buf.append("</Description>");
		buf.append("<StartTime>");
		if (startTime != null && !allDay) {
			buf.append(startTime);
		}
		buf.append("</StartTime>");
		buf.append("<StopTime>");
		if (stopTime != null && !allDay) {
			buf.append(stopTime);
		}
		buf.append("</StopTime>");
		buf.append("<AllDay>");
		buf.append(allDay);
		buf.append("</AllDay>");
		buf.append("<DisplayText>");
		buf.append(this.toString().replaceAll("&", "&amp;"));
		buf.append("</DisplayText>");
		buf.append("</ScheduleItem>");
		
		return buf.toString();
		
	}
	
}
