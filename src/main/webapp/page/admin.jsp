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
        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin">Library</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/logout">
                        Welcome Back, <sec:authentication property="name"/>!
                        Logout</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/admin/registration">New
                        client registration</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        Actions with books
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/admin/show_all_books">Show all books</a></li>
                        <li><a class="dropdown-item" href="/admin/add_book_to_user">Add a book to a client</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<c:if test="${allUsers.size()>0}">
    <div class="container">
        <div class="row">
            <table class="table table-striped">
                <caption>All clients</caption>
                <thead>
                <tr>
                    <th scope="col">№ of the line</th>
                    <th scope="col">Login of the client</th>
                    <th scope="col">Show books of the client</th>
                    <th scope="col">Delete the client</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allUsers}" var="client" varStatus="loop">
                    <tr>
                        <th scope="row"> ${loop.count}</th>
                        <td> ${client.login}</td>
                        <c:choose>
                            <c:when test="${client.books.size()>0}">
                                <td>
                                    <form class="d-flex"
                                          action="${pageContext.request.contextPath}/admin/show_books_of_client"
                                          method="post">
                                        <input type="hidden" name="idOfClient" value="${client.id}">
                                        <button type="submit" class="btn btn-info">Show books
                                        </button>
                                    </form>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    Client doesn't have any books
                                </td>
                            </c:otherwise>
                        </c:choose>
                        <td>
                            <form class="d-flex" action="${pageContext.request.contextPath}/admin/delete"
                                  method="post">
                                <input type="hidden" name="idToDelete" value="${client.id}">
                                <button type="submit" class="btn btn-danger">Delete
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>

<c:if test="${allBooks.size()>0}">
    <div class="container">
        <div class="row">
            <table class="table table-striped">
                <caption>All books</caption>
                <thead>
                <tr>
                    <th scope="col">№ of the line</th>
                    <th scope="col">Id of the book</th>
                    <th scope="col">Author of the book</th>
                    <th scope="col">Name of the book</th>
                    <th scope="col">Count of the books</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allBooks}" var="book" varStatus="loop">
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

<c:if test="${booksOfClient.size()>0}">
    <div class="container">
        <div class="row">
            <table class="table table-striped">
                <caption>All books of ${clientById.login}</caption>
                <thead>
                <tr>
                    <th scope="col">№ of the line</th>
                    <th scope="col">Author of the book</th>
                    <th scope="col">Name of the book</th>
                    <th scope="col">Return the book</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${booksOfClient}" var="book" varStatus="loop">
                    <tr>
                        <th scope="row"> ${loop.count}</th>
                        <td> ${book.author}</td>
                        <td> ${book.name}</td>
                        <td>
                            <form class="d-flex"
                                  action="${pageContext.request.contextPath}/admin/return_book_from_client"
                                  method="post">
                                <input type="hidden" name="idOfBook" value="${book.id}">
                                <input type="hidden" name="idOfClient" value="${clientById.id}">
                                <button type="submit" class="btn btn-info">Return the book
                                </button>
                            </form>
                        </td>
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
