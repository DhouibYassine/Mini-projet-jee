<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Édition de l'autorisation</title>
</head>
<body>
    <h1>Édition de l'autorisation</h1>
    
    <!-- Affichage des détails de l'enseignant -->
    <p>Nom de l'enseignant : ${teacher.name}</p>
    <p>Nombre d'heures</p>
        <!-- Formulaire d'édition de l'autorisation -->
    <form action="AuthorizationServlet" method="post">
        <input type="hidden" name="teacherId" value="${teacher.id}">
        <input type="submit" value="Générer autorisation">
    </form>
    
    <!-- Affichage des autorisations par mois et par date -->
    <h2>Autorisations de l'enseignant ${teacher.name}</h2>
    <table>
        <thead>
            <tr>
                <th>Mois</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="authorization" items="${authorizations}">
                <tr>
                    <td>${authorization.month}</td>
                    <td>${authorization.date}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
    
