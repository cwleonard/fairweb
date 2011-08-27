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

import com.amphibian.fair.beans.NewsItem;
import com.amphibian.fair.db.dao.web.NewsItemDAO;


public class ManageNewsAction extends Action {

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
				NewsItemDAO dao = new NewsItemDAO();
				dao.remove(Integer.parseInt(id));
				writer.println("<TransactionResponse><Delete id=\"" + id + "\">OK</Delete></TransactionResponse>");
			}

		} else if ("load".equalsIgnoreCase(command)) {
			
			String id = request.getParameter("id");
			if (id != null && id.length() > 0) {
				NewsItemDAO dao = new NewsItemDAO();
				NewsItem ni = dao.loadById(Integer.parseInt(id));
				writer.print("<ItemsList>");
				writer.print(ni.toStringXML());
				writer.print("</ItemsList>");
			}
			
		} else if ("loadall".equalsIgnoreCase(command)) {
			
			NewsItemDAO dao = new NewsItemDAO();
			ArrayList list = dao.loadAll();
			writer.print("<ItemsList>");
			Iterator it = list.iterator();
			while (it.hasNext()) {
				NewsItem ni = (NewsItem)it.next();
				writer.print(ni.toStringXML());
			}
			writer.print("</ItemsList>");
			
		} else if ("save".equalsIgnoreCase(command)) {
			
			String id = request.getParameter("id");
			String date = request.getParameter("date");
			String text = request.getParameter("text");

			if (id != null) {
				NewsItemDAO dao = new NewsItemDAO();
				NewsItem i = new NewsItem();
				if (id.length() > 0) {
					i.setId(Integer.parseInt(id));
				}
				i.setPostDate(date);
				i.setText(text);
				dao.store(i);
				writer.println("<TransactionResponse><Save id=\"" + i.getId() + "\">OK</Save></TransactionResponse>");
			}
		}
			
		return null;
		
	}

}
