package tn.iit.controller;
import tn.iit.models.Authorization;
import tn.iit.models.Teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuthorizationDAO;
import dao.TeacherDAO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet("/Submit")
public class Submit extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Authorization> authorizations;
	private TeacherDAO teacherDAO;
	private AuthorizationDAO authorizationDAO;

    @Override
    public void init() throws ServletException {
    	this.teacherDAO =new TeacherDAO();
    	this.authorizationDAO =new AuthorizationDAO();
        super.init();
       
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the form data
        int duration = Integer.parseInt(request.getParameter("hours"));
        String company = request.getParameter("companyName");
        String dateStr = request.getParameter("date");

        // Convert the date string to a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the error case
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        // Retrieve the selected teacher from the request
        Teacher selectedTeacher = teacherDAO.getTeacherById(id);

        // Get the current week's start and end dates
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        Date weekStartDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndDate = calendar.getTime();

        // Get the list of authorizations for the selected teacher
        List<Authorization> authorizations = authorizationDAO.getAuthorizationsByTeacher(selectedTeacher);

        // Calculate the sum of authorization durations for the current week
        int currentWeekDurationSum = 0;
        for (Authorization authorization : authorizations) {
            Date authorizationDate = authorization.getDate();
            if (authorizationDate.compareTo(weekStartDate) >= 0 && authorizationDate.compareTo(weekEndDate) <= 0) {
                currentWeekDurationSum += authorization.getDuration();
            }
        }

        // Check if the requested duration is available for this week
        if (currentWeekDurationSum + duration <= 4) {
            // Create a new authorization object and save it to the database
            Authorization newAuthorization = new Authorization(selectedTeacher, date, duration, company);
            authorizationDAO.addAuthorization(newAuthorization);
            selectedTeacher.setNombreHeure(selectedTeacher.getNombreHeure()- duration);
            teacherDAO.update(selectedTeacher);
            request.setAttribute("authorization", newAuthorization);
            request.setAttribute("selectedTeacher", selectedTeacher);
            
            // Redirect to a success page or display a success message
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pdfcontroller");
            dispatcher.forward(request, response);        } else {
            // Redirect to an error page or display an error message
        	int remainingHours = 4-currentWeekDurationSum;

        	String alertMessage = "You have exceeded the allowed hours per week. Remaining hours: " + remainingHours;
              request.setAttribute("alertMessage", alertMessage);

              // Forward the request to the form page
              RequestDispatcher dispatcher = request.getRequestDispatcher("request.jsp");
              dispatcher.forward(request, response);        }
    }
}
