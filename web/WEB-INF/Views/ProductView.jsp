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
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Products</title>
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
            width: 60%;

        }

        h3 {
            color: white;
            text-align: center;
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

        select {
            width: 150px;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            align-content: center;
        }

        input {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-top: 6px;
            margin-bottom: 16px;
        }

        .input1 {
            width: 80%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-top: 6px;
            margin-bottom: 16px;
        }

        input[type=submit] {
            background-color: #4CAF50;
            color: white;
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
<jsp:include page="../../_menu.jsp"/>
<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:15%; padding-left: 15px; z-index: -1;">
    <h3 class="w3-bar-item">Filter results</h3>
    <form method="post" action="${pageContext.request.contextPath}/filterDB">
        <label> rooms<br>
            <select name="sleeps">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
            </select>
        </label>
        <br>
        <label> apartment class <br>
            <select name="class">
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
            </select>
        </label>
        <label> checkIn
            <input type="date" name="checkIn" class="input1"/>
        </label>
        <label> check Out
            <input type="date" name="checkOut" class="input1"/>
        </label>
        <input type="submit" value="Filter" class="input1"/>
    </form>
</div>
<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:15%; right:0; padding-left: 15px">
    <h3 class="w3-bar-item">Sort</h3>
    <form method="get" action="productView">
        <label> Order
            <p>
                <input class="w3-radio" type="radio" name="order" value="asc">
                <label> Ascending <span class="glyphicon glyphicon-sort-by-attributes"/> </label></p>
            <p>
                <input class="w3-radio" type="radio" name="order" value="desc">
                <label> Descending <span class="glyphicon glyphicon-sort-by-attributes-alt"/></label></p>

        </label>
        <label>Sort By
            <select name="orderBy">
                <option value="class">class</option>
                <option value="sleeps">sleeps</option>
                <option value="price">price</option>
            </select>
        </label>
        <br>
        <input type="submit" value="sub" class="input1">
    </form>
</div>

<h3 style="color: white">Available rooms</h3>

<p style="color: blue;" align="center">${message}</p>

<table align="center" style="background-color: mintcream">
    <tr>
        <th>image</th>
        <th>Sleeps</th>
        <th>Class</th>
        <th>$/day</th>
        <th>Check-In</th>
        <th>Check-Out</th>
        <th>Book It!</th>
    </tr>
    <c:forEach items="${productList}" var="product" varStatus="loop">
        <form method="post" action="${pageContext.request.contextPath}/productView">
            <tr>
                <input style="color: white" name="prodId" type="hidden" value="${product.roomNo}">
                <td>
                    <img class="img" id="myImg${product.roomNo}" src="${product.image}" width="180" height="125"
                         alt="Apartment# ${product.roomNo}, class ${product.clazz}, room for ${product.sleeps} persons, ${product.price}$/ a day">
                </td>
                <td>${product.sleeps}</td>
                <td>${product.clazz}</td>
                <td>${product.price}</td>
                <td align="center">
                    <input type="date" name="checkIn">
                </td>
                <td align="center">
                    <input type="date" name="checkOut">
                </td>
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </form>
        <!-- The Modal -->
        <div id="myModal" class="modal">
            <span class="close" style="color:white;">&times;</span>
            <img class="modal-content" id="img01">
            <div id="caption"></div>
        </div>

        <script>
            // Get the modal
            var modal = document.getElementById('myModal');

            // Get the image and insert it inside the modal - use its "alt" text as a caption
            var img = document.getElementById('myImg${product.roomNo}');
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


</body>
</html>
