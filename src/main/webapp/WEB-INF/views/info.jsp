<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <form:form action="${pageContext.request.contextPath}/info/${login}" commandName="user" method="post">
        <table>
            <tr>
                <td>First name:</td>
                <td><form:input path="firstName"/></td>
                <td><form:errors path="firstName"/></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><form:input path="lastName"/></td>
                <td><form:errors path="lastName"/></td>
            </tr>
            <tr>
                <td>Login:</td>
                <td><form:input path="login"/></td>
                <td><form:errors path="login"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:password path="password"/></td>
                <td><form:errors path="password"/></td>
            </tr>
        </table>
        <input type="submit" value="Edit">
    </form:form>
</body>
</html>
