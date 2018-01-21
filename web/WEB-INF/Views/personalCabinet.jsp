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
</body>
</html>