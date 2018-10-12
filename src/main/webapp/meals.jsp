<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 08.10.18
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--jsp:useBean id="users" class="ru.javawebinar.topjava.util.MealsUtil" scope="page" -->

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="me" value="${mealss}" />
<table>
    <thead>
        <tr><th>Время</th><th>Описание</th><th>Калории</th></tr>
    </thead>
    <tbody>
    <c:forEach var = "list" items = "${me}">
        <tr style="background-color:${ (list.exceed == true ? 'greenyellow' : 'red')}">
            <td>${list.dateTime}</td>
            <td>${list.description}</td>
            <td>${list.calories}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
