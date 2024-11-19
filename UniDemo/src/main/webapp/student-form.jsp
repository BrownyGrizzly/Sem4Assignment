<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${student != null ? "Edit Student" : "Add New Student"}</title>
    <link rel="stylesheet" href="styles.css"> <!-- Optional: Add your styles -->
</head>
<body>
<h1>${student != null ? "Edit Student" : "Add New Student"}</h1>
<form action="student" method="post">
    <input type="hidden" name="action" value="${student != null ? "UPDATE" : "SAVE"}">
    <c:if test="${student != null}">
        <input type="hidden" name="id" value="${student.id}">
    </c:if>
    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${student != null ? student.name : ''}" required>
    </div>
    <div>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${student != null ? student.email : ''}" required>
    </div>
    <div>
        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="${student != null ? student.phone : ''}" required>
    </div>
    <div>
        <label for="classroom">Classroom:</label>
        <select id="classroom" name="classroomId" required>
            <option value="">Select Classroom</option>
            <c:forEach var="classroom" items="${classrooms}">
                <option value="${classroom.id}"
                    ${student != null && student.classroom.id == classroom.id ? "selected" : ""}>
                        ${classroom.name}
                </option>
            </c:forEach>
        </select>
    </div>
    <button type="submit">${student != null ? "Update" : "Save"}</button>
    <a href="student?action=LIST">Cancel</a>
</form>
</body>
</html>
