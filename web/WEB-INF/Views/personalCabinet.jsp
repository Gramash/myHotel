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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Dashboard</title>
    <style>
        @import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);

        body {

            background: -webkit-linear-gradient(left, #25c481, #25b7c4);
            background: linear-gradient(to right, #25c481, #25b7c4);
            font-family: 'Roboto', sans-serif;
        }

        table {
            margin-left: 10%;
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 60%;

        }

        h3 {
            color: white;
            text-align: center;
            margin-left: 10%;
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


<p style="color: mintcream; margin-left: 10%;">${message}</p>
<p style="color: #2aabd2; margin-left: 10%;">${noOrders}</p>

<table style="background-color: mintcream">
    <tr>
        <td>roomNo</td>
        <td>Sleeps</td>
        <td>checkIn</td>
        <td>checkOut</td>
        <td>Total Price</td>
        <td>Payment due date</td>
    </tr>
    <c:forEach items="${orderList}" var="order">
        <form method="post" action="${pageContext.request.contextPath}/personalCabinet">
            <tr>
                <td>
                    <input name="prodId" value="${order.roomNo}" type="hidden">
                        ${order.roomNo}
                </td>
                <td>${order.sleeps}</td>
                <td>
                    <input name="checkIn" value="${order.checkIn}" type="hidden">
                        ${order.checkIn}
                </td>
                <td>
                    <input name="checkOut" value="${order.checkOut}" type="hidden">
                        ${order.checkOut}
                </td>
                <td>${order.price}</td>
                <td>
                    <c:if test="${order.paid==null}">paid-up</c:if>
                    <c:if test="${order.paid!=null}">${order.paid}</c:if>
                </td>
                <td>

                    <input type="submit" value="Confirm">
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<br>
<hr>
<p style="margin-left: 10%; color:mintcream;"> Your Applications</p>
<p style="margin-left: 10%; color:mintcream;">${applMessage}</p>
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
<br> <hr>
<p style="margin-left: 10%; color:mintcream;">Offers for Your applications </p>
<p style="margin-left: 10%; color:mintcream;">${offerMessage}</p>
<table style="background-color: mintcream">
    <tr>
        <td>Application#</td>
        <td>room #</td>
        <td>Sleeps</td>
        <td>Check In</td>
        <td>Check Out</td>
        <td>$/day</td>
        <td></td>
        <td>Book It!</td>
    </tr>

    <c:forEach items="${offerList}" var="offer">
        <form method="post" action="${pageContext.request.contextPath}/productView">
            <tr>
                <td>
                    <input name="appId" type="hidden" value="${offer.applicationId}">
                        ${offer.applicationId}
                </td>
                <td>
                    <input name="id" type="hidden" value="${offer.roomNo}"/>
                        ${offer.roomNo}
                </td>
                <td>${offer.sleeps}</td>
                <td>
                    <input name="checkIn" type="hidden" value="${offer.checkIn}"/>
                        ${offer.checkIn}
                </td>
                <td>
                    <input name="checkOut" type="hidden" value="${offer.checkOut}"/>
                        ${offer.checkOut}
                </td>
                <td>${offer.price}</td>
                <td>
                    <img src="${offer.image}" height="125" width="150">
                </td>
                <td>
                    <input type="submit" value="Book It!">
                </td>
            </tr>
        </form>
    </c:forEach>
</table>


</body>
</html>