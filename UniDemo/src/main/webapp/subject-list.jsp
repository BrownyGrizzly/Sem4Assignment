<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Subject List</title>
</head>
<body>
<h1>Subject List</h1>
<a href="subject?action=ADD">Add New Subject</a>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Hours</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="subject" items="${subjects}">
        <tr>
            <td>${subject.id}</td>
            <td>${subject.name}</td>
            <td>${subject.hours}</td>
            <td>
                <a href="subject?action=EDIT&id=${subject.id}">Edit</a> |
                <a href="subject?action=DELETE&id=${subject.id}" onclick="return confirm('Are you sure?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
