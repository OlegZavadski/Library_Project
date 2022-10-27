<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Admin header</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Library</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page"
                       href="${pageContext.request.contextPath}/logout">
                        Welcome Back, <sec:authentication property="name"/>!
                        Logout</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/admin">Show all
                        users</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page"
                       href="${pageContext.request.contextPath}/admin/show_all_books">Show all books</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page"
                       href="${pageContext.request.contextPath}/admin/show_overdue_books">Show overdue books</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page"
                       href="${pageContext.request.contextPath}/admin/registration">New
                        user registration</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page"
                       href="${pageContext.request.contextPath}/admin/save_new_book">Save a new book</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0" method="get"
                  action="${pageContext.request.contextPath}/search">
                <input class="form-control mr-sm-2" type="search" placeholder="Input author or title"
                       aria-label="Search" name="forSearch">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search a book</button>
            </form>
        </div>
    </div>
</nav>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>
</html>
