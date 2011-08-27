package com.amphibian.fair.account.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.web.UserDAO;


public class ManageUsersAction extends Action {

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
//			if (id != null && id.length() > 0) {
//				GeneralInformationItemDAO dao = new GeneralInformationItemDAO();
//				dao.remove(Integer.parseInt(id));
//				writer.println("<TransactionResponse><Delete id=\"" + id + "\">OK</Delete></TransactionResponse>");
//			}

		} else if ("load".equalsIgnoreCase(command)) {
			
			String id = request.getParameter("id");
			if (id != null && id.length() > 0) {
				UserDAO dao = new UserDAO();
				User ii = dao.loadById(id);
				writer.print("<ItemsList>");
				writer.print(ii.toStringXML());
				writer.print("</ItemsList>");
			}
			
		} else if ("loadall".equalsIgnoreCase(command)) {
			
			UserDAO dao = new UserDAO();
			ArrayList list = dao.loadAll();
			writer.print("<ItemsList>");
			Iterator it = list.iterator();
			while (it.hasNext()) {
				User ii = (User)it.next();
				writer.print(ii.toStringXML());
			}
			writer.print("</ItemsList>");
			
		} else if ("save".equalsIgnoreCase(command)) {
			
			String id = request.getParameter("id");
			String userId = request.getParameter("userId");
			String userType = request.getParameter("uType");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String orgName = request.getParameter("orgName");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zip = request.getParameter("zip");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String admin = request.getParameter("webAdmin");

			if (id != null) {
				
				if (id.length() == 0) id = "-1";
				
				if (userType.equalsIgnoreCase("P")) {
					orgName = "";
				} else {
					firstName = "";
					lastName = "";
				}
				
				User u = new User();
				u.setId(Integer.parseInt(id));
				u.setUserId(userId);
				u.setFirstName(firstName);
				u.setLastName(lastName);
				u.setOrgName(orgName);
				u.setAddress(address);
				u.setCity(city);
				u.setState(state);
				u.setZip(zip);
				u.setPhone(phone);
				u.setEmail(email);
				u.setWebAdmin(Boolean.parseBoolean(admin));
				
				UserDAO dao = new UserDAO();
				dao.store(u);
				
				writer.println("<TransactionResponse><Save id=\"" + u.getId() + "\">OK</Save></TransactionResponse>");
				
			}
		}
			
		return null;
		
	}

}
