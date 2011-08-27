package com.amphibian.fair.schedule.action;


import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.ScheduleItem;
import com.amphibian.fair.db.dao.web.ScheduleItemDAO;


public class LoadItemAction extends Action {

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
		
		ScheduleItem si = null;
		ArrayList sis = null;
		if (id != null && id.length() > 0) {
			si = dao.loadById(id);
		} else if (day != null && day.length() > 0) {
			sis = dao.loadByDay(Integer.parseInt(day));
		}
		
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("application/xml");
		response.getWriter().print("<ItemsList>");
		if (si != null) {
			response.getWriter().print(si.toStringXML());
		} else if (sis != null) {
			Iterator it = sis.iterator();
			while(it.hasNext()) {
				ScheduleItem i = (ScheduleItem)it.next();
				response.getWriter().print(i.toStringXML());
			}
		}
		response.getWriter().print("</ItemsList>");

		return null;
		
	}

}
