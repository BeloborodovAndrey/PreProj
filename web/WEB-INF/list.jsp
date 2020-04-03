<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: belob
  Date: 31.03.2020
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Список пользователей</title>
</head>
<body>
<table border="2">
    <tr>
        <td>ID</td>
        <td>Имя</td>
        <td>Пароль</td>
        <td>Действия</td>
    </tr>
    <c:forEach items="${users}" var = "user">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getName()}</td>
            <td>${user.getPassword()}</td>
            <td>
                <form action = "users/update" method="get">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="hidden" name="name" value="${user.getName()}">
                    <input type="hidden" name="password" value="${user.getPassword()}">
                    <input type="submit" value="Изменить" style="float:left">
                </form>
                <form action="users/delete" method="get">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="submit" value="Удалить" style="float:left">
                </form></td>
        </tr>
    </c:forEach>
</table>

<form action = "users/register" method="get">
    <input type="submit" value="Добавить пользователя">
</form>
</body>
</html>
