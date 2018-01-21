<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: reygo
  Date: 1/9/2018
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Personal Cabinet</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 40%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>

<body>

<jsp:include page="../../_menu.jsp"></jsp:include>

<h3>Hello: ${loginedUser.userName} </h3>

This is a protected page <br>

<p style="color: black;">${message}</p>
<p style="color: #2aabd2">${noOrders}</p>

<table border="1">
    <tr>
        <td>roomNo</td>
        <td>Sleeps</td>
        <td>checkIn</td>
        <td>checkOut</td>
        <td>Price</td>
    </tr>
    <c:forEach items="${orderList}" var="order">
        <tr>
            <td>${order.roomNo}</td>
            <td>${order.sleeps}</td>
            <td>${order.checkIn}</td>
            <td>${order.checkOut}</td>
            <td>${order.price}</td>
        </tr>
    </c:forEach>
</table>
<br><hr>
<p> Your Applications</p>
<p>${applMessage}</p>
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