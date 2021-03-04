<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>USERS</title>
</head>
<body>

<h3>Пользователи</h3>
<table>
    <tr>
        <th>ИМЯ</th>
        <th>ФАМИЛИЯ</th>
        <th>ВОЗРАСТ</th>
        <th>ПОЧТА</th>
        <th>ДЕЙСТВИЯ</th>
    </tr>

    <c:forEach var="user" items="${allUsers}">

        <c:url var="updateButton" value="/updateUserInfo/${user.id}">
            <c:param name="userId" value="${user.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/deleteUserById">
            <c:param name="userId" value="${user.id}"/>
        </c:url>

        <tr>
            <td>${user.name}</td>
            <td>${user.surName}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>
                <input type="button" value="Обновить" onclick="window.location.href='${updateButton}'">
                <input type="button" value="Удалить" onclick="window.location.href='${deleteButton}'">
            </td>
        </tr>
    </c:forEach>

</table>

<a href="${pageContext.request.contextPath}/addUser">Добавить пользователя</a>


</body>
</html>
