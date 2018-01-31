<%--
  Created by IntelliJ IDEA.
  User: reygo
  Date: 1/16/2018
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<table border="1">
    <tr>
            <% for (int i = 0; i < 10; i++) {%>
    <tr>
        <td>
            <% out.print(i == 0 ? "" : i);%>
        </td>

            <% for (int j = 1; j < 10; j++) {%>
        <td>

            <% int a = i == 0 ? (j) : (i * j);%>
            <% out.print(a);%>
        </td>
            <% }%>
    <tr>
        <% }%>
    </tr>
</table>
</body>
</html>
