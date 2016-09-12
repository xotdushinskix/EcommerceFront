<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <form:form action="${pageContext.request.contextPath}/info/edit/password" commandName="user" method="post">
        Your current password: <input type="text" name="currentPassword" />
                                                <%--<br />--%>
                                                <%--<c:if test="${noCurrentPassword}">--%>
                                                    <%--<small><p color="red">--%>
                                                        <%--Note: you must enter a password--%>
                                                    <%--</p></small>--%>
                                                <%--</c:if>--%>
        New password: <input type="text" name="newPSWRD" />
                                        ${emptyNewPass}<br>
        Confirm new password: <input type="text" name="cnfrmPSWRD" />
                                    ${emptyConfirmPass}  ${message2} <br>
        <input type="submit" value="Edit pass">
    </form:form>
    ${allEmpty}
</body>
</html>


<%--<P>--%>
    <%--Enter your name:--%>
    <%--<input type="text" name="name"--%>
           <%--value="<c:out value="${param.name}"/>" />--%>
    <%--<br />--%>
    <%--<c:if test="${noName}">--%>
        <%--<small><font color="red">--%>
            <%--Note: you must enter a name--%>
        <%--</font></small>--%>
    <%--</c:if>--%>
<%--</p>--%>