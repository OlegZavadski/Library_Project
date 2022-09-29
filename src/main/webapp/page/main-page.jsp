<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Main page</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <sec:authorize access="isAnonymous()">
            <a class="navbar-brand">Library</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/admin">Library</a>
        </sec:authorize>
        <sec:authorize access="hasRole('USER')">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/user">Library</a>
        </sec:authorize>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <sec:authorize access="!isAuthenticated()">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/login">Login</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/logout">
                        Welcome Back, <sec:authentication property="name"/>!
                        Logout</a>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <table class="table table-striped">
            <caption>All books in the library</caption>
            <thead>
            <tr>
                <th scope="col">№ of a line</th>
                <th scope="col">Author of a book</th>
                <th scope="col">Name of a book</th>
                <th scope="col">Count of a books</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book" varStatus="loop">
                <tr>
                    <th scope="row"> ${loop.count}</th>
                    <td> ${book.author}</td>
                    <td> ${book.name}</td>
                    <td> ${book.count}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>
</html>
