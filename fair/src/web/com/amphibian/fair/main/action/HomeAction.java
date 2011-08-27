package com.amphibian.fair.main.action;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.FairDay;
import com.amphibian.fair.beans.HitCounter;
import com.amphibian.fair.db.dao.web.DownloadItemDAO;
import com.amphibian.fair.db.dao.web.GeneralInformationItemDAO;
import com.amphibian.fair.db.dao.web.NewsItemDAO;
import com.amphibian.fair.db.dao.web.ScheduleDAO;

public class HomeAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		NewsItemDAO dao = new NewsItemDAO();
		List news = dao.loadAll();
		
		GeneralInformationItemDAO gDAO = new GeneralInformationItemDAO();
		List info = gDAO.loadAll();
		
		DownloadItemDAO dDao = new DownloadItemDAO();
		List downloads = dDao.loadAll();
		
		Calendar cal = new GregorianCalendar();
		int dayNum = FairDay.getDayOfFair(cal);
		if (dayNum > 0 && dayNum < 9) {
			ScheduleDAO scheduleDao = new ScheduleDAO();
			FairDay day = scheduleDao.loadByDay(dayNum);
			request.setAttribute("fairday", day);
		} else if (dayNum > 8) {
			String nextNum = FairDay.getAnnuity(cal.get(Calendar.YEAR) + 1);
			request.setAttribute("nextYear", nextNum);
		} else {
			FairDay day1 = new FairDay(1);
			int daysLeft = day1.daysUntil();
			request.setAttribute("daysLeft", new Integer(daysLeft));
		}
		
		HitCounter hc = new HitCounter(HitCounter.HOME_PAGE);
		
		request.setAttribute("news", news);
		request.setAttribute("info", info);
		request.setAttribute("downloads", downloads);
		request.setAttribute("hits", hc);
		
		return mapping.findForward("homePage");
		
	}
	
	
}
