<%--
  Created by IntelliJ IDEA.
  User: reygo
  Date: 1/13/2018
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Products</title>
    <style>
        @import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
        body {
            background: -webkit-linear-gradient(left, #25c481, #25b7c4);
            background: linear-gradient(to right, #25c481, #25b7c4);
            font-family: 'Roboto', sans-serif;
        }

        table{

        }
    </style>

</head>
<body>
<jsp:include page="../../_menu.jsp"/>


<h3>Available rooms</h3>

<p style="color: blue;" >${confirmMessage}</p>


<table bgcolor="">
    <tr>
        <th>Room #</th>
        <th>Sleeps</th>
        <th>Price</th>
        <th>image</th>
        <th>Check-In</th>
        <th>Check-Out</th>
        <th>make order</th>
    </tr>
    <c:forEach items="${productList}" var="product" varStatus="loop">
        <form method="post" action="${pageContext.request.contextPath}/productView">
            <tr>
                <td> <input style="color: white" name="id" type="hidden" value="${product.roomNo}"> ${product.roomNo}</td>
                <td>${product.sleeps}</td>
                <td>${product.price}</td>
                <td>
                    <img src="${product.image}" height="125" width="150">
                </td>
                <td align="center">
                    <input type="date" name ="checkIn" >
                </td>
                <td align="center">
                    <input type ="date" name="checkOut" >
                </td>
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </form>
    </c:forEach>
</table>

</body>
</html>
