<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <form:form action="${pageContext.request.contextPath}/main/add/${product.productId}" modelAttribute="productForSale" method="post">
        <table>
            <tr>
                <td>Brand:</td>
                <td>${productForSale.productBrand}</td>
            </tr>
            <tr>
                <td>Model:</td>
                <td>${productForSale.productModel}</td>
            </tr>
            <tr>
                <td>MPN:</td>
                <td>${productForSale.productMPN}</td>
            </tr>
            <tr>
                <td>Stock:</td>
                <td>${productForSale.productStock}</td>
            </tr>
        </table>
        <input type="number" name="purchQuantity" />
        <input type="submit" value="Add to cart">
    </form:form>
</body>
</html>
