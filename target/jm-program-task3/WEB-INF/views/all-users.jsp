<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>USERS</title>

    <meta name="viewport" charset="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <script src='https://www.google.com/recaptcha/api.js'></script>

</head>

<body>

<div class="mt-2 ml-4">
    <h4>Пользователи</h4>
</div>
<div class="container mt-2 ml-2">
    <table class="table table-sm table-bordered" >
        <thead class="thead-dark" >
        <tr>
            <th scope="col">ИМЯ</th>
            <th scope="col">ФАМИЛИЯ</th>
            <th scope="col">ВОЗРАСТ</th>
            <th scope="col">ПОЧТА</th>
            <th scope="col">ДЕЙСТВИЯ</th>
        </tr>
        </thead>

        <c:forEach var="user" items="${allUsers}">

            <c:url var="updateButton" value="/updateUserInfo/${user.id}">
                <c:param name="userId" value="${user.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteUserById">
                <c:param name="userId" value="${user.id}"/>
            </c:url>
            <tbody>
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
            </tbody>
        </c:forEach>
    </table>
</div>
<button class="btn btn-primary ml-4" type="submit" onclick="window.location.href='${pageContext.request.contextPath}/addUser'">Добавить пользователя</button>


</body>
</html>
