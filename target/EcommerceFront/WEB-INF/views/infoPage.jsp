<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 05.09.16
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info</title>
</head>
<body>

    <table>
        <tr>
            <td>First name:</td>
            <td>${fName}</td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td>${lName}</td>
        </tr>
        <tr>
            <td>Login:</td>
            <td>${login}</td>
        </tr>
    </table>
    <br>
    <a href="/info/edit/${userId.id}">Edit your info</a> <br>
    <a href="/info/edit/password">Edit your password</a>

</body>
</html>
