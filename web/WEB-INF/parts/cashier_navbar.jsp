<%--
  Created by IntelliJ IDEA.
  User: meizo
  Date: 24.03.2022
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }
        .topnav {
            overflow: hidden;
            background-color: #333;
        }
        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
            font-size: 20px;
        }
        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }
        .topnav a.active {
            background-color: #4CAF50;
            color: white;
        }
        .topnav-right {
            float: right;
        }
    </style>
</head>
<body>
<div class="topnav">
    <a class="active" href="/logout">Log out</a>
    <a href="/SignUp">Sign Up</a>
    <%-- здесь будет отображение всех чеков которые создал этот кассир--%>
    <a href="#">Orders</a>
    <div class="topnav-right">
        <a href="#">Info</a>
        <a href="#">Change Language</a>
    </div>
</div>
<div style="text-align: center;">
    <h2>Cash register at your service</h2>
</div>
</body>
</html>
