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
    <form action="${pageContext.request.contextPath}/player?action=ADD" method="POST" class="mb-5" onsubmit="return validateForm()">
        <div class="row">
            <!-- Player name and age -->
            <div class="col-md-6">
                <div class="form-group">
                    <label for="playerName">Player Name</label>
                    <input type="text" class="form-control" id="playerName" name="playerName" required>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="playerAge">Player Age</label>
                    <input type="text" class="form-control" id="playerAge" name="playerAge" pattern="^[0-9]{1,2}$" title="Age should be a number (1-99)" required>
                </div>
            </div>
        </div>
        <div class="row">
            <!-- Index name and value -->
            <div class="col-md-6">
                <div class="form-group">
                    <label for="indexName">Index Name</label>
                    <select class="form-control" id="indexName" name="indexName" required>
                        <option value="">Select Index</option>
                        <option value="speed">Speed</option>
                        <option value="strength">Strength</option>
                        <option value="accurate">Accurate</option>
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
        <c:forEach var="player" items="${playerList}">
            <tr>
                <td>${player.player_id}</td>
                <td>${player.name}</td>
                <td>${player.age}</td>
                <td>${player.index_name}</td>
                <td>${player.value}</td>
                <td><a href="player?action=EDIT&id=${player.player_id}" class="btn btn-warning">Edit</a></td>
                <td><a href="player?action=DELETE&id=${player.player_id}" class="btn btn-danger">Delete</a></td>
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
        var playerName = document.getElementById("playerName").value;
        var playerAge = document.getElementById("playerAge").value;
        var indexName = document.getElementById("indexName").value;
        var value = document.getElementById("value").value;

        if (playerName.trim() == "") {
            alert("Player name must be filled out");
            return false;
        }
        if (isNaN(playerAge) || playerAge < 1 || playerAge > 99) {
            alert("Player age must be a number between 1 and 99");
            return false;
        }
        if (indexName == "") {
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
