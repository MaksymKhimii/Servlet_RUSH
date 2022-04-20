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


</body>
</html>
