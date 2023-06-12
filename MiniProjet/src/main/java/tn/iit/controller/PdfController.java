package tn.iit.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import tn.iit.models.Authorization;
import tn.iit.models.Teacher;

@WebServlet("/pdfcontroller")
public class PdfController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the authorization and selectedTeacher from the request attributes
        Authorization authorization = (Authorization) request.getAttribute("authorization");
        Teacher selectedTeacher = (Teacher) request.getAttribute("selectedTeacher");

        // Generate the PDF content
        ByteArrayOutputStream pdfOutputStream = generatePdf(authorization, selectedTeacher);

        // Set the response headers
        response.setContentType("application/pdf");
        response.setContentLength(pdfOutputStream.size());
        response.setHeader("Content-Disposition", "attachment; filename=authorization.pdf");

        // Write the PDF content to the response output stream
        ServletOutputStream outputStream = response.getOutputStream();
        pdfOutputStream.writeTo(outputStream);
        outputStream.flush();
        
        // Redirect to the index.jsp page
          }

    private ByteArrayOutputStream generatePdf(Authorization authorization, Teacher selectedTeacher) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Create a PDF document
        Document document = new Document();
        try {
            // Set up the PDF writer with the output stream
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            // Open the document
            document.open();

            // Add content to the document
            Paragraph title = new Paragraph("Authorization Details");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add authorization details
            Paragraph authorizationInfo = new Paragraph("Authorization Information:");
            document.add(authorizationInfo);

            Paragraph date = new Paragraph("Date: " + authorization.getDate());
            document.add(date);

            Paragraph duration = new Paragraph("Duration: " + authorization.getDuration() + " hours");
            document.add(duration);

            Paragraph company = new Paragraph("Company: " + authorization.getPlace());
            document.add(company);
            // Add teacher details
            Paragraph teacherInfo = new Paragraph("Teacher Information:");
            document.add(teacherInfo);

            Paragraph teacherName = new Paragraph("Name: " + selectedTeacher.getName());
            document.add(teacherName);

            Paragraph teacherId = new Paragraph("ID: " + selectedTeacher.getId());
            document.add(teacherId);

        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            // Close the document
            document.close();
        }

        return outputStream;
    }
   
}
