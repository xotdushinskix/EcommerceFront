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
        <table>
            <tr>
                <td>Product brand:</td>
                <td><form:input path="productBrand" /></td>
                <td><form:errors path="productBrand" /></td>
            </tr>
            <tr>
                <td><form:input path="productModel" /></td>
                <td><form:errors path="productModel" /></td>
            </tr>
            <tr>
                <td><form:input path="productStock" /></td>
                <td><form:errors path="productStock" /></td>
            </tr>
            <tr>
                <td><form:input path="productMPN" /></td>
                <td><form:errors path="productMPN" /></td>
            </tr>
        </table>
        <br>
        <input type="submit" value="Add" />
    </form:form>
</body>
</html>
