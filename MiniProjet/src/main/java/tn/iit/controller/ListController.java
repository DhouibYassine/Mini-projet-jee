package tn.iit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuthorizationDAO;
import tn.iit.models.Authorization;
@WebServlet("/ListController")
public class ListController extends HttpServlet {

	/**
	 * 
	 */
	private AuthorizationDAO authorizationDAO;
	private static final long serialVersionUID = 1L;
    public ListController() {
    	super();
    	this.authorizationDAO= new AuthorizationDAO();
    }
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Authorization> authorizations = authorizationDAO.getAllAuthorizations();
    	request.setAttribute("authorizations", authorizations);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("authorizationlist.jsp");
    	dispatcher.forward(request, response);
    }
    @Override 
    protected void 	doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		doGet(request, response);
    }
}
