package com.amphibian.fair.premiums.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.HitCounter;
import com.amphibian.fair.beans.User;
import com.amphibian.fair.db.dao.config.ConfigDAO;
import com.amphibian.fair.db.dao.premiums.ClassDAO;
import com.amphibian.fair.db.dao.premiums.DepartmentDAO;
import com.amphibian.fair.db.dao.premiums.EntryDAO;
import com.amphibian.fair.db.dao.premiums.SectionDAO;
import com.amphibian.fair.premiums.PremiumsClass;
import com.amphibian.fair.premiums.PremiumsDepartment;
import com.amphibian.fair.premiums.PremiumsSection;

public class ClassDetailAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		
		try {
			
			User user = (User)request.getSession().getAttribute("UserObj");
			
			EntryDAO eDAO = new EntryDAO();
			ClassDAO dao = new ClassDAO();

			PremiumsClass c = null;
			if (id != null) {
				c = dao.loadById(id);
			} else if (code != null) {
				c = dao.loadByCode(code);
			}
			
			ArrayList stuff = new ArrayList();
			PremiumsClass tempClass = c;
			while(tempClass.getSection() == 0) {
				tempClass = dao.loadById(tempClass.getParent());
				stuff.add(0, tempClass);
			}
			
			SectionDAO sectDAO = new SectionDAO();
			PremiumsSection sect = sectDAO.loadById(tempClass.getSection());
			
			DepartmentDAO deptDAO = new DepartmentDAO();
			PremiumsDepartment dept = deptDAO.loadById(sect.getDepartment());

			request.setAttribute("dept", dept);
			request.setAttribute("sect", sect);

			ArrayList yourEntries = null;
			if (user != null) {
				yourEntries = eDAO.loadByCodeAndExhibitor(c.getCode(), user);
				request.setAttribute("yourEntries", yourEntries);
			}
			
			String eInfo = null;
			if (yourEntries == null || yourEntries.size() == 0) {
				eInfo = "no entries";
			} else if (yourEntries.size() == 1) {
				eInfo = "1 entry";
			} else {
				eInfo = yourEntries.size() + " entries";
			}
			
			if (c.isLivestock()) {
				request.setAttribute("livestock", Boolean.TRUE);
				request.setAttribute("showBlank", Boolean.TRUE);
			}
			
			if (yourEntries != null) {
				if (c.getMaxEntry() > yourEntries.size()) {
					request.setAttribute("showBlank", Boolean.TRUE);
				}
			} else {
				request.setAttribute("showBlank", Boolean.TRUE);
			}
			
			if (!c.isAllowEntry()) {
				request.setAttribute("NoDirectEntry", Boolean.TRUE);
			}
			
			request.setAttribute("eInfo", eInfo);
			
			request.setAttribute("stuff", stuff);
			request.setAttribute("class", c);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ConfigDAO config = new ConfigDAO();
		boolean allowEntry = config.getBooleanValue("premiums.allow.entry");
		request.setAttribute("allowEntry", new Boolean(allowEntry));

		request.setAttribute("showBlank", Boolean.FALSE);

		HitCounter hc = new HitCounter(HitCounter.CLASS_DETAIL_PAGE);
		request.setAttribute("hits", hc);
		
		return mapping.findForward("classDetail");

	}
}
