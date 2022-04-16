<%--
  Created by IntelliJ IDEA.
  User: meizo
  Date: 08.02.2022
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources_ru"/>
<html>
<style>.table_blur{
    margin: auto;
}
.table_blur {
    background: #f5ffff;
    border-collapse: collapse;
    text-align: left;
}
.table_blur th {
    border-top: 1px solid #777777;
    border-bottom: 1px solid #777777;
    box-shadow: inset 0 1px 0 #999999, inset 0 -1px 0 #999999;
    background: linear-gradient(#9595b6, #5a567f);
    color: white;
    padding: 10px 15px;
    position: relative;
}
.table_blur th:after {
    content: "";
    display: block;
    position: absolute;
    left: 0;
    top: 25%;
    height: 25%;
    width: 100%;
    background: linear-gradient(rgba(255, 255, 255, 0), rgba(255,255,255,.08));
}
.table_blur tr:nth-child(odd) {
    background: #ebf3f9;
}
.table_blur th:first-child {
    border-left: 1px solid #777777;
    border-bottom:  1px solid #777777;
    box-shadow: inset 1px 1px 0 #999999, inset 0 -1px 0 #999999;
}
.table_blur th:last-child {
    border-right: 1px solid #777777;
    border-bottom:  1px solid #777777;
    box-shadow: inset -1px 1px 0 #999999, inset 0 -1px 0 #999999;
}
.table_blur td {
    border: 1px solid #e3eef7;
    padding: 10px 15px;
    position: relative;
    transition: all 0.5s ease;
}
.table_blur tbody:hover td {
    color: transparent;
    text-shadow: 0 0 3px #a09f9d;
}
.table_blur tbody:hover tr:hover td {
    color: #444444;
    text-shadow: none;
}</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    body {
        font-family: Arial;
    }

    * {
        box-sizing: border-box;
    }

    form.example input[type=text] {
        padding: 10px;
        font-size: 17px;
        border: 1px solid grey;
        float: left;
        width: 80%;
        background: #f1f1f1;
    }

    form.example button {
        float: left;
        width: 20%;
        padding: 10px;
        background: #2196F3;
        color: white;
        font-size: 25px;
        border: 1px solid grey;
        border-left: none;
        cursor: pointer;
    }

    form.example button:hover {
        background: #0b7dda;
    }

    form.example::after {
        content: "";
        clear: both;
        display: table;
    }
</style>
</html>

<html>

<head>
    <title><fmt:message key="merchandiser_jsp.title_Merchandiser"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<jsp:include page="/WEB-INF/parts/merchandiser_navbar.jsp"/>

<p>
<div style="text-align: center;"><fmt:message key="merchandiser_jsp.label_merchandiser_successfully_logged_in"/> </div>
<br> <%-- специальный отступ для нормального вида поисковика, не убирать!!!--%>
<form class="example" action="searchProduct" style="margin:auto;max-width:300px">
    <input type="text" placeholder=<fmt:message key="st_cashier_jsp.placeholder.Search_product_by_name.." /> name="name">
    <button type="submit"><i class="fa fa-search"></i></button>
</form>
<table class="table_blur">
    <thead>
    <tr>
        <th><fmt:message key="merchandiser_jsp.table.idproducts"/> </th>
        <th><fmt:message key="merchandiser_jsp.table.name"/> </th>
        <th><fmt:message key="merchandiser_jsp.table.quantity"/> </th>
        <th><fmt:message key="merchandiser_jsp.table.weight"/> </th>
        <th><fmt:message key="merchandiser_jsp.table.tonnage"/> </th>
        <th><fmt:message key="merchandiser_jsp.table.price"/> </th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="products" scope="request" type="java.util.List"/>
    <c:forEach items="${products}" var="prod">
        <tr>
            <th>${prod.idproducts}</th>
            <th> ${prod.name}</th>
            <th>${prod.quantity}</th>
            <th>${prod.weight}</th>
            <th><input type="text" value="${prod.tonnage}" disabled></th>
            <th>${prod.price}</th>
        </tr>
    </c:forEach>
    </tbody>
</table>
</p>
</body>
</html>
