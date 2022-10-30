<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of all books</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>

<jsp:include page="headers/header-for-admin.jsp"/>


<div class="container">
    <div class="row">
        <c:choose>
            <c:when test="${books.size()>0}">
                <table class="table table-striped">
                    <caption>All books in the library</caption>
                    <thead>
                    <tr>
                        <th scope="col">Line number</th>
                        <th scope="col">Book id</th>
                        <th scope="col">Book author</th>
                        <th scope="col">Title of the book</th>
                        <th scope="col">Year of publishing</th>
                        <th scope="col">Availability</th>
                        <th scope="col">Delete book</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${books}" var="book" varStatus="loop">
                        <tr>
                            <th scope="row"> ${loop.count}</th>
                            <td> ${book.id}</td>
                            <td><a href="${pageContext.request.contextPath}/authors/${book.author}"
                                   class="link-primary">${book.author}</a></td>
                            <td> ${book.title}</td>
                            <td> ${book.year}</td>
                            <c:choose>
                                <c:when test="${book.available}">
                                    <td> Available</td>
                                </c:when>
                                <c:otherwise>
                                    <td> The user with id ${book.user.id} has this book</td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${book.user==null}">
                                    <td>
                                        <form class="d-flex"
                                              action="${pageContext.request.contextPath}/admin/delete_book"
                                              method="post">
                                            <input type="hidden" name="bookId" value="${book.id}">
                                            <button type="submit" class="btn btn-danger">Delete
                                            </button>
                                        </form>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <button type="button" class="btn btn-secondary" disabled>You can't delete this
                                            book
                                        </button>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${totalPages>1}">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:forEach begin="0" end="${totalPages-1}" var="page">
                                <li class="page-item"><a class="page-link"
                                                         href="${pageContext.request.contextPath}/admin/show_all_books?page=${page}">${page+1}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </nav>
                </c:if>
            </c:when>
            <c:otherwise>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col"><H4>There aren't any books</H4></th>
                    </tr>
                    </thead>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>
</html>
