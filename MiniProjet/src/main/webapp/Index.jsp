<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="tn.iit.models.Teacher" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teacher List</title>
</head>
<body>
    <h1>Teacher List</h1>
    
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Number of Hours</th>
        </tr>
        
        <%-- Check if the teachers list is not null --%>
        <% 
            List<Teacher> teachers = (List<Teacher>) request.getServletContext().getAttribute("teacher");
            if (teachers != null) {
                for (Teacher teacher : teachers) {
        %>
        <tr>
            <td><%= teacher.getId() %></td>
            <td><%= teacher.getName() %></td>
            <td><%= teacher.getNombreHeure() %></td>
        </tr>
        <% 
                }
            } else {
        %>
        <tr>
            <td colspan="3">No teachers found.</td>
        </tr>
        <% } %>
    </table>
    
    <br>
    <a href="AddTeacher.jsp">Add Teacher</a>
</body>
</html>
