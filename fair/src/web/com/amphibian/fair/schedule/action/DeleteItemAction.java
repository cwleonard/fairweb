package com.amphibian.fair.schedule.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.db.dao.web.ScheduleItemDAO;


public class DeleteItemAction extends Action {

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
		
		if (id != null && id.length() > 0) {
			dao.delete(id);
		}
		
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("application/xml");
		response.getWriter().println("<transactionResponse><id>" + id + "</id></transactionResponse>");

		return null;
		
	}

}
