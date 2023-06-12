<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="tn.iit.models.Teacher" %>
<%@ page import="tn.iit.models.Authorization" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Authorization List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
          crossorigin="anonymous">
</head>
<body>
<h1 style="color: red" class="card-footer fw-bold text-center my-3">Authorization List</h1>

<div class="container mt-5 mb-1 card text-center">
    <%-- Retrieve the teacher and authorizations from the request --%>
    <% Teacher teacher = (Teacher) request.getAttribute("teacher"); %>
    <% List<Authorization> authorizations = (List<Authorization>) request.getAttribute("authorizations"); %>

    <%-- Display the teacher information --%>
    <h2>Teacher: <%= teacher.getName() %></h2>
     <h2>Number of hours remaining: <%= teacher.getNombreHeure() %></h2>
 
    <%-- Display the authorizations in a table --%>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Date</th>
            <th>teacher name</th>
            <th>duration</th>
            <th>place</th>
        </tr>
        </thead>
        <tbody>
        <% if (!authorizations.isEmpty()) {
            for (Authorization authorization : authorizations) {
                %>
                <tr>
                    <td><%= authorization.getDate() %></td>
                    <td><%= authorization.getTeacher().getName() %></td>
                    <td><%= authorization.getDuration() %></td>
                    <td><%= authorization.getPlace() %></td>
                </tr>
                <%
            }
        } else {
            %>
            <tr>
                <td colspan="3">No authorizations found.</td>
            </tr>
            <%
        } %>
        </tbody>
    </table>
<div class="d-flex justify-content-center">
    <a href="Index.jsp" class="btn btn-secondary mx-2 mb-3">Back to Teacher List</a>
    <a href="request.jsp?id=<%= teacher.getId()%>" class="btn btn-secondary mx-2 mb-3">Authorisation</a>
</div>

</div>
</body>
</html>
