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
    </style>
</head>

<body>

<jsp:include page="../../_menu.jsp"></jsp:include>

<h3>Manager Task</h3>

Hello, This is a protected page!

<table>
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

</body>
</html>