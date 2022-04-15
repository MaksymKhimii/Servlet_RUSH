<%--
  Created by IntelliJ IDEA.
  User: meizo
  Date: 08.04.2022
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources_ru"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <a class="active" href="/logout"><fmt:message key="st_cashier_navbar_jsp.href.Log_out"/> </a>
    <a href="/SignUp"><fmt:message key="st_cashier_navbar_jsp.href.Sign_Up"/> </a>
    <a href="/Xreport"><fmt:message key="st_cashier_navbar_jsp.href.X_report"/> </a>
    <a href="/report"><fmt:message key="st_cashier_navbar_jsp.href.report"/> </a>
    <a href="/AllReports"><fmt:message key="st_cashier_navbar_jsp.href.All_Reports"/> </a>
    <div class="topnav-right">
        <a href="#"><fmt:message key="st_cashier_navbar_jsp.href.Info"/> </a>
       <%-- <form id="locale" action="controller"
              method="post">
            <input hidden name="command" value="selectLocale"/>

            <select name="localeToSet" onchange="this.form.submit()">

                <option value="${defaultLocale}">${defaultLocale}[Set]
                </option>
                <c:forEach var="localeName" items="${locales}">
                    <c:if test="${localeName!=defaultLocale}">
                        <option value="${localeName}">${localeName}
                        </option>
                    </c:if>
                </c:forEach>
            </select>
        </form> --%>
        <a href="#"><fmt:message key="st_cashier_navbar_jsp.href.Change_Language"/> </a>
    </div>
</div>
<div style="text-align: center;">
    <h2><fmt:message key="st_cashier_navbar_jsp.label.Cash_register_at_your_service"/> </h2>
</div>
<script>

</script>
</body>
</html>
