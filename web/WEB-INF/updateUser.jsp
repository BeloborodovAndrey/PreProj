<%--
  Created by IntelliJ IDEA.
  User: belob
  Date: 31.03.2020
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

Редактировать пользователя

<form action="/users/update" method="post">
    <input type="hidden" name = "id" value="${param.id}">
    Имя пользователя:<input type="text" name="name" value="${param.name}">
    Пароль:<input type="text" name="password" value="${param.password}">
    <input type="submit" value="Обновить">
</form>

</body>
</html>
