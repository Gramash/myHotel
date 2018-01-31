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

        button {
            alignment: center;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        button:hover {
            opacity: 1;
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
        <td>Class</td>
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
            <td>${order.clazz}</td>
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
        <td>class</td>
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
                <td><input name="class" type="hidden" value="${appl.clazz}">${appl.clazz}</td>
                <td><input name="checkIn" type="hidden" value="${appl.checkIn}">${appl.checkIn}</td>
                <td><input name="checkOut" type="hidden" value="${appl.checkOut}">${appl.checkOut}</td>
                <td>${appl.email}</td>
                <td>${appl.name}</td>
                <td align="center"><input type="submit" class="btn btn-success" value="Find"></td>
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
                <th>image</th>
                <th>Room #</th>
                <th>Sleeps</th>
                <th>Class</th>
                <th>Price</th>

                <c:if test="${appId==null}">
                    <th>isTaken</th>
                    <th>isAvailable</th>
                </c:if>
                <c:if test="${appId!=null}">
                    <th>send response</th>
                </c:if>
            </tr>
            <c:forEach items="${productList}" var="product" varStatus="loop">
                <form method="POST" action="${pageContext.request.contextPath}/toggle">
                    <tr>
                        <td>
                            <img src="${product.image}" height="125" width="150">
                        </td>
                        <input type="hidden" value="${appId}" name="appId"/>
                        <input type="hidden" value="${user_id}" name="userId"/>
                        <td><input style="  color: white" name="roomNo" type="hidden"
                                   value="${product.roomNo}"> ${product.roomNo}
                        </td>
                        <td>${product.sleeps}</td>
                        <td>${product.clazz}</td>
                        <td>${product.price}</td>
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
                            <td>
                                <c:if test="${!product.available}">
                                    <a style="color:red">out of order</a><br>
                                </c:if>
                            </td>
                            <td>
                                <button type="button" style="background-color: #27c4b1; color: mintcream"
                                        class="btn btn-info btn-lg"
                                        data-toggle="modal" data-target="#myModal${product.roomNo}">Update
                                </button>
                                <div class="modal fade" id="myModal${product.roomNo}" role="dialog">
                                    <div class="modal-dialog">
                                        <form method="POST" action="${pageContext.request.contextPath}/updateProduct">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title">UPDATE room # ${product.roomNo}</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <p style="color: black;">Please choose values to be updated</p>
                                                    <label>Sleeps</label>
                                                    <select name="sleeps" required>
                                                        <option value="1">1</option>
                                                        <option value="2">2</option>
                                                        <option value="3">3</option>
                                                    </select><br>
                                                    <select name="class" required>
                                                        <option value="A">A</option>
                                                        <option value="B">B</option>
                                                        <option value="C">C</option>
                                                    </select><br>
                                                    <label>Price</label>
                                                    <input type="text" name="price" value="${product.price}"
                                                           required><br>
                                                    <label>Availability</label>
                                                    <select name="available" required><br>
                                                        <option value="true">available</option>
                                                        <option value="false">out of order</option>
                                                    </select>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" formaction="updateProduct">Update</button>
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        Cancel
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </td>
                        </c:if>
                        <c:if test="${appId!=null}">
                            <td align="center">
                                <input type="submit" class="btn btn-warning" value="Submit"/>
                            </td>
                        </c:if>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </form>


</body>
</html>
