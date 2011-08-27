package com.amphibian.fair.account.action;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

public class ForgotPasswordAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		String userid = (String)PropertyUtils.getProperty(form, "userId");
		
		if (isCancelled(request)) {
			return mapping.findForward("premiumsRedirect");
		}
		
		if (userid != null && userid.length() > 0) {
			
			UserDAO dao = new UserDAO();
			User user = dao.loadByUserId(userid);
			if (user != null) {
				
				try {
					
					Properties props = System.getProperties();
					
					props.put("mail.smtp.host", "smtp.earthlink.net");
					Session session = Session.getDefaultInstance(props, null);
					
					Message msg = new MimeMessage(session);
					
					msg.setFrom(new InternetAddress("jcfair@countryilink.net"));
					msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail(), false));
					//msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("casey@amphibian.com", false));
					
					msg.setSubject("Juniata County Fair Website Login");
					
					msg.setText("Your password for logging in to the Juniata County Fair "
							+ "Website is: " + user.getPassword());
					
					msg.setHeader("X-Mailer", "Amphibian WebMailer");
					msg.setSentDate(new Date());
					
					Transport.send(msg);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {
				
				ActionMessages errors = new ActionMessages();
				errors.add("userId", new ActionMessage("invalid.userid"));
				this.saveErrors(request, errors);
				
				return mapping.findForward("forgottenPage");
				
			}
			
			return mapping.findForward("premiumsRedirect");
			
		} else {
			
			return mapping.findForward("forgottenPage");
			
		}
		
	}
	
}
