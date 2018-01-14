<%--
  Created by IntelliJ IDEA.
  User: reygo
  Date: 1/13/2018
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ProductView</title>
    <style>
        table {
            border-spacing: 0;
            width: 50%;
            border: 1px solid #ddd;
        }
        th {
            cursor: pointer;
        }
        th, td {
            text-align: left;
            padding: 16px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2
        }
    </style>
</head>
<body>
<jsp:include page="../../_menu.jsp"/>

<h3>Available rooms</h3>

<table id="productsTable">
<tr>
    <th>Room #</th>
    <th>Sleeps</th>
    <th>Price</th>
    <th>image</th>
</tr>
<c:forEach items="${productList}" var="product">
    <tr>
        <td>${product.roomNo}</td>
        <td>${product.sleeps}</td>
        <td>${product.price}</td>
        <td>
            <img src="${product.image}" height="125" width="150">
        </td>
    </tr>
</c:forEach>
</table>
</body>
</html>
