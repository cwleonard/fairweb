package com.amphibian.fair.main.action;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.GeneralInformationItem;
import com.amphibian.fair.beans.NewsItem;
import com.amphibian.fair.db.dao.web.GeneralInformationItemDAO;
import com.amphibian.fair.db.dao.web.NewsItemDAO;


public class ManageInfoAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		if (request.getSession().getAttribute("AdminUserObj") == null) {
			return null;
		}

		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("application/xml");

		PrintWriter writer = response.getWriter();
		
		String command = request.getParameter("command");
		
		if ("delete".equalsIgnoreCase(command)) {

			String id = request.getParameter("id");
			if (id != null && id.length() > 0) {
				GeneralInformationItemDAO dao = new GeneralInformationItemDAO();
				dao.remove(Integer.parseInt(id));
				writer.println("<TransactionResponse><Delete id=\"" + id + "\">OK</Delete></TransactionResponse>");
			}

		} else if ("load".equalsIgnoreCase(command)) {
			
			String id = request.getParameter("id");
			if (id != null && id.length() > 0) {
				GeneralInformationItemDAO dao = new GeneralInformationItemDAO();
				GeneralInformationItem ii = dao.loadById(Integer.parseInt(id));
				writer.print("<ItemsList>");
				writer.print(ii.toStringXML());
				writer.print("</ItemsList>");
			}
			
		} else if ("loadall".equalsIgnoreCase(command)) {
			
			GeneralInformationItemDAO dao = new GeneralInformationItemDAO();
			ArrayList list = dao.loadAll();
			writer.print("<ItemsList>");
			Iterator it = list.iterator();
			while (it.hasNext()) {
				GeneralInformationItem ii = (GeneralInformationItem)it.next();
				writer.print(ii.toStringXML());
			}
			writer.print("</ItemsList>");
			
		} else if ("save".equalsIgnoreCase(command)) {
			
			String id = request.getParameter("id");
			String headline = request.getParameter("headline");
			String text = request.getParameter("text");

			System.out.println(text);
			
			if (id != null) {
				GeneralInformationItemDAO dao = new GeneralInformationItemDAO();
				GeneralInformationItem i = new GeneralInformationItem();
				if (id.length() > 0) {
					i.setId(Integer.parseInt(id));
				}
				i.setHeadline(headline);
				i.setText(text);
				dao.store(i);
				writer.println("<TransactionResponse><Save id=\"" + i.getId() + "\">OK</Save></TransactionResponse>");
			}
		}
			
		return null;
		
	}

}
