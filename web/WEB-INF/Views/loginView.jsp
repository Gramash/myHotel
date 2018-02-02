<%--
  Created by IntelliJ IDEA.
  User: reygo
  Date: 1/9/2018
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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

    </style>
</head>
<body>

<jsp:include page="../../_menu.jsp"></jsp:include>


<p style="color: red;">${errorMessage}</p>
<p style="color: blue;">${welcomeMessage}</p>
<div class="container">
    <form method="POST" action="${pageContext.request.contextPath}/login">
        <table border="0" align="center">
            <tr>
                <td>
                    <label></label>
                    <input type="text" placeholder="Login" name="userLogin" value="${user.userLogin}"
                           class="form-control" required=''/></td>
            </tr>
            <tr>
                <td>
                    <label></label>
                    <input type="password" placeholder="password" name="password" value="${user.password}"
                           class="form-control"/></td>
            </tr>
            <tr>
                <td>
                    <label></label>
                    <input type="submit" value="Submit"/>
                    <a style="color: mintcream" href="${pageContext.request.contextPath}/">Cancel</a>
                </td>
            </tr>
        </table>

        <script>
            $(document).ready(function () {
                $('#userLogin').click(function () {
                    if (!$.trim($('#userLogin').val())) {
                        alert("textbox value can't be empty");
                    }
                });
            });
        </script>
    </form>
</div>

</body>
</html>
