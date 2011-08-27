package com.amphibian.fair.premiums.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.db.dao.premiums.EntryDAO;

public class ShowEntriesAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {

		String filter = request.getParameter("filter");
		
		EntryDAO dao = new EntryDAO();
		ArrayList eList = dao.loadAllWithNames(filter);
		
		request.setAttribute("entries", eList);
			
		return mapping.findForward("entriesPage");
		
	}
}
