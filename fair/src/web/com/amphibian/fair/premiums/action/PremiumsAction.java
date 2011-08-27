package com.amphibian.fair.premiums.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.beans.HitCounter;
import com.amphibian.fair.db.dao.config.ConfigDAO;
import com.amphibian.fair.db.dao.premiums.ClassDAO;
import com.amphibian.fair.db.dao.premiums.DepartmentDAO;
import com.amphibian.fair.db.dao.premiums.SectionDAO;
import com.amphibian.fair.premiums.PremiumsDepartment;
import com.amphibian.fair.premiums.PremiumsSection;

public class PremiumsAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {

		String deptId = request.getParameter("dept");
		String sectId = request.getParameter("sect");
		
		if (deptId == null && sectId == null) {
		
			DepartmentDAO dao = new DepartmentDAO();
			List depts = dao.loadAll();
		
			request.setAttribute("depts", depts);
			
		} else if (deptId != null) {
			
			DepartmentDAO deptDAO = new DepartmentDAO();
			PremiumsDepartment dept = deptDAO.loadById(deptId);
			
			SectionDAO sectDAO = new SectionDAO();
			List sects = sectDAO.loadAllByDepartment(dept);
			
			request.setAttribute("dept", dept);
			request.setAttribute("sects", sects);
			
		} else if (sectId != null) {
			
			SectionDAO sectDAO = new SectionDAO();
			PremiumsSection sect = sectDAO.loadById(sectId);
			
			DepartmentDAO deptDAO = new DepartmentDAO();
			PremiumsDepartment dept = deptDAO.loadById(String.valueOf(sect.getDepartment()));
			
			ClassDAO classDAO = new ClassDAO();
			List classes = classDAO.loadAllBySection(sect);
			
			request.setAttribute("dept", dept);
			request.setAttribute("sect", sect);
			request.setAttribute("classes", classes);
			
		}
		
		ConfigDAO config = new ConfigDAO();
		boolean allowEntry = config.getBooleanValue("premiums.allow.entry");
		boolean ended = config.getBooleanValue("premiums.ended");
		String premiumsYear = config.getValue("premiums.year");
		request.setAttribute("allowEntry", new Boolean(allowEntry));
		request.setAttribute("ended", new Boolean(ended));
		request.setAttribute("premiumsYear", premiumsYear);
		
		HitCounter hc = new HitCounter(HitCounter.PREMIUMS_PAGE);
		request.setAttribute("hits", hc);

		return mapping.findForward("premiumsPage");
		
	}
}
