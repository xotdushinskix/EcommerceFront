<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 01.09.16
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
    <form:form action="${pageContext.request.contextPath}/main/add" modelAttribute="product" method="post">
        <form:input path="productBrand" /><br>
        <form:input path="productModel" /><br>
        <form:input path="productStock" /><br>
        <form:input path="productMPN" /><br>

        <input type="submit" value="Add" />
    </form:form>
</body>
</html>
