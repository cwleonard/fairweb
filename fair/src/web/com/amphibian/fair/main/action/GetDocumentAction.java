package com.amphibian.fair.main.action;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.amphibian.fair.beans.DownloadItem;
import com.amphibian.fair.db.dao.web.DownloadItemDAO;
import com.amphibian.fair.main.form.FileUploadForm;


public class GetDocumentAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("application/octet-stream");
		
		String id = request.getParameter("id");

		DownloadItemDAO dao = new DownloadItemDAO();
		DownloadItem i = dao.loadById(Integer.parseInt(id));

		response.setHeader("Content-Disposition", "attachment; filename=" + i.getFileName());
		
		ByteArrayInputStream is = (ByteArrayInputStream)i.getInputStream();
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1];
		while (is.read(buffer) > 0) {
			os.write(buffer);
		}
			
		return null;
		
	}

}
