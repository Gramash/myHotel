<%--
  Created by IntelliJ IDEA.
  User: reygo
  Date: 1/16/2018
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
    <script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
    <script>
        $(function () {
            $("#datepicker").datepicker();
        });
    </script>

</head>


<body>
<form action="showDate.jsp">
    <label for="datepicker">Enter date:</label>
    <input type="text" name="selDate" id="datepicker">

    <input type="submit" value="Submit">
</form>
<form action="showDate.jsp">
    <label for="datepicker">Enter date:</label>
    <input type="text" name="selDate" id="datepicker1">

    <input type="submit" value="Submit">
</form>
<form action="showDate.jsp">
    <label for="datepicker">Enter date:</label>
    <input type="text" name="selDate" id="datepicker2">

    <input type="submit" value="Submit">
</form>

</body>

</html>
