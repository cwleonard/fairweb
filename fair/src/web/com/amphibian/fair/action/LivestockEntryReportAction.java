package com.amphibian.fair.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.db.dao.web.LivestockEntryReportDAO;


public class LivestockEntryReportAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		LivestockEntryReportDAO dao = new LivestockEntryReportDAO();
		
		String html = dao.getReport();

		request.setAttribute("report", html);

		return mapping.findForward("reportPage");
		
	}

}
