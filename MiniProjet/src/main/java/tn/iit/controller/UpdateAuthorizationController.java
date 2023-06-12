package tn.iit.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tn.iit.models.Authorization;
import tn.iit.models.Teacher;
import dao.AuthorizationDAO;
import dao.TeacherDAO;

@WebServlet("/UpdateAuthorizationController")
public class UpdateAuthorizationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AuthorizationDAO authorizationDAO;
 private TeacherDAO teacherDAO;
    public void init() {
        authorizationDAO = new AuthorizationDAO();
        teacherDAO =new TeacherDAO();// Initialize the DAO object
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the authorization ID and updated details from the request parameters
        int authorizationId = Integer.parseInt(request.getParameter("authorizationId"));
        String dateStr = request.getParameter("date");
        int duration = Integer.parseInt(request.getParameter("duration"));
        String place = request.getParameter("place");

        // Get the existing authorization from the database
        Authorization authorization = authorizationDAO.getAuthorizationById(authorizationId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the error case
            response.sendRedirect("authorizationlist.jsp");
            return;
        }
        Teacher selectedTeacher = authorization.getTeacher();

        // Retrieve the selected teacher from the request

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
        for (Authorization authorizationlist : authorizations) {
            Date authorizationDate = authorizationlist.getDate();
            if (authorizationDate.compareTo(weekStartDate) >= 0 && authorizationDate.compareTo(weekEndDate) <= 0) {
                currentWeekDurationSum += authorizationlist.getDuration();
            }
        }

        // Calculate the previous duration of the authorization
        int previousDuration = authorization.getDuration();

        // Check if the requested duration is available for this week
        if (currentWeekDurationSum - previousDuration + duration <= 4) {
            // Create a new authorization object and save it to the database
        	authorization.setDate(date);
        	authorization.setDuration(duration);
        	authorization.setPlace(place);
        	
            // Update the authorization in the database
            authorizationDAO.updateAuthorization(authorization);

            // Update the teacher's hours
            int updatedTeacherHours = selectedTeacher.getNombreHeure() + previousDuration - duration;
            selectedTeacher.setNombreHeure(updatedTeacherHours);
            teacherDAO.update(selectedTeacher);

            // Set the attributes for the success case
            request.setAttribute("authorization", authorization);
            request.setAttribute("selectedTeacher", selectedTeacher);

            // Forward to the success page
            RequestDispatcher dispatcher = request.getRequestDispatcher("ListController");
            dispatcher.forward(request, response);
        } else {
            // Calculate the remaining hours based on the current week's limit
            int remainingHours = 4 - currentWeekDurationSum + previousDuration;

            // Set the alert message
            String alertMessage = "You have exceeded the allowed hours per week. Remaining hours: " + remainingHours;
            request.setAttribute("alertMessage", alertMessage);
            request.setAttribute("authorization", authorization);
            // Forward to the form page for error handling
            RequestDispatcher dispatcher = request.getRequestDispatcher("EditAuthorization.jsp");
            dispatcher.forward(request, response);
        }
    }

}
