package tn.iit.controller;

import dao.TeacherDAO;
import tn.iit.models.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/TeacherListController")
public class TeacherListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TeacherDAO teacherDAO;

    public TeacherListController() {
        super();
        teacherDAO = new TeacherDAO(); // Instantiate the DAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Teacher> teachers = teacherDAO.getAllTeachers(); // Retrieve the list of teachers from the DAO

        request.getServletContext().setAttribute("teachers", teachers); // Store the list in the servlet context

        request.getRequestDispatcher("TeacherList.jsp").forward(request, response); // Forward the request to the JSP page
    }
}
