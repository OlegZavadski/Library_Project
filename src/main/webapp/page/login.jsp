<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Login page</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>

<div class="container mt-2">
    <div class="row">
        <div class="col-3">
        </div>
        <div class="col-6">
            <H2 style="color: red">
                ${error}
            </H2>
            <form action="${pageContext.request.contextPath}/process_login" method="post">
                <input class="form-control mr-sm-2" type="text" name="username"
                       placeholder="Input username"
                       aria-label="Login">
                <input class="form-control mr-sm-2" type="password" name="password"
                       placeholder="Input password"
                       aria-label="Password"><br>
                <div class="d-grid gap-2 col-8 mx-auto">
                    <button class="btn btn-primary" type="submit">Enter</button>
                    <button class="btn btn-secondary" type="reset">Reset</button>
                </div>
            </form>
        </div>
        <div class="col-3">
        </div>
    </div>
</div>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>
</html>
