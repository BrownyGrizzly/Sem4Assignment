<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Player Information</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Player Information</h2>

    <!-- Display error message -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
                ${error}
        </div>
    </c:if>

    <!-- Player Form -->
    <form action="${pageContext.request.contextPath}/player" method="POST" class="mb-5" onsubmit="return validateForm()">
        <div class="row">
            <!-- Player name and age -->
            <div class="col-md-6">
                <div class="form-group">
                    <label for="name">Player Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="age">Player Age</label>
                    <input type="text" class="form-control" id="age" name="age" pattern="^[0-9]{1,2}$" title="Age should be a number (1-99)" required>
                </div>
            </div>
        </div>
        <div class="row">
            <!-- Index name and value -->
            <div class="col-md-6">
                <div class="form-group">
                    <label for="indexId">Index Name</label>
                    <select class="form-control" id="indexId" name="indexId" required>
                        <option value="">Select Index</option>
                        <c:forEach var="indexer" items="${indexer}">
                            <option value="${indexer.id}">${indexer.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="value">Value</label>
                    <input type="number" class="form-control" id="value" name="value" min="0" max="100" required>
                </div>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Add</button>
    </form>

    <!-- Player List -->
    <h3>Player List</h3>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Player Name</th>
            <th>Player Age</th>
            <th>Index Name</th>
            <th>Value</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="player" items="${viewListPlayerList}">
            <tr>
                <td>${player.id}</td>
                <td>${player.name}</td>
                <td>${player.age}</td>
                <td>${player.indexName}</td>
                <td>${player.value}</td>
                <td><a href="player?action=EDIT&id=${player.id}" class="btn btn-warning">Edit</a></td>
                <td><a href="player?action=DELETE&id=${player.id}" class="btn btn-danger">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<footer>
    <p class="text-center">Số 8, Tôn Thất Thuyết, Cầu Giấy, Hà Nội</p>
</footer>

<script>
    // Custom JavaScript validation (optional)
    function validateForm() {
        var name = document.getElementById("name").value;
        var age = document.getElementById("age").value;
        var indexId = document.getElementById("indexId").value;
        var value = document.getElementById("value").value;

        if (name.trim() == "") {
            alert("Player name must be filled out");
            return false;
        }
        if (isNaN(age) || age < 1 || age > 99) {
            alert("Player age must be a number between 1 and 99");
            return false;
        }
        if (indexId == "") {
            alert("Please select an index");
            return false;
        }
        if (isNaN(value) || value < 0 || value > 100) {
            alert("Value must be a number between 0 and 100");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
