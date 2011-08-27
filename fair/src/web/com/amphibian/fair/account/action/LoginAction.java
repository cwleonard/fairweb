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

import com.amphibian.fair.beans.HitCounter;
import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.web.UserDAO;

public class LoginAction extends Action {

	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		ActionMessages errors = new ActionMessages();
		
		String userid = (String)PropertyUtils.getProperty(form, "userId");
		String password = (String)PropertyUtils.getProperty(form, "password");
		String returnToSender = (String)PropertyUtils.getProperty(form, "returnToSender");
		String from = (String)PropertyUtils.getProperty(form, "from");
		
		if (userid == null && password == null) {
			from = request.getHeader("REFERER");
			PropertyUtils.setProperty(form, "from", from);
			HitCounter hc = new HitCounter(HitCounter.LOGIN_PAGE);
			request.setAttribute("hits", hc);
			return mapping.findForward("login");
		}
		
		if (userid != null && userid.length() > 0) {
			
			UserDAO dao = new UserDAO();
			User user = dao.load(userid, password);
			if (user != null) {
				request.getSession().setAttribute("UserObj", user);
				if (user.isWebAdmin()) {
					request.getSession().setAttribute("AdminUserObj", user);
				}
			} else {
				errors.add("userId", new ActionMessage("invalid.credentials"));
			}
			
		} else {

			errors.add("userId", new ActionMessage("email.required"));
			
		}

		this.saveErrors(request.getSession(), errors);

		ActionForward fwd = null;
		
		if ("true".equalsIgnoreCase(returnToSender) || !errors.isEmpty()) {
			fwd = new ActionForward();
			fwd.setRedirect(true);
			String[] stuff = from.split("/");
			from = "/" + stuff[stuff.length-1];
			fwd.setPath(from);
			if (from.startsWith("login.do") || from.startsWith("forgotPassword.do")) {
				fwd = null;
			}
		}
		
		if (fwd == null) {
			fwd = mapping.findForward("homeRedirect");
		}
		
		
		return fwd;
		
	}

}
