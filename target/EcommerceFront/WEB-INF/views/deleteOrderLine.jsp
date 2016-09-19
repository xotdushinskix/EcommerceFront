<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 19.09.16
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Order Line</title>
</head>
<body>
    <table>
        <tr>
            <td>Product ID</td>
            <td>${orderLine.product.productId}</td>
        </tr>
        <tr>
            <td>Brand</td>
            <td>${orderLine.product.productBrand}</td>
        </tr>
        <tr>
            <td>Model</td>
            <td>${orderLine.product.productModel}</td>
        </tr>
        <tr>
            <td>MPN</td>
            <td>${orderLine.product.productMPN}</td>
        </tr>
        <tr>
            <td>Bought Quantity</td>
            <td>${orderLine.boughtQuantity}</td>
        </tr>
    </table>

    <form:form action="${pageContext.request.contextPath}/cart/delete/${orderLine.orderLineId}" commandName="orderLine" method="post">
        <input type="submit" value="Delete Line">
    </form:form>

</body>
</html>
