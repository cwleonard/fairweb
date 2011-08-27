package com.amphibian.fair.premiums.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.HitCounter;
import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.premiums.EntryDAO;

public class PremiumsEntriesAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {

		EntryDAO dao = new EntryDAO();

		User user = (User) request.getSession().getAttribute("UserObj");
		
		if (user == null) {
			return null;
		}
		
		ArrayList eList = dao.loadAllByExhibitor(user);
		request.setAttribute("entries", eList);
		
		HitCounter hc = new HitCounter(HitCounter.PREMIUMS_ENTRIES_PAGE);
		request.setAttribute("hits", hc);
	
		return mapping.findForward("entriesPage");
		
	}

}
