package tn.iit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuthorizationDAO;
import dao.TeacherDAO;
import tn.iit.models.Authorization;
import tn.iit.models.Teacher;
@WebServlet("/AuthorizationController")

public class AuthorizationController extends HttpServlet {
    private AuthorizationDAO authorizationDAO;
    private TeacherDAO teacherDAO;

    public AuthorizationController() {
        this.authorizationDAO = new AuthorizationDAO();
        this.teacherDAO =new TeacherDAO();
    }
    
   	private static final long serialVersionUID = 1L;
       
       @Override
       	

           protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               int teacherId = Integer.parseInt(request.getParameter("id"));

               Teacher teacher = teacherDAO.getTeacherById(teacherId);
               if (teacher != null) {
                   List<Authorization> authorizations = authorizationDAO.getAuthorizationsByTeacher(teacher);

                   request.setAttribute("teacher", teacher);
                   request.setAttribute("authorizations", authorizations);
                   request.getRequestDispatcher("authorization.jsp").forward(request, response);}
               }
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           doGet(request, response);
       }
       	   

    public List<Authorization> getAuthorizationsForTeacher(Teacher teacher) {
        // Logic to fetch authorizations for a specific teacher from the DAO
        return authorizationDAO.getAuthorizationsByTeacher(teacher);
    }

   

    // Other methods for authorization-related actions

    // You can add more methods as per your application requirements

    // ...
}
