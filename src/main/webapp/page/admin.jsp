<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Admin page</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand">Library</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/logout">
                        Welcome Back, <sec:authentication property="name"/>!
                        Logout</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenu" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        Actions with clients
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/admin/registration">New
                            client registration</a></li>
                        <li><a class="dropdown-item" href="/admin/show_all_clients">Show all clients with books</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        Actions with books
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/admin/show_all_books">Show all books</a></li>
                        <li><a class="dropdown-item" href="/admin/add_book_to_user">Add a book to client</a></li>
                        <li><a class="dropdown-item" href="/admin/return_book_from_user">Return a book from
                            client</a></li>
                    </ul>
                </li>
            </ul>
            <form class="d-flex" action="${pageContext.request.contextPath}/admin/delete" method="post">
                <input class="form-control me-2" type="number" placeholder="Input id of client to delete"
                       aria-label="Search" name="idToDelete">
                <button class="btn btn-outline-success" type="submit">Delete</button>
            </form>
            <H3><span style="color: #b22222; ">${error}</span></H3>
        </div>
    </div>
</nav>

<c:if test="${allUsers.size()>0}">
    <div class="container">
        <div class="row">
            <table class="table table-striped">
                <caption>All clients with books</caption>
                <thead>
                <tr>
                    <th scope="col">№ of a line</th>
                    <th scope="col">Id of a client</th>
                    <th scope="col">Login of a client</th>
                    <th scope="col">Books of a client</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allUsers}" var="client" varStatus="loop">
                    <c:if test="${client.books.size()>0}">
                        <tr>
                            <th scope="row"> ${loop.count}</th>
                            <td> ${client.id}</td>
                            <td> ${client.login}</td>
                            <td> ${client.books}</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>

<c:if test="${books.size()>0}">
    <div class="container">
        <div class="row">
            <table class="table table-striped">
                <caption>All books</caption>
                <thead>
                <tr>
                    <th scope="col">№ of a line</th>
                    <th scope="col">Id of a book</th>
                    <th scope="col">Author of a book</th>
                    <th scope="col">Name of a book</th>
                    <th scope="col">Count of a books</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${books}" var="book" varStatus="loop">
                    <tr>
                        <th scope="row"> ${loop.count}</th>
                        <td> ${book.id}</td>
                        <td> ${book.author}</td>
                        <td> ${book.name}</td>
                        <td> ${book.count}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>
</html>
