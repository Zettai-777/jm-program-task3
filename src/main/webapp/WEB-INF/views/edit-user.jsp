<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <meta name="viewport" charset="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <script src='https://www.google.com/recaptcha/api.js'></script>

</head>
<body>
<div class="container mt-2 ml-2">
    <c:if test="${empty user.name}">
        <h4>Новый пользователь</h4>
    </c:if>
    <c:if test="${!empty user.name}">
        <h4>Редактировать пользователя</h4>
    </c:if>
    <form action="${pageContext.request.contextPath}/saveUser" method="post" >
        <c:if test="${!empty user.name}">
            <input type="hidden" name="id" id="id" value="${user.id}">
        </c:if>

        <div class="form-group">
            <label for="name"><strong>Имя</strong></label>
            <input type="text" class="form-control <c:if test="${!empty nameError}">is-invalid</c:if>"
                   value="<c:if test="${!empty user}">${user.name}</c:if>"
                   name="name" id="name" placeholder="Введите Ваше имя">
            <c:if test="${!empty nameError}">
                <div class="invalid-feedback">
                    ${nameError}
                </div>
            </c:if>
        </div>

        <div class="form-group">
            <label for="surName"><strong>Фамилия</strong></label>
            <input type="text" class="form-control <c:if test="${!empty surNameError}">is-invalid</c:if>"
                   value="<c:if test="${!empty user}">${user.surName}</c:if>"
                   name="surName" id="surName" placeholder="Введите Вашу фамилию">
            <c:if test="${!empty surNameError}">
                <div class="invalid-feedback">
                        ${surNameError}
                </div>
            </c:if>
        </div>

        <div class="form-group">
            <label for="age"><strong>Возраст</strong></label>
            <input type="text" class="form-control <c:if test="${!empty ageError}">is-invalid</c:if>"
                   value="<c:if test="${!empty user}">${user.age}</c:if>"
                   name="age" id="age" placeholder="Введите Ваш возраст">
            <c:if test="${!empty ageError}">
                <div class="invalid-feedback">
                        ${ageError}
                </div>
            </c:if>
        </div>

        <div class="form-group">
            <label for="email"><strong>Почта</strong></label>
            <input type="text" class="form-control <c:if test="${!empty emailError}">is-invalid</c:if>"
                   value="<c:if test="${!empty user}">${user.email}</c:if>"
                   name="email" id="email" placeholder="Введите Вашу почту">
            <c:if test="${!empty emailError}">
                <div class="invalid-feedback">
                        ${emailError}
                </div>
            </c:if>
        </div>
        <div>
            <c:if test="${empty user.name}">
                <button class="btn btn-primary mt-3" type="submit">Добавить нового пользователя</button>
            </c:if>
            <c:if test="${!empty user.name}">
                <button class="btn btn-primary mt-3" type="submit">Изменить пользователя</button>
            </c:if>
        </div>
    </form>
</div>
</body>
</html>
