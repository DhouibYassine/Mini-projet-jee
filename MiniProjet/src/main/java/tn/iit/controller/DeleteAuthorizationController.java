package tn.iit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuthorizationDAO;
import dao.TeacherDAO;
import tn.iit.models.Authorization;
import tn.iit.models.Teacher;

@WebServlet("/DeleteAuthorizationController")
public class DeleteAuthorizationController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AuthorizationDAO authorizationDAO;
	private TeacherDAO teacherDAO;

    public void init() {
        // Initialize the authorizationDAO instance
        authorizationDAO = new AuthorizationDAO();
        teacherDAO =new TeacherDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the authorization ID from the request
        int authorizationId = Integer.parseInt(request.getParameter("authorizationId"));
       Authorization authorization =  authorizationDAO.getAuthorizationById(authorizationId);
       Teacher teacher =authorization.getTeacher();
       teacher.setNombreHeure(teacher.getNombreHeure()+authorization.getDuration());
        // Delete the authorization
       teacherDAO.update(teacher);
        authorizationDAO.deleteAuthorization(authorizationId);

        // Redirect the user back to the authorization list page
        response.sendRedirect("ListController");
    }
}

