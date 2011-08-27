package com.amphibian.fair.premiums.action;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.amphibian.fair.db.dao.premiums.ClassDAO;
import com.amphibian.fair.db.dao.premiums.ExtraPrizeDAO;
import com.amphibian.fair.db.dao.premiums.HonorableMentionDAO;
import com.amphibian.fair.db.dao.premiums.PrizeDAO;
import com.amphibian.fair.db.dao.premiums.SectionDAO;
import com.amphibian.fair.premiums.PremiumsClass;
import com.amphibian.fair.premiums.PremiumsPrize;
import com.amphibian.fair.premiums.PremiumsSection;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class JudgingSheetPDFAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		response.setContentType("application/pdf");
		Document document = new Document();
		try {

			SectionDAO sdao = new SectionDAO();
			PremiumsSection sect = sdao.loadById(request.getParameter("id"));
			
			ClassDAO cdao = new ClassDAO();
			ArrayList classes = cdao.loadAllBySection(sect);
			
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			
			Iterator it = classes.iterator();
			while (it.hasNext()) {
				PremiumsClass c = (PremiumsClass)it.next();
				addClassesToDocument(document, c);
			}

			/*
			Table t = new Table(5);
			t.addCell("1st place");
			t.addCell("2nd place");
			t.addCell("3rd place");
			t.addCell("4th place");
			t.addCell("5th place");
			document.add(t);
		*/
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return null;

	}
	
	private void addClassesToDocument(Document doc, PremiumsClass c) throws DocumentException {
		
		ArrayList children = c.getChildren();
		if (children != null) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				PremiumsClass pc = (PremiumsClass)it.next();
				addClassesToDocument(doc, pc);
			}
		} else {
			String txt = "Class " + c.getShortCode() + " - " + c.getDescription();
			doc.add(new Paragraph(txt, FontFactory.getFont(FontFactory.HELVETICA, 10)));

			PrizeDAO pDAO = new PrizeDAO();
			//ExtraPrizeDAO epDAO = new ExtraPrizeDAO();
			//HonorableMentionDAO hmDAO = new HonorableMentionDAO();

			ArrayList prizes = pDAO.loadAllByClass(c);
			Iterator pit = prizes.iterator();
			Paragraph prizeParagraph = new Paragraph();
			prizeParagraph.setFont(FontFactory.getFont(FontFactory.HELVETICA, 8));
			prizeParagraph.setIndentationLeft(20);
			while (pit.hasNext()) {
				PremiumsPrize pp = (PremiumsPrize)pit.next();
				prizeParagraph.add(new Phrase(pp.toString()));
			}
			doc.add(prizeParagraph);
			
		}
		
		
		
	}
	
}
