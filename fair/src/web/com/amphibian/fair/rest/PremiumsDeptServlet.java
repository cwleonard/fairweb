package com.amphibian.fair.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amphibian.fair.db.dao.premiums.DepartmentDAO;
import com.amphibian.fair.premiums.PremiumsDepartment;
import com.google.gson.Gson;


public class PremiumsDeptServlet extends HttpServlet {

	private static final long serialVersionUID = 3731950841983240708L;

	/**
	 * This method provides read access to premiums departments. The
	 * data is returned in JSON format.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{

		String id = request.getParameter("id");
		if (id == null || id.length() == 0) {
			// get all departments
			DepartmentDAO dao = new DepartmentDAO();
			List<PremiumsDepartment> depts = dao.loadAll();
			Gson gson = new Gson();
			String dl = gson.toJson(depts);
			response.getWriter().print(dl);
		} else {
			// get a single department
			DepartmentDAO dao = new DepartmentDAO();
			PremiumsDepartment dept = dao.loadById(id);
			Gson gson = new Gson();
			String d = gson.toJson(dept);
			response.getWriter().print(d);
		}
		
	}

	
	
}
