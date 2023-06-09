<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Teacher</title>
</head>
<body>
    <h1>Add Teacher</h1>
    
    <form action="AddController" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br>
        
        <label for="nombreHeure">Number of Hours:</label>
        <input type="number" id="nombreHeure" name="nombreHeure" required><br>
        
        <input type="submit" value="Add Teacher">
    </form>
    
    <br>
    <a href="Index.jsp">Back to Teacher List</a>
</body>
</html>
