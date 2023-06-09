package tn.iit.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class AuthorizationServlet extends HttpServlet {
    
    private List<Authorization> authorizations = new ArrayList<>();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        Teacher teacher = getTeacherById(teacherId);
        
        // Calcul du nombre d'heures autorisées pour l'enseignant
        int remainingWeeks = getRemainingWeeks();
        int authorizedHours = remainingWeeks * 4; // 4 heures par semaine
        Authorization authorization = new Authorization(teacher, authorizedHours);
        authorizations.add(authorization);
        
        response.sendRedirect("editAuthorization.jsp?teacherId=" + teacherId);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        Teacher teacher = getTeacherById(teacherId);
        List<Authorization> teacherAuthorizations = getAuthorizationsByTeacher(teacher);
        
        request.setAttribute("teacher", teacher);
        request.setAttribute("authorizations", teacherAuthorizations);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editAuthorization.jsp");
        dispatcher.forward(request, response);
    }
    
    private Teacher getTeacherById(int teacherId) {
        Teacher[] teachers = null;//zeyda
		for (Teacher teacher : teachers) {
            if (teacher.getId() == teacherId) {
                return teacher;
            }
        }
        return null;
    }
    
    private List<Authorization> getAuthorizationsByTeacher(Teacher teacher) {
        List<Authorization> teacherAuthorizations = new ArrayList<>();
        for (Authorization authorization : authorizations) {
            if (authorization.getTeacher().equals(teacher)) {
                teacherAuthorizations.add(authorization);
            }
        }
        return teacherAuthorizations;
    }
    
    private int getRemainingWeeks() {
        // Calcul du nombre de semaines restantes de l'année courante
        // ...
        return getRemainingWeeks();
    }
}

class Authorization {
    private Teacher teacher;
    private int authorizedHours;
    
    public Authorization(Teacher teacher, int authorizedHours) {
        this.teacher = teacher;
        this.authorizedHours = authorizedHours;
    }
    
    public Teacher getTeacher() {
        return teacher;
    }
    
    public int getAuthorizedHours() {
        return authorizedHours;
    }
}

