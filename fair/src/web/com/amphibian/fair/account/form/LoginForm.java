package com.amphibian.fair.account.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LoginForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String userId;
	
	private String password;

	private String returnToSender;
	
	private String from;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		userId = null;
		password = null;
		returnToSender = "false";
	}

	public String getReturnToSender() {
		return returnToSender;
	}

	public void setReturnToSender(String returnToSender) {
		this.returnToSender = returnToSender;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
}
