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
    <title>Manager Task</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 60%;
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
            text-indent: 50px;
            margin-left: 100px;
        }
    </style>
</head>

<body>

<jsp:include page="../../_menu.jsp"></jsp:include>

<h3 style="margin-left: 2%">Manager Task</h3>

<p style="margin-left: 2%"> Hello, This is a protected page!</p>

<table style="margin-left: 2%">
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
<br> <hr>
<p style="margin-left: 2%">Pending Applications</p>
<table style="margin-left: 2%">
    <tr>
        <td>Application#</td>
        <td>sleeps</td>
        <td>Check In</td>
        <td>Check Out</td>
        <td>email</td>
        <td>name</td>
    </tr>

    <c:forEach items="${applList}" var="appl">
        <tr>
            <td>${appl.applId}</td>
            <td>${appl.sleeps}</td>
            <td>${appl.checkIn}</td>
            <td>${appl.checkOut}</td>
            <td>${appl.email}</td>
            <td>${appl.name}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>