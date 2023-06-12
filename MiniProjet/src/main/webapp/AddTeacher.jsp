<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Teacher</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
    <h1 style="color:red" class="card-footer fw-bold text-center my-3">Add Teacher</h1>

   <div class="container mt-5 mb-1 card">

    <form action="AddController" method="post">
        <div class="my-3">
            <label for="name" class="form-label">Name:</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="cin" class="form-label">CIN (8 digits):</label>
            <input type="text" id="cin" name="cin" class="form-control" required pattern="\d{8}">
        </div>

        <div class="mb-3">
            <label for="surname" class="form-label">Surname:</label>
            <input type="text" id="surname" name="surname" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="age" class="form-label">Age:</label>
            <input type="number" id="age" name="age" class="form-control" required>
        </div>
            <a href="Index.jsp" class="btn btn-secondary mb-3">Back to Teacher List</a>

        <button type="submit" class="btn btn-primary mb-3">Add Teacher</button>
        
    </form>

</div>

</body>
</html>
