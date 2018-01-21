<%--
  Created by IntelliJ IDEA.
  User: reygo
  Date: 1/9/2018
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


</head>

<body>


<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">WebSiteName</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="${pageContext.request.contextPath}/personalCabinet">Personal Cabinet</a></li>
            <li><c:if test="${loginedUser!=null && loginedUser.accessLevel.equals(role)}">
                <a href="${pageContext.request.contextPath}/managerTask">Manager Task</a>
            </c:if></li>
            <li><a href="${pageContext.request.contextPath}/productView">Product View</a></li>
            <li><a href="${pageContext.request.contextPath}/userInfo">User Info</a></li>
            <li><a href="${pageContext.request.contextPath}/register">Register</a></li>
            <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
            <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            <li><a>[ ${loginedUser.userLogin} ]</a></li>
        </ul>
    </div>
</nav>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>


