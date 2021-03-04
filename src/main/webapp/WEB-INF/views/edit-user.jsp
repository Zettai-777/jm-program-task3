<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <c:if test="${empty user.name}">
        <title>Добавить нового пользователя</title>
    </c:if>
    <c:if test="${!empty user.name}">
        <title>Изменить информацию о пользователе</title>
    </c:if>
</head>

<body>

<form action="${pageContext.request.contextPath}/saveUser" method="post">
    <c:if test="${!empty user.name}">
        <input type="hidden" name="id" id="id" value="${user.id}">
    </c:if>
    <label for="name">Имя</label>
    <input type="text" name="name" id="name">
    <label for="surName">Фамилия</label>
    <input type="text" name="surName" id="surName">
    <label for="age">Возраст</label>
    <input type="text" name="age" id="age">
    <label for="email">Почта</label>
    <input type="text" name="email" id="email">
    <c:if test="${empty user.name}">
        <input type="submit" value="Добавить нового пользователя">
    </c:if>
    <c:if test="${!empty user.name}">
        <input type="submit" value="Изменить пользователя">
    </c:if>

</form>


</body>
</html>
