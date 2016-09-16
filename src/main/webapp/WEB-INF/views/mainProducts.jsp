<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 01.09.16
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
    Greeting : ${greeting}
    This is a welcome page.<br>
    <a href="/admin">Admin page</a><br>
    <a href="/user">User Page</a><br></br>

    <h3>All products list:</h3>
    <table border="2">
        <thead>
        <tr>
            <th>Product Id</th>
            <th>Brand</th>
            <th>Model</th>
            <th>Stock</th>
            <th>Reserved Stock</th>
            <th>MPN</th>
            <%--<th>Add to cart</th>--%>
            <th>Add to cart</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allProducts}" var="product1">
            <tr>
                <td>${product1.productId}</td>
                <td>${product1.productBrand}</td>
                <td>${product1.productModel}</td>
                <td>${product1.productStock}</td>
                <td>${product1.reservedStock}</td>
                <td>${product1.productMPN}</td>
                <%--<td><a href="make_purchase?productId=<c:out value="${product1.productId}"/>">To cart</a></td>--%>
                <td><a href="/main/add/${product1.productId}">Add to cart</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h3 style="color: #ac2925">${moreThanInStock}</h3>

    <%--<br>--%>

    <%--<table border="2">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th>Firstname</th>--%>
            <%--<th>Lastname</th>--%>
            <%--<th>Login</th>--%>
            <%--<th>Edit</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<c:forEach items="${allUsers}" var="user1">--%>
            <%--<tr>--%>
                <%--<td>${user1.firstName}</td>--%>
                <%--<td>${user1.lastName}</td>--%>
                <%--<td>${user1.login}</td>--%>
                <%--<td><a href="/main/user/edit/${user1.id}">Edit</a></td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--</tbody>--%>
    <%--</table>--%>



    <p style="position: absolute; top: 0.5%; right: 3%;"><c:if test="${pageContext.request.userPrincipal.name == null}" > <a href="/login">Login</a> </c:if></p>
    <p><c:if test="${pageContext.request.userPrincipal.name != null}" > <p><jsp:include page="logout.jsp" /> </c:if></p>
    <%--<p style="position: absolute; top: 0.5%; right: 8%;"><c:if test="${pageContext.request.userPrincipal.name != null}" > ${pageContext.request.userPrincipal.name} </c:if></p>--%>
    <p style="position: absolute; top: 0.5%; right: 6%;"><c:if test="${pageContext.request.userPrincipal.name == null}" > <a href="/registration">Registration</a> </c:if></p>
    <p style="position: absolute; top: 0.1%; right: 8%;"><c:if test="${pageContext.request.userPrincipal.name != null}" > <strong>Welcome, <a href="/info">${userName}</a></strong></c:if></p>
    <p style="position: absolute; top: 4%; right: 3%;"><c:if test="${pageContext.request.userPrincipal.name != null}" > <strong><a href="/cart">Cart</a></strong></c:if></p>


</body>
</html>
