<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: reygo
  Date: 1/9/2018
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        Manager Task
    </title>
    <style>

        body {
            margin-left: 2%;
        }

        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 60%;
            margin-left: 10px;

        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }

        p {
            text-indent: 10px;
        }
    </style>
</head>

<body>

<jsp:include page="../../_menu.jsp"></jsp:include>

<h3> Greetings ${loginedUser.userName}!</h3>
<h4>Welcome to Your workplace. Have a nice day!</h4>

<p>This is a protected page!</p>

<table name="orders">
    <tr>
        <td>Room #</td>
        <td>Sleeps</td>
        <td>Check In</td>
        <td>Check Out</td>
        <td>Price</td>
        <td>User name</td>
        <td>User E-mail</td>
        <td>Paid</td>
    </tr>

    <c:forEach items="${orderList}" var="order">
        <tr>
            <td>${order.roomNo}</td>
            <td>${order.sleeps}</td>
            <td>${order.checkIn}</td>
            <td>${order.checkOut}</td>
            <td>${order.price}</td>
            <td>${order.user.userName}</td>
            <td>${order.user.email}</td>
            <td>${order.paid}</td>
        </tr>
    </c:forEach>
</table>
<br>
<hr>
<p>Pending Applications</p>

<table id="applications">
    <tr>
        <td>Application#</td>
        <td>sleeps</td>
        <td>Check In</td>
        <td>Check Out</td>
        <td>email</td>
        <td>name</td>
    </tr>
    <c:forEach items="${applList}" var="appl">
        <form method="POST" action="${pageContext.request.contextPath}/filterDB">
            <tr>
                <input name="userID" type="hidden" value="${appl.userId}">
                <td><input name="appId" type="hidden" value="${appl.applId}"> ${appl.applId}</td>
                <td><input name="sleeps" type="hidden" value="${appl.sleeps}">${appl.sleeps}</td>
                <td><input name="checkIn" type="hidden" value="${appl.checkIn}">${appl.checkIn}</td>
                <td><input name="checkOut" type="hidden" value="${appl.checkOut}">${appl.checkOut}</td>
                <td>${appl.email}</td>
                <td>${appl.name}</td>
                <td><input type="submit" value="Submit"></td>
            </tr>
        </form>
    </c:forEach>
</table>


<br>
<hr>
<form method="post" action="${pageContext.request.contextPath}/response">
    <p>Suitable products for Your query ${appId} for userId${user_id}</p>
    <table style="margin-left: 10px" bgcolor="#fffafa" id="queryResults">
        <tr>
            <th>Room #</th>
            <th>Sleeps</th>
            <th>Price</th>
            <th>image</th>
            <th>send response</th>
        </tr>
        <c:forEach items="${productList}" var="product" varStatus="loop">

            <tr>
                <input type="hidden" value="${appId}" name="appId"/>
                <input type="hidden" value="${user_id}" name="userId"/>
                <td><input style="  color: white" name="roomNo" type="hidden"
                           value="${product.roomNo}"> ${product.roomNo}
                </td>
                <td>${product.sleeps}</td>
                <td>${product.price}</td>
                <td>
                    <img src="${product.image}" height="125" width="150">
                </td>
                <td><input type="submit" value="Submit"/></td>
            </tr>

        </c:forEach>
    </table>
</form>


</body>
</html>