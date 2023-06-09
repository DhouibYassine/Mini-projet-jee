package tn.iit.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class TeacherServlet extends HttpServlet {
    
    private List<Teacher> teachers = new ArrayList<>();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacherName = request.getParameter("teacherName");
        Teacher teacher = new Teacher(teacherName);
        teachers.add(teacher);
        response.sendRedirect("teachers.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("teachers", teachers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("teachers.jsp");
        dispatcher.forward(request, response);
    }
    
    class Teacher {
        private static final long serialVersionUID = 1L;
        private static int count = 1;
        private int id;
        private String name;
        
        public Teacher(String name) {
            this.id = count++;
            this.name = name;
        }
        
        public int getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
    }
}
