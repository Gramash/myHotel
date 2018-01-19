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
    <title>jQuery UI Datepicker - Default functionality</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
<jsp:include page="../../_menu.jsp"/>

<h3>Available rooms</h3>

<p style="color: blue;" >${confirmMessage}</p>

<table>
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
                <td> <input name="id" type="hidden" value="${product.roomNo}"> ${product.roomNo}</td>
                <td>${product.sleeps}</td>
                <td>${product.price}</td>
                <td>
                    <img src="${product.image}" height="125" width="150">
                </td>
                <td align="center">
                    <script>
                        $(function () {
                            $(this).datepicker();
                            $(".datepicker").each(function (index) {
                            })
                        });
                    </script>
                    <input style="width:100px;" type="text" class="datepicker" name="checkIn">
                </td>
                <td align="center">
                    <script>
                        $(function () {
                            $(".datepicker").each(function (index) {
                                $(this).datepicker();
                            })
                        });
                    </script>
                    <input style="width:100px;" type="text" class="datepicker" name="checkOut">
                </td>
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>
