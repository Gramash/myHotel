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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>
        Manager Task
    </title>
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

        h3, h4 {
            color: white;
            margin-left: 10%;

        }

        p {
            color: white;
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

<h3 style="color:mintcream;"> Greetings ${loginedUser.userName}!</h3>
<h4 style="color:mintcream;">Welcome to Your workplace. Have a nice day!</h4>


<table name="orders" style=" background-color: mintcream ">
    <tr>
        <td>Room #</td>
        <td>Sleeps</td>
        <td>Check In</td>
        <td>Check Out</td>
        <td>Price</td>
        <td>User name</td>
        <td>User E-mail</td>
        <td>Payment due date</td>
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
            <td>
                <c:if test="${order.paid==null}">Paid-Up</c:if>
                <c:if test="${order.paid!=null}">${order.paid}</c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<hr>
<p style="margin-left: 10%; color: mintcream;">Pending Applications</p>

<table id="applications" style=" background-color: mintcream ">
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
<c:if test="${appId!=null}">
<form method="post" action="${pageContext.request.contextPath}/response">
    <p style="margin-left: 10%; color:mintcream;">Suitable products for Your query ${appId}</p>
    </c:if>
    <c:if test="${appId==null}">
    <form method="post" action="${pageContext.request.contextPath}/managerTask">
        <p style="margin-left: 10%; color:mintcream;">All rooms </p>
        </c:if>
        <table id="queryResults" style=" background-color: mintcream ">
            <tr>
                <th>Room #</th>
                <th>Sleeps</th>
                <th>Price</th>
                <th>image</th>
                <c:if test="${appId==null}">
                    <th>isTaken</th>
                </c:if>
                <c:if test="${appId!=null}">
                    <th>send response</th>
                </c:if>

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
                    <c:if test="${appId==null}">
                        <td>
                            <c:if test="${!product.taken}">
                                free
                            </c:if>
                            <c:if test="${product.taken}">
                                <input type="date" name="checkIn" value="${checkIn}"/> <br>
                                <input type="date" name="checkOut" value="${checkOut}"/> <br>
                                <input type="submit" value="Check dates"/>
                                <p style="color: black;">${datesCheck}</p>

                            </c:if>
                        </td>
                    </c:if>
                    <c:if test="${appId!=null}">
                        <td>
                            <input type="submit" value="Submit"/>
                        </td>
                    </c:if>
                </tr>

            </c:forEach>
        </table>
    </form>

</body>
</html>