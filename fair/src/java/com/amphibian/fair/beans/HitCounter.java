package com.amphibian.fair.beans;

import com.amphibian.fair.db.dao.web.HitCounterDAO;

public class HitCounter {

	public static final int HOME_PAGE = 1;
	
	public static final int PREMIUMS_PAGE = 2;
	
	public static final int CLASS_DETAIL_PAGE = 3;
	
	public static final int SCHEDULE_PAGE = 4;
	
	public static final int ABOUT_PAGE = 5;
	
	public static final int LOGIN_PAGE = 6;
	
	public static final int PREMIUMS_ENTRIES_PAGE = 7;
	
	private int page;
	
	private int hits;

	public HitCounter(int page) {
		this.page = page;
		HitCounterDAO dao = new HitCounterDAO();
		hits = dao.hit(page);
	}
	
	public int getHits() {
		return hits;
	}

	public String toString() {
		return Integer.toString(hits);
	}

	public int getPage() {
		return page;
	}
	
}
