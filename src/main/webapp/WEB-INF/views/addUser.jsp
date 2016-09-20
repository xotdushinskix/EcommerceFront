<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: FromxSoul
  Date: 30.08.2016
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form:form action="${pageContext.request.contextPath}/registration" modelAttribute="user" method="post">
  <table>
    <tr>
      <td>First Name:</td>
      <td><form:input path="firstName"/></td>
      <td><form:errors path="firstName"/></td>
    </tr>

    <tr>
      <td>Second Name:</td>
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

    <tr>
      <td>Confirm password:</td>
      <td><input type="password" name="passwordC"/></td>
      <td>${password2Error}</td>
      <td>${passwordsDontEquals}</td>
    </tr>

    </table>

  <input type="submit" value="Registration" />
</form:form>
</body>
</html>
