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

<jsp:include page="header-for-admin.jsp"/>

<div class="container">
    <div class="row">
        <table class="table table-striped">
            <caption>All books in the library</caption>
            <thead>
            <tr>
                <th scope="col">Line number</th>
                <th scope="col">Id of a book</th>
                <th scope="col">Author of a book</th>
                <th scope="col">Title of a book</th>
                <th scope="col">Year of publishing</th>
                <th scope="col">Availability</th>
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
