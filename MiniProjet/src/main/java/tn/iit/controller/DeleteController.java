package tn.iit.controller;



import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeacherDAO;
import tn.iit.models.Teacher;

import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TeacherDAO teacherDAO;
    public DeleteController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ServletContext context = request.getServletContext();
        List<Teacher> tUser = (List<Teacher>) context.getAttribute("teacher");

        if (id >= 0 && id < tUser.size()) {
            tUser.remove(id);
            teacherDAO.delete(id);
            
        }

        response.sendRedirect("Index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
