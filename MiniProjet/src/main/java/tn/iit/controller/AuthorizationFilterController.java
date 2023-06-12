package tn.iit.controller;
import dao.AuthorizationDAO;
import tn.iit.models.Authorization;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/AuthorizationFilterController")
public class AuthorizationFilterController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AuthorizationDAO authorizationDAO;

    public void init() {
        authorizationDAO = new AuthorizationDAO(); // Initialize the DAO object
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filterType = request.getParameter("filterType");
        if (filterType.equals("month")) {
            String monthStr = request.getParameter("month");
            filterAuthorizationsByMonth(monthStr, request, response);
        } else if (filterType.equals("date")) {
            String dateStr = request.getParameter("date");
            filterAuthorizationsByDate(dateStr, request, response);
        } else {
            // Invalid filter type
              
        	List<Authorization> authorizations = authorizationDAO.getAllAuthorizations();
        	request.setAttribute("authorizations", authorizations);
        	RequestDispatcher dispatcher = request.getRequestDispatcher("authorizationlist.jsp");
        	dispatcher.forward(request, response);

        }
    }

    private void filterAuthorizationsByMonth(String monthStr, HttpServletRequest request,
                                             HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        try {
            Date month = dateFormat.parse(monthStr);

            List<Authorization> filteredAuthorizations = authorizationDAO.getAuthorizationsByMonth(month);

            request.setAttribute("authorizations", filteredAuthorizations);
            request.getRequestDispatcher("authorizationlist.jsp").forward(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the error case
            response.sendRedirect("authorizationlist.jsp");
        }
    }

    private void filterAuthorizationsByDate(String dateStr, HttpServletRequest request,
                                            HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(dateStr);

            List<Authorization> filteredAuthorizations = authorizationDAO.getAuthorizationsByDate(date);

            request.setAttribute("authorizations", filteredAuthorizations);
            request.getRequestDispatcher("authorizationlist.jsp").forward(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the error case
            response.sendRedirect("authorizationlist.jsp");
        }
    }
    
    
}
