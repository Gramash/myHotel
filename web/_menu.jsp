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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">iHotel</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/personalCabinet">Dashboard</a></li>
            <li><c:if test="${loginedUser!=null && loginedUser.accessLevel.equals(role)}">
                <a href="${pageContext.request.contextPath}/managerTask">Manager Task</a>
            </c:if></li>
            <li><a href="${pageContext.request.contextPath}/productView">Offers</a></li>
            <li><a href="${pageContext.request.contextPath}/userInfo">My Account</a></li>
            <li>
                <c:if test="${loginedUser==null}">
                    <a href="${pageContext.request.contextPath}/register">Join</a>
                </c:if>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a>[ ${loginedUser.userLogin} ]</a>
            <li>
                <c:if test="${loginedUser==null}">
                    <a href="${pageContext.request.contextPath}/login"> <span
                            class="glyphicon glyphicon-log-in"></span> Sign In</a>
                </c:if>
            </li>
            <li>
                <c:if test="${loginedUser!=null}">
                    <a href="${pageContext.request.contextPath}/logout"><span
                            class="glyphicon glyphicon-user"></span> Sign Out</a>
                </c:if>
            </li>
            </li>

        </ul>


    </div>
</nav>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    function changeclass(element) {
        $('.showhide').click(function () {
            $(this).removeClass('myclass');
            $(this).addClass('active');
        });
    }

</script>
</body>
</html>


