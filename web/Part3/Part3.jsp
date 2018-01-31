<%--
  Created by IntelliJ IDEA.
  User: reygo
  Date: 1/16/2018
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/part3">
    <table border="0">
        <tr>
            <td>User Login</td>
            <td><input type="text" name="userLogin" value="${user}"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
        <tr>
            <td>User List</td>
            <td>
                <c:forEach items="${list}" var="user">
                    ${user},
                </c:forEach>
            </td>
            <td>
                <a href action="${sessionScope.list.clear()}">remove userList</a>
            </td>
        </tr>

    </table>

</form>
</body>
</html>