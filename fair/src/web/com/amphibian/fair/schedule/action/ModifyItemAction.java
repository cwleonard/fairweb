package com.amphibian.fair.schedule.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.ScheduleItem;
import com.amphibian.fair.db.dao.web.ScheduleItemDAO;


public class ModifyItemAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {

		if (request.getSession().getAttribute("AdminUserObj") == null) {
			return null;
		}

		ScheduleItemDAO dao = new ScheduleItemDAO();
		
		String id = request.getParameter("id");
		String day = request.getParameter("day");
		String desc = request.getParameter("desc");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String allday = request.getParameter("allday");
		
		if (id != null && id.length() == 0) {
			id = null;
		}
		
		ScheduleItem item = new ScheduleItem();
		item.setId(id);
		item.setDay(Integer.parseInt(day));
		item.setStartTime(start);
		item.setStopTime(end);
		item.setDescription(desc);
		item.setAllDay(Boolean.parseBoolean(allday));
		
		// if id is null, a new id will be returned
		id = dao.store(item);
		
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/xml");
		response.getWriter().println("<transactionResponse><id>" + id + "</id></transactionResponse>");

		return null;
		
	}

}
