<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Overdue books</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>

<jsp:include page="header-for-admin.jsp"/>

<c:choose>
    <c:when test="${books.size()>0}">
        <div class="container">
            <div class="row">
                <table class="table table-striped">
                    <caption>Overdue books</caption>
                    <thead>
                    <tr>
                        <th scope="col">Line number</th>
                        <th scope="col">Book id</th>
                        <th scope="col">Book author</th>
                        <th scope="col">Title of the book</th>
                        <th scope="col">Year of publishing</th>
                        <th scope="col">Date of issue</th>
                        <th scope="col">Id of user</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${books}" var="book" varStatus="loop">
                        <tr>
                            <th scope="row"> ${loop.count}</th>
                            <td> ${book.id}</td>
                            <td> ${book.author}</td>
                            <td> ${book.title}</td>
                            <td> ${book.year}</td>
                            <td><fmt:formatDate value="${book.dateOfIssue}" pattern="yyyy-MM-dd"/></td>
                            <td> ${book.user.id}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container">
            <div class="row">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col"><H4>There aren't any overdue books</H4></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>
</html>
