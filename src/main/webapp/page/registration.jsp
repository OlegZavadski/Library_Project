<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Page for registration a new user</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>

<jsp:include page="admin_header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-3">
        </div>
        <div class="col-6">
            <form action="${pageContext.request.contextPath}/admin/registration" method="post">
                <input class="form-control mr-sm-2" type="text" name="login"
                       placeholder="Input new login"
                       aria-label="Login">
                <input class="form-control mr-sm-2" type="password" name="password"
                       placeholder="Input new password"
                       aria-label="Password">
                <div class="buttons">
                    <button type="submit" class="btn btn-outline-primary"
                            style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: 15px;"
                            value="Enter">Enter
                    </button>
                    <button type="reset" class="btn btn-outline-secondary"
                            style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: 15px;"
                            value="Reset">Reset
                    </button>
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
