<%--
  Created by IntelliJ IDEA.
  User: belob
  Date: 31.03.2020
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить пользователя</title>
</head>
<body>
<form action = "users" method="post">
    <input required type="text" name="name" placeholder="Имя">
    <input required type="text" name="password" placeholder="Пароль">
    <input type="submit" value="OK">
</form>
</body>
</html>