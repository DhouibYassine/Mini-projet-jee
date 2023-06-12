<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="tn.iit.models.Teacher" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Teacher</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1 style="color:red" class="card-footer fw-bold text-center my-3">Edit Teacher</h1>

        <%-- Retrieve the teacher object from the request --%>
        <% Teacher teacher = (Teacher) request.getAttribute("teacher"); %>

        <%-- Display the current values in input fields for editing --%>
        
        <form action="UpdateTeacherServlet" method="post" class="mt-5 mb-1 card">
            <input type="hidden" name="id" value="<%= teacher.getId() %>">
            <div class="m-3">
                <label for="name" class="form-label ">Name:</label>
                <input type="text" name="name" id="name" class="form-control" value="<%= teacher.getName() %>">
            </div>
                        <div class="mb-3 mx-3">
                <label for="surname" class="form-label">Surname:</label>
                <input type="text" name="surname" id="surname" class="form-control" value="<%= teacher.getSurname() %>">
            </div>
            
             <div class="mb-3 mx-3">
                <label for="age" class="form-label">Age:</label>
                <input type="text" name="age" id="age" class="form-control" value="<%= teacher.getAge() %>">
            </div>
            
            <div class="mb-3 mx-3">
                <label for="cin" class="form-label">CIN:</label>
                <input type="text" name="cin" id="cin" class="form-control" value="<%= teacher.getCin() %>">
            </div>
            <button type="submit" class="m-3 btn btn-success">Update</button>
        </form>
    </div>
</body>
</html>
