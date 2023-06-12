<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="tn.iit.models.Teacher" %>
<%@ page import="tn.iit.models.Authorization" %>
<%@ page import="java.util.GregorianCalendar" %>
<%String idParam = request.getParameter("id");
int id;
try {
    id = Integer.parseInt(idParam);
} catch (NumberFormatException e) {
    e.printStackTrace();
    // Handle the case where the ID parameter is not a valid integer
    return;
} %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Authorization Request Form</title>
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
<h1 style="color:red" class="card-footer fw-bold text-center my-3">Authorization Request Form</h1>
    

<div class="container mt-5 mb-1 card">
    <form action="Submit" method="POST">
    <input type="hidden" name="id" value="<%= id %>">
        <div class="my-3">
            <label for="hours" class="form-label">Number of Hours (between 1 and 4)</label>
            <input type="number" class="form-control" id="hours" name="hours" min="1" max="4" required>
        </div>
        <div class="mb-3">
            <label for="companyName" class="form-label">Company Name</label>
            <input type="text" class="form-control" id="companyName" name="companyName" required>
        </div>
        <div class="mb-3">
            <label for="date" class="form-label">Date (Current Week)</label>
            <input type="date" class="form-control" id="date" name="date" min="<%= getMinDate() %>" max="<%= getMaxDate() %>" required>
        </div>
<a href="Index.jsp" class="btn btn-secondary px-3 py-2 mb-3 ms-1">Return to Home</a>
<button type="submit" class="btn btn-primary px-3 py-2 mb-3 ms-1">Submit</button>

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
