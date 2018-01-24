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
    <title>Dashboard</title>
    <style>
        @import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);

        body {
            background: -webkit-linear-gradient(left, #25c481, #25b7c4);
            background: linear-gradient(to right, #25c481, #25b7c4);
            font-family: 'Roboto', sans-serif;
        }

        table {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;

            width: 60%;

        }

        h3 {
            color: white;
            text-align: center;
        }

        table td, table th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        table tr:hover {
            background-color: #ddd;
        }

        table th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            color: #5b5b5b;

        }
    </style>
</head>

<body>

<jsp:include page="../../_menu.jsp"></jsp:include>

<h3 style="color: mintcream">Hello: ${loginedUser.userName} </h3>

This is a protected page <br>

<p style="color: black;">${message}</p>
<p style="color: #2aabd2">${noOrders}</p>

<table style="background-color: mintcream">
    <tr>
        <td>roomNo</td>
        <td>Sleeps</td>
        <td>checkIn</td>
        <td>checkOut</td>
        <td>Total Price</td>
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
<br>
<hr>
<p> Your Applications</p>
<p>${applMessage}</p>
<table style="background-color: mintcream">
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
<p>Offers for Your applications </p>
<p >${offerMessage}</p>
<table style="background-color: mintcream">
    <tr>
        <td>Application#</td>
        <td>room #</td>
        <td>Sleeps</td>
        <td>Check In</td>
        <td>Check Out</td>
        <td>$/day</td>
        <td></td>
    </tr>

    <c:forEach items="${offerList}" var="offer" >
        <tr>
            <td>${offer.applicationId}</td>
            <td>${offer.roomNo}</td>
            <td>${offer.sleeps}</td>
            <td>${offer.checkIn}</td>
            <td>${offer.checkOut}</td>
            <td>${offer.price}</td>
            <td>
                <img src="${offer.image}" height="125" width="150">
            </td>
        </tr>
    </c:forEach>
</table>


</body>
</html>