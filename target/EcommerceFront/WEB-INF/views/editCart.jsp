<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 13.09.16
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Cart</title>
</head>
<body>
    <form:form action="${pageContext.request.contextPath}/cart/edit/{orderLineId}" commandName="orderLine" method="post">
        <table border="2">
            <tr>
                <td>Orderline ID</td>
                <td><form:input path="orderLineId" readonly="true"/></td>
            </tr>
            <%--<tr>--%>
                <%--<td>Product ID</td>--%>
                <%--&lt;%&ndash;<td>${orderLine.product.productId}</td>&ndash;%&gt;--%>
                <%--<td><form:input path="orderLineId" readonly="true"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>Brand</td>--%>
                <%--<td><form:input path="orderLine.product.productBrand" readonly="true"/> </td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>Model</td>--%>
                <%--<td>${orderLine.product.productModel}</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>MPN</td>--%>
                <%--<td>${orderLine.product.productMPN}</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>Stock</td>--%>
                <%--<td>${orderLine.product.productStock}</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>Bought quantity</td>--%>
                <%--<td>${orderLine.boughtQuantity}</td>--%>
            <%--</tr>--%>
            <tr>
                <td>New quantity</td>
                <td><input type="text" name="newPurchQuantity" /></td>
                ${moreThanStock}
            </tr>
        </table>
        <input type="submit" value="Edit">
    </form:form>
</body>
</html>
