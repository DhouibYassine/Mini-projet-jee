<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="tn.iit.models.Teacher" %>
<%@ page import="tn.iit.models.Authorization" %>
<%@ page import="java.util.GregorianCalendar" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Authorization</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
          crossorigin="anonymous">
</head>
<body>
<script>
        var alertMessage = '<%= request.getAttribute("alertMessage") %>';
        if (alertMessage !="null") {
            alert(alertMessage);
        }
    </script>
<h1 class="card-footer fw-bold text-center my-3">Edit Authorization</h1>

<div class="container mt-5 mb-5 card text-center">
    <form action="UpdateAuthorizationController" method="post">
        <input type="hidden" name="authorizationId" value="${authorization.id}">
        
        <div class="mb-3">
            <label for="date" class="form-label">Date:</label>
            <input type="date" id="date" name="date" class="form-control" value="${authorization.date}" min="<%= getMinDate() %>" max="<%= getMaxDate() %>"  required>
        </div>

        <div class="mb-3">
            <label for="duration" class="form-label">Duration:</label>
            <input type="text" id="duration" name="duration" class="form-control" value="${authorization.duration}" required>
        </div>

        <div class="mb-3">
            <label for="place" class="form-label">Company:</label>
            <input type="text" id="place" name="place" class="form-control" value="${authorization.place}" required>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>

</body>
</html>

<%!
    private String getMinDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        Date minDate = calendar.getTime();
        return formatDate(minDate);
    }

    private String getMaxDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        Date maxDate = calendar.getTime();
        return formatDate(maxDate);
    }

    private String formatDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return String.format("%d-%02d-%02d", year, month, day);
    }
%>
