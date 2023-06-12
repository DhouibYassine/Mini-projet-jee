<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="tn.iit.models.Teacher" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.Transaction" %>
<%@ page import="org.hibernate.cfg.Configuration" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teacher List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <style>
        .table-container {
            margin: 50px auto;
            width: 80%;
        }
        .add-teacher-container {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1 style="color:red" class="card-footer fw-bold text-center my-3">Teacher List</h1>

    <div class="table-container">
        <table class="table table-striped table-bordered text-center">
            <thead class="table-dark ">
                <tr>
                    <th >ID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>CIN</th>
                    <th>Age</th>
                    <th>Number of Hours</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%-- Retrieve the list of teachers from the database --%>
               <% 
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");
                SessionFactory sessionFactory = configuration.buildSessionFactory();
                Session hibernateSession = sessionFactory.openSession();
                Transaction transaction = hibernateSession.beginTransaction();

                List<Teacher> teachers = hibernateSession.createQuery("FROM Teacher", Teacher.class).list();
                ServletContext context = request.getServletContext();
                context.setAttribute("teacher", teachers);
                transaction.commit();
                hibernateSession.close();
                sessionFactory.close(); 

                if (!teachers.isEmpty()) {
                    for (Teacher teacher : teachers) {
                %>
                <tr>
                    <td><%= teacher.getId() %></td>
                    <td><%= teacher.getName() %></td>
                    <td><%= teacher.getSurname() %></td>
                    <td><%= teacher.getCin() %></td>
                    <td><%= teacher.getAge() %></td>
                    
                    <td><%= teacher.getNombreHeure() %></td>
                    <td>
                        <a href="AuthorizationController?id=<%= teacher.getId() %>" class="btn btn-primary">Authorisation</a>
                        <a href="EditTeacher?id=<%= teacher.getId() %>" class="mx-3 px-4 btn btn-warning">Edit</a>
                        <a href="DeleteController?id=<%= teacher.getId() %>" class="btn btn-danger">Delete</a>
                        
                    </td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4">No teachers found.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <div class="add-teacher-container">
        <a href="ListController" class="btn btn-success px-5 py-3">view all Authorization</a>    
        <a href="AddTeacher.jsp" class="btn btn-success px-5 py-3">Add Teacher</a>
    </div>    
    </body>
</html>
