<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
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
    <a class="active" href="/logout"><fmt:message key="navbar_jsp.href.Log_out"/></a>
    <a href="/SignUp"><fmt:message key="navbar_jsp.href.Sign_Up"/></a>
    <div class="topnav-right">
        <a href="/info"><fmt:message key="navbar_jsp.href.Info"/> </a>
        <a href="?lang=en">Eng</a>
        <a href="?lang=ru">Ru</a>
    </div>
</div>
<div style="text-align: center; ">
    <h2><fmt:message key="navbar_jsp.label.Cash_register_at_your_service"/></h2>
</div>
</body>
</html>