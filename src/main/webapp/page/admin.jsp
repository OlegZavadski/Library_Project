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

<jsp:include page="admin_header.jsp"/>

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
                                      method="get">
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

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>
</html>
