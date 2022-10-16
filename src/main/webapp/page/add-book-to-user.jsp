<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Add a book to user</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>

<jsp:include page="admin_header.jsp"/>

<div class="container">
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">№ of the line</th>
                <th scope="col">Id of the book</th>
                <th scope="col">Author of the book</th>
                <th scope="col">Title of the book</th>
                <th scope="col">Add the book</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allBooks}" var="book" varStatus="loop">
                <c:if test="${!userById.books.contains(book)}">
                    <tr>
                        <th scope="row"> ${loop.count}</th>
                        <td> ${book.id}</td>
                        <td> ${book.author}</td>
                        <td> ${book.title}</td>
                        <c:choose>
                            <c:when test="${book.available}">
                                <td>
                                    <form class="d-flex"
                                          action="${pageContext.request.contextPath}/admin/add_book_to_user"
                                          method="post">
                                        <input type="hidden" name="idOfBook" value="${book.id}">
                                        <input type="hidden" name="idOfUser" value="${userById.id}">
                                        <button type="submit" class="btn btn-info">Add the book
                                        </button>
                                    </form>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <button type="button" class="btn btn-secondary" disabled>This book is out of stock
                                    </button>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:if>
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
