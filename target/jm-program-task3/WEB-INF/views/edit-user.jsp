<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <c:if test="${empty user.name}">
        <title>Добавить нового пользователя</title>
    </c:if>
    <c:if test="${!empty user.name}">
        <title>Изменит информацию пользователя</title>
    </c:if>
</head>

<body>
<c:if test="${empty user.name}">
    <c:url value="/users/addUser" var="var"/>
</c:if>

<c:if test="${!empty user.name}">
    <c:url value="/users/updateUserInfo" var="var"/>
</c:if>

<form action="${var}" method="post">
    <c:if test="${!empty user.name}">
        <input type="hidden" name="id" value="${user.id}">
    </c:if>
    <label for="name">Имя</label>
    <input type="text" name="name" id="name">
    <label for="surName">Фамилия</label>
    <input type="text" name="surName" id="surName">
    <label for="age">Возраст</label>
    <input type="text" name="age" id="age">
    <label for="email">Почта</label>
    <input type="text" name="email" id="email">
    <input type="submit" value="Добавить пользователя">
</form>


</body>
</html>
