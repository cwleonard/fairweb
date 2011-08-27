package com.amphibian.fair.account.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.web.UserDAO;

public class SaveUserAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		if (isCancelled(request)) {
			return mapping.findForward("premiumsRedirect");
		}

		User loggedInUser = (User)request.getSession().getAttribute("UserObj");
		User adminUser = (User)request.getSession().getAttribute("AdminUserObj");

		User user = (User)PropertyUtils.getProperty(form, "userObject");

		ActionMessages errors = new ActionMessages();

		try {
			
			UserDAO dao = new UserDAO();
			
			if (user.getId() == -1 && dao.userIdInUse(user.getUserId())) {
				errors.add("userId", new ActionMessage("userid.inuse"));
				this.saveErrors(request, errors);
				return mapping.getInputForward();
			} else {
				if (adminUser == null && loggedInUser != null) {
					// regular users can only edit themselves...
					if (loggedInUser.getUserId().equals(user.getUserId())) {
						dao.store(user);
						request.getSession().setAttribute("UserObj", user);
					}
				} else if (adminUser != null && loggedInUser != null) {
					// admin users can edit other people...
					dao.store(user);
					if (adminUser.getUserId().equals(loggedInUser.getUserId())) {
						request.getSession().setAttribute("UserObj", user);
					}
				} else {
					// new person is signing up for account...
					user.setWebAdmin(false);  // make sure we're not being hacked
					dao.store(user);
					request.getSession().setAttribute("UserObj", user);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("premiumsRedirect");
		
	}

}
