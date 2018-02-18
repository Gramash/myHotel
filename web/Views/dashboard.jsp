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
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 70%;

        }

        h3, h4 {
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

        * {
            box-sizing: border-box
        }

        /* Style the tab */
        .tab {
            float: left;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
            width: 15%;
            height: 700px;
        }

        /* Style the buttons inside the tab */
        .tab button {
            display: block;
            background-color: inherit;
            color: black;
            padding: 22px 16px;
            width: 100%;
            border: none;
            outline: none;
            text-align: left;
            cursor: pointer;
            transition: 0.3s;
            font-size: 17px;
        }

        /* Change background color of buttons on hover */
        .tab button:hover {
            background-color: #ddd;
        }

        /* Create an active/current "tab button" class */
        .tab button.active {
            background-color: #ccc;
        }

        /* Style the tab content */
        .tabcontent {
            width: 100%;
            height: 700px;
        }

        .img {
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s;
        }

        .img:hover {
            opacity: 0.7;
        }

        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            padding-top: 100px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0, 0, 0); /* Fallback color */
            background-color: rgba(0, 0, 0, 0.9); /* Black w/ opacity */
        }

        /* Modal Content (image) */
        .modal-content {
            margin: auto;
            display: block;
            width: 80%;
            max-width: 700px;
        }

        /* Caption of Modal Image */
        #caption {
            margin: auto;
            display: block;
            width: 80%;
            max-width: 700px;
            text-align: center;
            color: #ccc;
            padding: 10px 0;
            height: 150px;
        }

        /* Add Animation */
        .modal-content, #caption {
            -webkit-animation-name: zoom;
            -webkit-animation-duration: 0.6s;
            animation-name: zoom;
            animation-duration: 0.6s;
        }

        @-webkit-keyframes zoom {
            from {
                -webkit-transform: scale(0)
            }
            to {
                -webkit-transform: scale(1)
            }
        }

        @keyframes zoom {
            from {
                transform: scale(0)
            }
            to {
                transform: scale(1)
            }
        }

        /* The Close Button */
        .close {
            position: absolute;
            top: 15px;
            right: 35px;
            color: #f1f1f1;
            font-size: 70px;
            font-weight: bold;
            transition: 0.3s;
            transform: scale(4);
        }

        .close:hover,
        .close:focus {
            color: #bbb;
            text-decoration: none;
            cursor: pointer;
        }

        /* 100% Image Width on Smaller Screens */
        @media only screen and (max-width: 700px) {
            .modal-content {
                width: 100%;
            }
        }
    </style>
</head>
<body>

<jsp:include page="/_menu.jsp"></jsp:include>


<div class="tab">
    <button> </button>
    <button class="tablinks" onclick="openCity(event, 'Welcome')" id="defaultOpen">Info</button>
    <button class="tablinks" onclick="openCity(event, 'Orders')" >Orders</button>
    <button class="tablinks" onclick="openCity(event, 'applications')">Pending applications</button>
</div>



<div id="Welcome" class="tabcontent">
    <h3 style="color: mintcream">Greetings, ${loginedUser.userName}! </h3>
    <h3> This is Your dashboard! </h3>
    <h3> Please proceed to the tabs on the left in order to check Your orders and manage applications</h3>
</div>

<div id="Orders" class="tabcontent">
    <h3 style="color: mintcream" > Your orders</h3>
    <table style="background-color: mintcream" align="center">
        <tr>
            <td>roomNo</td>
            <td>Sleeps</td>
            <td>Class</td>
            <td>checkIn</td>
            <td>checkOut</td>
            <td>Total Price</td>
            <td>Payment due date</td>
            <td>Pay</td>
        </tr>
        <c:forEach items="${orderList}" var="order">
            <form method="post" action="/controller">
                <tr>
                    <input type="hidden" name="command" value="confirmOrder">
                    <td>
                        <input name="prodId" value="${order.roomNo}" type="hidden">
                            ${order.roomNo}
                    </td>
                    <td>${order.sleeps}</td>
                    <td>${order.clazz}</td>
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
                    <td align="center">
                        <c:if test="${order.paid==null}">
                        <p style="size: auto; color: green">✔</p>
                        </c:if>
                        <c:if test="${order.paid!=null}">
                        <input type="submit" class="btn btn-success" value="Confirm">
                        </c:if>
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>
    <p style="color: mintcream;" align="center">${message}</p>
    <h4 style="color: mintcream" > Please remember that You have to pay for the order till the due date 12:00 am</h4>
    <h4 style="color: mintcream" > Otherwise it is going to be cancelled autimatically</h4>
</div>

<div id="applications" class="tabcontent">
    <p style="color:mintcream; margin-left: 22.5%"> Your Applications</p>
    <table style="background-color: mintcream" align="center">
        <tr>
            <td>Application#</td>
            <td>sleeps</td>
            <td>class</td>
            <td>Check In</td>
            <td>Check Out</td>
            <td>Cancel application</td>
        </tr>
        <c:forEach items="${applList}" var="appl">
            <form method="post" action="/controller">
                <input type="hidden" name="command" value="cancelApp">
                <tr>
                    <td>
                        <input name="appId" type="hidden" value="${appl.applId}">
                            ${appl.applId}
                    </td>
                    <td>${appl.sleeps}</td>
                    <td>${appl.clazz}</td>
                    <td>${appl.checkIn}</td>
                    <td>${appl.checkOut}</td>
                    <td align="center">
                        <input type="submit" class="btn btn-danger" value="Cancel">
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>
    <p style=" color:mintcream;" align="center">${applMessage}</p>

    <p style="color:mintcream;margin-left: 22.5%">Offers for Your applications </p>

    <table style="background-color: mintcream" align="center">
        <tr>
            <td>Application#</td>
            <td>room #</td>
            <td>Sleeps</td>
            <td>class</td>
            <td>Check In</td>
            <td>Check Out</td>
            <td>$/day</td>
            <td></td>
            <td>Book It!</td>
        </tr>

        <c:forEach items="${offerList}" var="offer">
            <form method="post" action="/controller">
                <tr>
                    <td>
                        <input type="hidden" name="reqFrom" value="${reqFrom}">
                        <input type="hidden" name="command" value="bookARoom">
                        <input name="appId" type="hidden" value="${offer.applId}">
                            ${offer.applId}
                    </td>
                    <td>
                        <input name="prodId" type="hidden" value="${offer.roomNo}"/>
                            ${offer.roomNo}
                    </td>
                    <td>${offer.sleeps}</td>
                    <td>${offer.clazz}</td>
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
                        <img class="img" id="myImg${offer.roomNo}" src="${offer.image}" width="180" height="125"
                             alt="Apartment# ${offer.roomNo}, class ${offer.clazz}, room for ${offer.sleeps} persons, ${offer.price}$/ a day">
                    </td>
                    <td>
                        <input type="submit" value="Book It!">
                    </td>
                </tr>
            </form>
            <div id="myModal" class="modal">
                <span class="close" style="color:white;">&times;</span>
                <img class="modal-content" id="img01">
                <div id="caption"></div>
            </div>
            <script>
                // Get the modal
                var modal = document.getElementById('myModal');

                // Get the image and insert it inside the modal - use its "alt" text as a caption
                var img = document.getElementById('myImg${offer.roomNo}');
                var modalImg = document.getElementById("img01");
                var captionText = document.getElementById("caption");
                img.onclick = function () {
                    modal.style.display = "block";
                    modalImg.src = this.src;
                    captionText.innerHTML = this.alt;
                }

                // Get the <span> element that closes the modal
                var span = document.getElementsByClassName("close")[0];

                // When the user clicks on <span> (x), close the modal
                span.onclick = function () {
                    modal.style.display = "none";
                }
            </script>
        </c:forEach>
    </table>
    <p style="color: white; ;" align="center">${offerMessage}</p>
</div>
<script>
    function openCity(evt, cityName) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(cityName).style.display = "block";
        evt.currentTarget.className += " active";
    }

    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();
</script>
</body>
</html>