<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 06.09.16
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user password</title>
</head>
<body>
    <%--<form action="/info/edit/password/${userId.id}" method="post">--%>
        <%--Your current password: <input type="text" name="currentPassword" />--%>
        <%--New password: <input type="text" name="newPSWRD" />--%>
        <%--Confirm new password: <input type="text" name="cnfrmPSWRD" />--%>
        <%--<input type="submit" value="Edit pass">--%>
    <%--</form>--%>
<form:form action="${pageContext.request.contextPath}/info/edit/password/${userId.id}" commandName="user" method="post">
    Your current password: <input type="text" name="currentPassword" />
                                    ${message1} <br>
    New password: <input type="text" name="newPSWRD" /> <br>
    Confirm new password: <input type="text" name="cnfrmPSWRD" />
    ${message2} <br>
    <input type="submit" value="Edit pass">
</form:form>
</body>
</html>
