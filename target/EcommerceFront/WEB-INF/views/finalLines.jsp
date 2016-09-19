<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 19.09.16
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Final order lines</title>
</head>
<body>
    <table border="2">
        <thead>
        <tr>
            <th>Product Id</th>
            <th>Brand</th>
            <th>Model</th>
            <th>MPN</th>>
            <th>Bought Quantity</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${historyLines.orderLines}" var="hisLin">
            <tr>
                <td>${hisLin.product.productId}</td>
                <td>${hisLin.product.productBrand}</td>
                <td>${hisLin.product.productModel}</td>
                <td>${hisLin.product.productMPN}</td>
                <td>${hisLin.boughtQuantity}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>
