<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 08.09.16
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>

<p style="position: absolute; top: 0.1%; right: 8%;"><strong><a href="/info">My page ${userName}</a></strong></p>
<p><c:if test="${pageContext.request.userPrincipal.name != null}" > <p><jsp:include page="logout.jsp" /> </c:if></p>

    <table border="2">
        <thead>
        <tr>
            <th>Order line Id</th>
            <th>Product id</th>
            <th>Brand</th>
            <th>Model</th>
            <th>Stock</th>
            <th>MPN</th>
            <th>Bought quantity</th>
            <th>Edit line</th>
            <%--<th>Add to cart</th>--%>
            <%--<th>Add to cart</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allLines}" var="line">
            <tr>
                <td>${line.orderLineId}</td>
                <td>${line.product.productId}</td>
                <td>${line.product.productBrand}</td>
                <td>${line.product.productModel}</td>
                <td>${line.product.productStock}</td>
                <td>${line.product.productMPN}</td>
                <td>${line.boughtQuantity}</td>
                <td><a href="/cart/edit/${line.orderLineId}">Edit</a></td>
                    <%--<td><a href="make_purchase?productId=<c:out value="${product1.productId}"/>">To cart</a></td>--%>
                <%--<td><a href="/main/add/${product1.productId}">Add to cart</a></td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h3 style="color: #ac2925">${moreThanStock}</h3>
    <h3 style="color: #ac2925">${nullValue}</h3>



</body>
</html>
