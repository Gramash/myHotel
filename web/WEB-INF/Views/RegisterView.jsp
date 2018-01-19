<%--
  Created by IntelliJ IDEA.
  User: reygo
  Date: 1/9/2018
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>

<jsp:include page="../../_menu.jsp"></jsp:include>

<h3>Login Page</h3>

<p style="color: red;">${errorMessage}</p>

<form method="POST" action="${pageContext.request.contextPath}/register">
    <table border="0">
        <tr>
            <td>User Login</td>
            <td><input type="text" name="userLogin" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/> </td>
        </tr>
        <tr>
            <td>User Name</td>
            <td><input type="text" name="userName" /> </td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>


</body>
</html>
