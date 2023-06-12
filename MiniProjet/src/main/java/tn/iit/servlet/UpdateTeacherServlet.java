package tn.iit.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeacherDAO;
import tn.iit.models.Teacher;

/**
 * Servlet implementation class UpdateTeacherServlet
 */
@WebServlet("/UpdateTeacherServlet")
public class UpdateTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   int teacherId = Integer.parseInt(request.getParameter("id"));
	        String name = request.getParameter("name");
	        String surname = request.getParameter("surname");
	        int age = Integer.parseInt(request.getParameter("age"));       
	        int cin = Integer.parseInt(request.getParameter("cin"));

	        // Retrieve the teacher object from the database
	        TeacherDAO teacherDAO = new TeacherDAO();
	        Teacher teacher = teacherDAO.getTeacherById(teacherId);

	        if (teacher != null) {
	            // Update the teacher's information
	            teacher.setName(name);
	            teacher.setSurname(surname);
	            teacher.setAge(age);
	            teacher.setCin(cin);

	            // Save the updated teacher object
	            teacherDAO.update(teacher);

	            // Redirect back to the teacher list page
	            response.sendRedirect("Index.jsp");
	        } else {
	            // Handle the case where the teacher was not found
	            // Display an error message or redirect to an error page
	            // For example:
	            response.sendRedirect("error.jsp");
	        }
		doGet(request, response);
	}

}
