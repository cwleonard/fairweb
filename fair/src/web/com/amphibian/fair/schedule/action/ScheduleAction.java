package com.amphibian.fair.schedule.action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.HitCounter;
import com.amphibian.fair.db.dao.web.ScheduleDAO;


public class ScheduleAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		ScheduleDAO dao = new ScheduleDAO();
		
		ArrayList days = dao.loadAll();

		request.setAttribute("days", days);

		HitCounter hc = new HitCounter(HitCounter.PREMIUMS_PAGE);
		request.setAttribute("hits", hc);

		return mapping.findForward("schedulePage");
		
	}

}
