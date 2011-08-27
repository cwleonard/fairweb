package com.amphibian.fair.main.action;


import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.amphibian.fair.beans.DownloadItem;
import com.amphibian.fair.beans.GeneralInformationItem;
import com.amphibian.fair.db.dao.web.DownloadItemDAO;
import com.amphibian.fair.main.form.FileUploadForm;


public class ManageDocumentsAction extends Action {

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
			DownloadItemDAO dao = new DownloadItemDAO();
			dao.remove(Integer.parseInt(id));

			writer.println("<TransactionResponse><Delete id=\"" + id + "\">OK</Delete></TransactionResponse>");

		} else if ("load".equalsIgnoreCase(command)) {
			
			
		} else if ("loadall".equalsIgnoreCase(command)) {
			
			DownloadItemDAO dao = new DownloadItemDAO();
			List items = dao.loadAll();
			writer.print("<FilesList>");
			Iterator it = items.iterator();
			while (it.hasNext()) {
				DownloadItem di = (DownloadItem)it.next();
				writer.print(di.toStringXML());
			}
			writer.print("</FilesList>");
			
		} else if ("save".equalsIgnoreCase(command)) {
			
			try {

				String title = request.getParameter("title");
				
				FileUploadForm uploadForm = (FileUploadForm)form;
				FormFile formFile = uploadForm.getFile();

				DownloadItemDAO dao = new DownloadItemDAO();
				DownloadItem i = new DownloadItem();
				i.setDescription(title);
				i.setInputStream(new DataInputStream(formFile.getInputStream()));
				i.setFileLength(formFile.getFileSize());
				i.setFileName(formFile.getFileName());
				dao.store(i);
				
				formFile.getInputStream().close();
				
				return mapping.findForward("uploadDone");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
			
		return null;
		
	}

}
