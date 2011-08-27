package com.amphibian.fair.schedule.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.FairDay;
import com.amphibian.fair.db.dao.web.ScheduleDAO;


public class ModifyDayAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {

		if (request.getSession().getAttribute("AdminUserObj") == null) {
			return null;
		}

		ScheduleDAO dao = new ScheduleDAO();
		
		String day = request.getParameter("day");
		String admission = request.getParameter("admission");
		String time = request.getParameter("time");
		String sponsor = request.getParameter("sponsor");
		String notes = request.getParameter("notes");
		
		if (day != null && day.length() > 0) {
			FairDay fairDay = dao.loadByDay(Integer.parseInt(day));
			if (admission != null) {
				fairDay.setAdmission(admission);
			}
			if (time != null) {
				fairDay.setStartTime(time);
			}
			if (sponsor != null) {
				fairDay.setSponsor(sponsor);
			}
			if (notes != null) {
				fairDay.setTitle(notes);
			}
			dao.store(fairDay);
		}
		
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("application/xml");
		response.getWriter().println("<transactionResponse><day>" + day + "</day></transactionResponse>");

		return null;
		
	}

}
