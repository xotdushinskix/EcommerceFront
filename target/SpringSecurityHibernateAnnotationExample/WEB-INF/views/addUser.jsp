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

<form:form action="${pageContext.request.contextPath}/admin/add" modelAttribute="user" method="post">
  <form:input path="firstName" /><br>
  <form:input path="lastName" /><br>
  <form:input path="login" /><br>
  <form:input path="password" /><br>

  <input type="submit" value="Add" />
</form:form>
</body>
</html>
