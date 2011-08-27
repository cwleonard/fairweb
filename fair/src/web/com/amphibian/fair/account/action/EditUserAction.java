package com.amphibian.fair.account.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.account.form.UserForm;
import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.web.UserDAO;

public class EditUserAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		String userId = (String)PropertyUtils.getProperty(form, "id");
		
		User loggedInUser = (User)request.getSession().getAttribute("UserObj");
		User adminUser = (User)request.getSession().getAttribute("AdminUserObj");
		
		User u = null;
		
		if (userId != null && userId.length() > 0) {

			int uid = Integer.parseInt(userId);
			
			if (adminUser != null && uid > 0) {
				UserDAO dao = new UserDAO();
				u = dao.loadById(userId);
			} else if (loggedInUser != null) {
				u = loggedInUser;
			} else {
				u = new User();
			}
			
		} else {
			if (loggedInUser != null) {
				u = loggedInUser;
			} else {
				u = new User();
			}
		}
		
		request.setAttribute("user", u);
		
		if (u.getId() != -1) {
			request.setAttribute("allowEditUserId", new Boolean(false));
		} else {
			request.setAttribute("allowEditUserId", new Boolean(true));
		}
		
		((UserForm)form).setFromUserObject(u);
		
		return mapping.findForward("userEditor");
		
	}

}
