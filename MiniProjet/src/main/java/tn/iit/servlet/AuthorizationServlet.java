package tn.iit.servlet;
import tn.iit.models.Authorization;
import tn.iit.models.Teacher;
import tn.iit.servlet.TeacherServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import dao.AuthorizationDAO;
import dao.TeacherDAO;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AuthorizationServlet extends HttpServlet {
    
    /**
	 * 
	 */
	private final TeacherDAO teacherDAO = new TeacherDAO();
    private AuthorizationDAO authorizationDAO = new AuthorizationDAO();


    
	private static final long serialVersionUID = 1L;
    
    @Override
    	

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int teacherId = Integer.parseInt(request.getParameter("teacherId"));

            Teacher teacher = teacherDAO.getTeacherById(teacherId);
            if (teacher != null) {
                List<Authorization> authorizations = authorizationDAO.getAuthorizationsByTeacher(teacher);

                request.setAttribute("teacher", teacher);
                request.setAttribute("authorizations", authorizations);
                request.getRequestDispatcher("authorization.jsp").forward(request, response);}
            }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    	   
    	    
    	    private void generateAuthorizationPDF(int teacherId, int authorizedHours) {
    	        // Implement the code to generate the PDF document for the teacher's authorization
    	        // You can use any PDF generation library or framework of your choice
    	        
    	        // Placeholder code
    	        // Use the teacherId and authorizedHours to generate the PDF document
    	        // Example: PDFGenerator.generateAuthorizationPDF(teacherId, authorizedHours);
    	    }
    
    
 
    
    
   
}


