<!DOCTYPE html>
<html>
<head>
    <title>Subject Form</title>
</head>
<body>
<h1>${subject != null ? "Edit Subject" : "Add New Subject"}</h1>
<form action="subject" method="post">
    <input type="hidden" name="action" value="${subject != null ? 'UPDATE' : 'SAVE'}">
    <c:if test="${subject != null}">
        <input type="hidden" name="id" value="${subject.id}">
    </c:if>
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${subject != null ? subject.name : ''}" required><br><br>
    <label for="hours">Hours:</label>
    <input type="number" id="hours" name="hours" value="${subject != null ? subject.hours : ''}" required><br><br>
    <button type="submit">${subject != null ? "Update" : "Save"}</button>
</form>
<a href="subject?action=LIST">Back to List</a>
</body>
</html>
