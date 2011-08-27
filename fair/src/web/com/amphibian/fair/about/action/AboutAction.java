package com.amphibian.fair.about.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.HitCounter;
import com.amphibian.fair.beans.PageText;
import com.amphibian.fair.db.dao.web.PageTextDAO;

public class AboutAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		PageTextDAO dao = new PageTextDAO();
		Map texts = dao.loadAll();
		
		PageText fairHistory = (PageText)texts.get("Fair History");
		request.setAttribute("history", fairHistory);
		
		HitCounter hc = new HitCounter(HitCounter.ABOUT_PAGE);
		
		request.setAttribute("hits", hc);
		
		return mapping.findForward("aboutPage");
		
	}
	
	
}
