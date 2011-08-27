package com.amphibian.fair.premiums.action;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.premiums.EntryDAO;
import com.amphibian.fair.premiums.PremiumsEntry;

public class SaveEntryAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		ArrayList entries = (ArrayList)PropertyUtils.getSimpleProperty(form, "entryObjects");
		User user = (User)request.getSession().getAttribute("UserObj");

		if (user == null) {
			return mapping.findForward("premiumsRedirect");
		}
		
		EntryDAO dao = new EntryDAO();
		
		Iterator i = entries.iterator();
		while(i.hasNext()) {
			
			PremiumsEntry entry = (PremiumsEntry)i.next();
			entry.setExhibitorId(user.getId());
			dao.store(entry);
			
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
