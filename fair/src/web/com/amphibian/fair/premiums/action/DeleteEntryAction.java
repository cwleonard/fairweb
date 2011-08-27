package com.amphibian.fair.premiums.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.premiums.EntryDAO;

public class DeleteEntryAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		String[] ids = (String[])PropertyUtils.getSimpleProperty(form, "id");
		User user = (User)request.getSession().getAttribute("UserObj");

		if (user == null) {
			return mapping.findForward("premiumsRedirect");
		}
		
		EntryDAO dao = new EntryDAO();
		
		for (int i = 0; i < ids.length; i++) {
			
			try {
				dao.delete(Integer.parseInt(ids[i]));
			} catch (NumberFormatException e) {
				// ignore
			}
			
		}
		
		String from = request.getHeader("REFERER");
		ActionForward fwd = new ActionForward();
		fwd.setRedirect(true);
		String[] stuff = from.split("/");
		from = "/" + stuff[stuff.length-1];
		fwd.setPath(from);
		
		return fwd;

	}
}
