<%--
  Created by IntelliJ IDEA.
  User: belob
  Date: 31.03.2020
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
Удалить пользователя -
<tr>
    <td>ID</td>
    <td>${param.id} ?</td>
</tr>
<form method="post">
    <input type="hidden" name="id" value="${param.id}">
    <input type="submit" value="Удалить">
</form>

</body>
</html>
