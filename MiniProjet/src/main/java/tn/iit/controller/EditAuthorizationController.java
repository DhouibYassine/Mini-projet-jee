package tn.iit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tn.iit.models.Authorization;
import dao.AuthorizationDAO;

@WebServlet("/EditAuthorizationController")
public class EditAuthorizationController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AuthorizationDAO authorizationDAO;

    public void init() {
        // Initialize the DAO object
        authorizationDAO = new AuthorizationDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the authorization ID from the request
        int authorizationId = Integer.parseInt(request.getParameter("authorizationId"));

        // Retrieve the authorization object from the database using the ID
        Authorization authorization = authorizationDAO.getAuthorizationById(authorizationId);

        // Set the authorization object as a request attribute
        request.setAttribute("authorization", authorization);

        // Forward the request to the edit authorization JSP page
        request.getRequestDispatcher("EditAuthorization.jsp").forward(request, response);
    }
}
