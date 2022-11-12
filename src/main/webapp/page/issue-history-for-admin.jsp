<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Issue history</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>

<jsp:include page="headers/header-for-admin.jsp"/>

<div class="container">
    <div class="row">
        <c:choose>
            <c:when test="${books_audit.size()>0}">
                <table class="table table-striped">
                    <caption>Issue history</caption>
                    <thead>
                    <tr>
                        <th scope="col">Line number</th>
                        <th scope="col">User id</th>
                        <th scope="col">Book author</th>
                        <th scope="col">Title of the book</th>
                        <th scope="col">Year of publishing</th>
                        <th scope="col">Date of issue</th>
                        <th scope="col">Return date</th>
                        <th scope="col">Days of use</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${books_audit}" var="bookAudit" varStatus="loop">
                        <tr>
                            <th scope="row"> ${loop.count}</th>
                            <td> ${bookAudit.userId}</td>
                            <td> ${bookAudit.author}</td>
                            <td> ${bookAudit.title}</td>
                            <td> ${bookAudit.year}</td>
                            <td><fmt:formatDate value="${bookAudit.dateOfIssue}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${bookAudit.returnDate}" pattern="yyyy-MM-dd"/></td>
                            <td> ${bookAudit.daysOfUse}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${totalPages>1}">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:forEach begin="0" end="${totalPages-1}" var="page">
                                <li class="page-item"><a class="page-link"
                                                         href="${pageContext.request.contextPath}/admin/show_issue_history?page=${page}">${page+1}</a>
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
                        <th scope="col"><H4>No issue history</H4></th>
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
