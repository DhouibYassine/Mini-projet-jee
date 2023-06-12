package tn.iit.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import tn.iit.models.Teacher;

@WebServlet("/EditTeacher")
public class EditTeacher extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the teacher ID from the request parameters
        int teacherId = Integer.parseInt(request.getParameter("id"));
        System.out.println(teacherId);
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Open a new Hibernate session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();

        // Fetch the teacher to be edited
        Teacher teacher = session.get(Teacher.class, teacherId);

        // Commit the transaction
        transaction.commit();

        // Close the session and session factory
        session.close();
        sessionFactory.close();

        // Set the teacher object as an attribute in the request
        request.setAttribute("teacher", teacher);

        // Forward the request to the JSP page for editing
        request.getRequestDispatcher("EditTeacher.jsp").forward(request, response);
    }
}
