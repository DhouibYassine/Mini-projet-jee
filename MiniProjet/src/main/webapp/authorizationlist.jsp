<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="tn.iit.models.Teacher" %>
<%@ page import="tn.iit.models.Authorization" %>
<%@ page import="dao.AuthorizationDAO" %>
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
<div class="row justify-content-end">
    <div class="col-md-5 my-3">
        <form action="AuthorizationFilterController" method="post" class="d-flex">
            <label for="month" class="form-label">Filter by Month:</label>
            <div class="input-group">
                <input type="month" id="month" name="month" class="form-control" required>
                <input type="hidden" name="filterType" value="month">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </form>
    </div>
    <div class="col-md-5 my-3">
        <form action="AuthorizationFilterController" method="post" class="d-flex">
            <label for="date" class="form-label">Filter by Date:</label>
            <div class="input-group">
                <input type="date" id="date" name="date" class="form-control" required>
                <input type="hidden" name="filterType" value="date">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </form>
    </div>
    <div class="col-md-2 my-3">
        <c:if test="${not empty filterType}">
            <form action="AuthorizationFilterController" method="post" class="d-flex justify-content-end">
                <input type="hidden" name="filterType" value="remove">
                <button type="submit" class="btn btn-success mt-2 me-4">Remove Filter</button>
            </form>
        </c:if>
    </div>
</div>


    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Teacher Name</th>
            <th>Authorization Date</th>
            <th>Duration</th>
            <th>Company</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <%-- Retrieve the list of authorizations from the request --%>
        <% 
            List<Authorization> authorizations = (List<Authorization>) request.getAttribute("authorizations");
            if (!authorizations.isEmpty()) {
                for (Authorization authorization : authorizations) {
                    %>
                    <tr>
                        <td><%= authorization.getTeacher().getName() %></td>
                        <td><%= authorization.getDate() %></td>
                        <td><%= authorization.getDuration() %></td>
                        <td><%= authorization.getPlace() %></td>
                        <td>
                            <form action="EditAuthorizationController" method="post">
                                <input type="hidden" name="authorizationId" value="<%= authorization.getId() %>">
                                <button type="submit" class="btn btn-warning">Edit</button>
                            </form>
                        </td>
                        <td>
                            <form action="DeleteAuthorizationController" method="post">
                                <input type="hidden" name="authorizationId" value="<%= authorization.getId() %>">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <%
                }
            } else {
                %>
                <tr>
                    <td colspan="6">No authorizations found</td>
                </tr>
                <%
            }
         %>
        </tbody>
    </table>
    
    <form action="Index.jsp">
        <button type="submit" class="btn btn-secondary mb-3">return to home</button>
    </form>
</div>

</body>
</html>
