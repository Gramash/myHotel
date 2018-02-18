<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: reygo
  Date: 1/9/2018
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Register</title>
    <style>
        @import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
        body {
            background: -webkit-linear-gradient(left, #25c481, #25b7c4);
            background: linear-gradient(to right, #25c481, #25b7c4);
            font-family: 'Roboto', sans-serif;
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

        input[type=submit] {
            background-color: #4CAF50;
            color: white;
        }

        #message, #loginMessage, #nameMessage{
            display: none;
            background: #f1f1f1;
            color: #000;
            position: relative;
            padding: 10px;

        }

        #message p  {
            padding: 7px 20px;
            font-size: 90%
        }
        #loginMessage p {
            padding: 10px 20px;
            font-size: 90%
        }
        #nameMessage p {
            padding: 10px 20px;
            font-size: 90%
        }

        .neutral{
            color: black;
        }

        .valid {
            color: green;
        }

        .valid:before {
            position: relative;
            left: -35px;
            content: "✔";
        }

        .invalid {
            color: red;
        }

        .invalid:before {
            position: relative;
            left: -35px;
            content: "✖";
        }
    </style>
</head>
<body>

<jsp:include page="/_menu.jsp"/>


<div class="container">
    <form method="POST" action="/controller">
        <input type="hidden" name="command" value="register">
        <table align="center">
            <tr>
                <td>
                    <label for="id"></label>
                    <input type="text" id="id" placeholder="Login" name="userLogin"
                           pattern="^(?=.*[A-Za-z0-9а-яА-я]$)[A-Za-zа-яА-я][A-Za-zа-яА-я\d._]{7,19}$" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="psw"></label>
                    <input type="password" id="psw" name="password" placeholder="Password"
                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="name"></label>
                    <input type="text" id="name" placeholder="First Name" name="userName"
                           pattern="^(?=.*[A-Za-z0-9а-яА-я]$)[A-Za-zа-яА-я][A-Za-zа-яА-я]{2,19}$" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="emailID"></label>
                    <input type="email" id="emailID" placeholder="E-mail" name="email" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <input style="color: mintcream" type="submit" value="Submit"/>
                    <a style="color:mintcream" href="/homeView.jsp">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<c:if test="${not empty errorRegisterMessage}">
    <div class="alert alert-danger" align="center">
        <strong> Failed!</strong> ${errorRegisterMessage}
    </div>
</c:if>

<div id="message" align="center">
    <h5>Password must contain the following:</h5>
    <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
    <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
    <p id="number" class="invalid">A <b>number</b></p>
    <p id="length" class="invalid">Minimum <b>8 characters</b></p>
    <p id="length2" class="invalid"> Maximum <b>16 characters</b></p>
</div>
<div id="loginMessage" align="center">
    <h4>Login must consist of the following:</h4>
    <p  id="letter2" class="neutral">Start and end with a letter or a number</p>
    <p id="capital2" class="neutral">Can have "_" or "."</p>
    <p id="number2" class="neutral">Must be 2 to 20 symbols long</p>
    <p></p>
    <p></p>
</div>
<div id="nameMessage" align="center">
    <h4>Name must consist of the following:</h4>
    <p >Start and end with a letter </p>
    <p >Can not contain special symbols "!@#$%^&*()_.</p>
    <p >Must be 8 to 20 symbols long</p>
    <p></p>
    <p></p>
</div>

<script>
    var myInput = document.getElementById("psw");
    var myInput2 = document.getElementById("id");
    var myInput3 = document.getElementById("name");
    var letter = document.getElementById("letter");
    var capital = document.getElementById("capital");
    var number = document.getElementById("number");
    var length = document.getElementById("length");
    var length2 = document.getElementById("length2");

    // When the user clicks on the password field, show the message box
    myInput.onfocus = function () {
        document.getElementById("message").style.display = "block";
    }

    // When the user clicks outside of the password field, hide the message box
    myInput.onblur = function () {
        document.getElementById("message").style.display = "none";
    }
    // show login pattern when user clicks on loging field
    myInput2.onfocus = function () {
        document.getElementById("loginMessage").style.display = "block";
    }
    //close login field when login intup is not active
    myInput2.onblur = function () {
        document.getElementById("loginMessage").style.display = "none";
    }
    myInput3.onfocus = function () {
        document.getElementById("nameMessage").style.display = "block";
    }
    myInput3.onblur = function () {
        document.getElementById("nameMessage").style.display = "none";
    }

    // When the user starts to type something inside the password field
    myInput.onkeyup = function () {
        // Validate lowercase letters
        var lowerCaseLetters = /[a-z]/g;
        if (myInput.value.match(lowerCaseLetters)) {
            letter.classList.remove("invalid");
            letter.classList.add("valid");
        } else {
            letter.classList.remove("valid");
            letter.classList.add("invalid");
        }

        // Validate capital letters
        var upperCaseLetters = /[A-Z]/g;
        if (myInput.value.match(upperCaseLetters)) {
            capital.classList.remove("invalid");
            capital.classList.add("valid");
        } else {
            capital.classList.remove("valid");
            capital.classList.add("invalid");
        }

        // Validate numbers
        var numbers = /[0-9]/g;
        if (myInput.value.match(numbers)) {
            number.classList.remove("invalid");
            number.classList.add("valid");
        } else {
            number.classList.remove("valid");
            number.classList.add("invalid");
        }

        // Validate length
        if (myInput.value.length >= 8) {
            length.classList.remove("invalid");
            length.classList.add("valid");
        } else {
            length.classList.remove("valid");
            length.classList.add("invalid");
        }

        if (myInput.value.length >16) {
            length2.classList.remove("valid");
            length2.classList.add("invalid");
        } else {
            length2.classList.remove("invalid");
            length2.classList.add("valid");
        }

    }
</script>


</body>
</html>
