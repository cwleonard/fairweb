package com.amphibian.fair.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class NewsItemForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String text;

	private String command;
	
	private String id;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		text = null;
	}
	
}
