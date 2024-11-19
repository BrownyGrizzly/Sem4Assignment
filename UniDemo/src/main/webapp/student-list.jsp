<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student List</title>
    <link rel="stylesheet" href="styles.css"> <!-- Optional: Add your styles -->
</head>
<body>
<h1>Student Management</h1>
<a href="student?action=ADD">Add New Student</a>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Classroom</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.email}</td>
            <td>${student.phone}</td>
            <td>${student.classroom.name}</td>
            <td>
                <a href="student?action=EDIT&id=${student.id}">Edit</a> |
                <a href="student?action=DELETE&id=${student.id}" onclick="return confirm('Are you sure?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
