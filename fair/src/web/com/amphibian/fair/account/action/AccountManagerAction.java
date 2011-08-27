package com.amphibian.fair.account.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.User;

public class AccountManagerAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		User adminUser = (User)request.getSession().getAttribute("AdminUserObj");
		
		if (adminUser == null) {
			return null;
		}
		
		return mapping.findForward("accountManager");
		
	}

}
