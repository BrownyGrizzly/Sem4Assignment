<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${classroom != null ? "Edit Classroom" : "Add Classroom"}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">${classroom != null ? "Edit Classroom" : "Add New Classroom"}</h1>
    <a href="classroom?action=LIST" class="btn btn-secondary mb-3">Back to Classroom List</a>

    <form action="classroom" method="post">
        <input type="hidden" name="_method" value="${classroom != null ? 'PUT' : 'POST'}">
        <c:if test="${classroom != null}">
            <input type="hidden" name="id" value="${classroom.id}">
        </c:if>
        <div class="mb-3">
            <label for="name" class="form-label">Classroom Name</label>
            <input type="text" class="form-control" id="name" name="name"
                   value="${classroom != null ? classroom.name : ''}" required>
        </div>
        <button type="submit" class="btn btn-primary">${classroom != null ? "Update" : "Add"} Classroom</button>
    </form>
</div>
</body>
</html>
