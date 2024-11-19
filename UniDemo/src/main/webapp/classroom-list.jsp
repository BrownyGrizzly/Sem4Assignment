<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Classroom List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Classroom Management</h1>
    <a href="index.jsp" class="btn btn-secondary mb-3">Back to Home</a>
    <a href="classroom?action=ADD" class="btn btn-primary mb-3">Add New Classroom</a>

    <!-- Classroom List -->
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Number of Students</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="classroom" items="${classrooms}">
            <tr>
                <td>${classroom.id}</td>
                <td>${classroom.name}</td>
                <td>${classroom.students.size()}</td>
                <td>
                    <a href="classroom?action=EDIT&id=${classroom.id}" class="btn btn-sm btn-warning">Edit</a>
                    <a href="classroom?action=DELETE&id=${classroom.id}"
                       onclick="return confirm('Are you sure you want to delete this classroom?');"
                       class="btn btn-sm btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
