package tn.iit.controller;

import dao.TeacherDAO;
import tn.iit.models.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddController")
public class AddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TeacherDAO teacherDAO;

	public AddController() {
		super();
		teacherDAO = new TeacherDAO(); // Instantiate the DAO
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String name = request.getParameter("name");
	    int hours = Integer.parseInt(request.getParameter("nombreHeure")); // Update parameter name

	    Teacher teacher = new Teacher(name);
	    teacher.setNombreHeure(hours);

	    teacherDAO.save(teacher); // Use the DAO to save the teacher

	    response.sendRedirect("Index.jsp");
	}

}
