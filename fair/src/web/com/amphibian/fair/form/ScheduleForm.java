package com.amphibian.fair.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ScheduleForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String day1Admission;
	private String day2Admission;
	private String day3Admission;
	private String day4Admission;
	private String day5Admission;
	private String day6Admission;
	private String day7Admission;
	private String day8Admission;
	
	private String day1Start;
	private String day2Start;
	private String day3Start;
	private String day4Start;
	private String day5Start;
	private String day6Start;
	private String day7Start;
	private String day8Start;
	
	private String[] itemDay1;
	private String[] itemDay2;
	private String[] itemDay3;
	private String[] itemDay4;
	private String[] itemDay5;
	private String[] itemDay6;
	private String[] itemDay7;
	private String[] itemDay8;
	
	
	
	public String getDay1Admission() {
		return day1Admission;
	}



	public void setDay1Admission(String day1Admission) {
		this.day1Admission = day1Admission;
	}



	public String getDay1Start() {
		return day1Start;
	}



	public void setDay1Start(String day1Start) {
		this.day1Start = day1Start;
	}



	public String getDay2Admission() {
		return day2Admission;
	}



	public void setDay2Admission(String day2Admission) {
		this.day2Admission = day2Admission;
	}



	public String getDay2Start() {
		return day2Start;
	}



	public void setDay2Start(String day2Start) {
		this.day2Start = day2Start;
	}



	public String getDay3Admission() {
		return day3Admission;
	}



	public void setDay3Admission(String day3Admission) {
		this.day3Admission = day3Admission;
	}



	public String getDay3Start() {
		return day3Start;
	}



	public void setDay3Start(String day3Start) {
		this.day3Start = day3Start;
	}



	public String getDay4Admission() {
		return day4Admission;
	}



	public void setDay4Admission(String day4Admission) {
		this.day4Admission = day4Admission;
	}



	public String getDay4Start() {
		return day4Start;
	}



	public void setDay4Start(String day4Start) {
		this.day4Start = day4Start;
	}



	public String getDay5Admission() {
		return day5Admission;
	}



	public void setDay5Admission(String day5Admission) {
		this.day5Admission = day5Admission;
	}



	public String getDay5Start() {
		return day5Start;
	}



	public void setDay5Start(String day5Start) {
		this.day5Start = day5Start;
	}



	public String getDay6Admission() {
		return day6Admission;
	}



	public void setDay6Admission(String day6Admission) {
		this.day6Admission = day6Admission;
	}



	public String getDay6Start() {
		return day6Start;
	}



	public void setDay6Start(String day6Start) {
		this.day6Start = day6Start;
	}



	public String getDay7Admission() {
		return day7Admission;
	}



	public void setDay7Admission(String day7Admission) {
		this.day7Admission = day7Admission;
	}



	public String getDay7Start() {
		return day7Start;
	}



	public void setDay7Start(String day7Start) {
		this.day7Start = day7Start;
	}



	public String getDay8Admission() {
		return day8Admission;
	}



	public void setDay8Admission(String day8Admission) {
		this.day8Admission = day8Admission;
	}



	public String getDay8Start() {
		return day8Start;
	}



	public void setDay8Start(String day8Start) {
		this.day8Start = day8Start;
	}



	public String[] getItemDay1() {
		return itemDay1;
	}



	public void setItemDay1(String[] itemDay1) {
		this.itemDay1 = itemDay1;
	}



	public String[] getItemDay2() {
		return itemDay2;
	}



	public void setItemDay2(String[] itemDay2) {
		this.itemDay2 = itemDay2;
	}



	public String[] getItemDay3() {
		return itemDay3;
	}



	public void setItemDay3(String[] itemDay3) {
		this.itemDay3 = itemDay3;
	}



	public String[] getItemDay4() {
		return itemDay4;
	}



	public void setItemDay4(String[] itemDay4) {
		this.itemDay4 = itemDay4;
	}



	public String[] getItemDay5() {
		return itemDay5;
	}



	public void setItemDay5(String[] itemDay5) {
		this.itemDay5 = itemDay5;
	}



	public String[] getItemDay6() {
		return itemDay6;
	}



	public void setItemDay6(String[] itemDay6) {
		this.itemDay6 = itemDay6;
	}



	public String[] getItemDay7() {
		return itemDay7;
	}



	public void setItemDay7(String[] itemDay7) {
		this.itemDay7 = itemDay7;
	}



	public String[] getItemDay8() {
		return itemDay8;
	}



	public void setItemDay8(String[] itemDay8) {
		this.itemDay8 = itemDay8;
	}



	public void reset(ActionMapping mapping, HttpServletRequest request) {

		day1Admission = null;
		day2Admission = null;
		day3Admission = null;
		day4Admission = null;
		day5Admission = null;
		day6Admission = null;
		day7Admission = null;
		day8Admission = null;
		
		day1Start = null;
		day2Start = null;
		day3Start = null;
		day4Start = null;
		day5Start = null;
		day6Start = null;
		day7Start = null;
		day8Start = null;
		
		itemDay1 = null;
		itemDay2 = null;
		itemDay3 = null;
		itemDay4 = null;
		itemDay5 = null;
		itemDay6 = null;
		itemDay7 = null;
		itemDay8 = null;
		
	}
	
}
