<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 08.09.16
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product to cart</title>
</head>
<body>

<p style="position: absolute; top: 0.1%; right: 8%;"><strong><a href="/info">My page ${userName}</a></strong></p>
<p><c:if test="${pageContext.request.userPrincipal.name != null}" > <p><jsp:include page="logout.jsp" /> </c:if></p>

    <form:form action="${pageContext.request.contextPath}/main/add/${productId}" modelAttribute="orderLines"  method="post">
        <table>
            <tr>
                <td>Product ID</td>
                <td>${product.productId}</td>
            </tr>
            <tr>
                <td>Brand</td>
                <td>${product.productBrand}</td>
            </tr>
            <tr>
                <td>Model</td>
                <td>${product.productModel}</td>
            </tr>
            <tr>
                <td>Reserved Stock</td>
                <td>${product.reservedStock}</td>
            </tr>
            <tr>
                <td>Stock</td>
                <td>${product.productStock}</td>
            </tr>
            <tr>
                <td>Added quantity</td>
                <td><form:input path="boughtQuantity"/></td>
                <td><form:errors path="boughtQuantity"/></td>
            </tr>

        </table>
        <input type="submit" value="Add to cart">
    </form:form>
</body>
</html>
