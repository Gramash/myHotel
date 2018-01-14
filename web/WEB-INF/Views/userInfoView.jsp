<%--
  Created by IntelliJ IDEA.
  User: reygo
  Date: 1/9/2018
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<jsp:include page="../../_menu.jsp"></jsp:include>

<h3>Hello: ${loginedUser.userName}</h3>

User Login: <b>${loginedUser.userLogin}</b>
<br>
Access Level: <b> ${loginedUser.accessLevel}</b>
<br>

</body>
</html>