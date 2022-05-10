<%--
  Created by IntelliJ IDEA.
  User: meizo
  Date: 20.04.2022
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<style>
    .button-37 {
        background-color: #13aa52;
        border: 1px solid #13aa52;
        border-radius: 4px;
        box-shadow: rgba(0, 0, 0, .1) 0 2px 4px 0;
        box-sizing: border-box;
        color: #fff;
        cursor: pointer;
        font-family: "Akzidenz Grotesk BQ Medium", -apple-system, BlinkMacSystemFont, sans-serif;
        font-size: 16px;
        font-weight: 400;
        outline: none;
        outline: 0;
        padding: 10px 25px;
        text-align: center;
        transform: translateY(0);
        transition: transform 150ms, box-shadow 150ms;
        user-select: none;
        -webkit-user-select: none;
        touch-action: manipulation;
    }

    .button-37:hover {
        box-shadow: rgba(0, 0, 0, .15) 0 3px 9px 0;
        transform: translateY(-2px);
    }

    @media (min-width: 768px) {
        .button-37 {
            padding: 10px 30px;
        }

    }
</style>
</html>
<html>
<head>
    <title><fmt:message key="st_cashier_navbar_jsp.href.Info"/></title>
</head>
<body>
<jsp:include page="/WEB-INF/parts/navbar.jsp"/>
<div style="text-align: center">
    <h2><fmt:message key="Info.label"/></h2>
    <br>
</div>
<div style="text-indent: 575px">
    <fmt:message key="Info.text"/> </div>
    <br>
<div style="text-indent: 575px">
    <fmt:message key="Info.text0"/> </div>
    <br>
<div style="text-indent: 575px">
    <fmt:message key="Info.text1"/> </div>
    <br>
<div style="text-indent: 575px">
    <fmt:message key="Info.text2"/> </div>
    <br>
<div style="text-indent: 575px">
    <fmt:message key="Info.text3"/> </div>
    <br>
<div style="text-indent: 575px">
    <fmt:message key="Info.text4"/> </div>
    <br>
<div style="text-indent: 575px">
    <fmt:message key="Info.text5"/> </div>
    <br>
<div style="text-indent: 575px">
    <fmt:message key="Info.text6"/> </div>
    <br>
<div style="text-indent: 575px">
    <fmt:message key="Info.text7"/> </div>
    <br>
<div style="text-indent: 575px">
    <fmt:message key="Info.text8"/> </div>
    <br>
<div style="text-indent: 575px">
    <fmt:message key="Info.text9"/> </div>
    <br>
<div style="text-indent: 570px">
    <h2> <fmt:message key="Info.author"/></h2> </div>

<form name="returnInfo" method="post" action="login">
    <div style="text-align: center;">
        <button class="button-37" role="button"><fmt:message key="button_Return"/></button>
    </div>
</form>
</body>
</html>
