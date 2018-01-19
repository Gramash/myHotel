<%--
  Created by IntelliJ IDEA.
  User: reygo
  Date: 1/9/2018
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<a href="${pageContext.request.contextPath}/personalCabinet">
    Customer Task
</a>

<c:if test="${loginedUser!=null && loginedUser.accessLevel.equals(role)}">
||
    <a href="${pageContext.request.contextPath}/managerTask">
        Manager Task
    </a>
</c:if>
||
<a href="${pageContext.request.contextPath}/productView">
    Product View
</a>
||
<a href="${pageContext.request.contextPath}/userInfo">
    User Info
</a>
||
<a href="${pageContext.request.contextPath}/register">
    Register
</a>
||
<a href="${pageContext.request.contextPath}/login">
    Login
</a>
||
<a href="${pageContext.request.contextPath}/logout">
    Logout
</a>


&nbsp;
<span style="color:red">[ ${loginedUser.userLogin} ]</span>


