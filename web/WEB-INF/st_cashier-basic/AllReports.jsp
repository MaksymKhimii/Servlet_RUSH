<%--
  Created by IntelliJ IDEA.
  User: meizo
  Date: 12.04.2022
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
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
<style>
    .alert {
        padding: 20px;
        background-color: #f44336;
        color: white;
        opacity: 1;
        transition: opacity 0.6s;
        margin-bottom: 15px;
    }

    .alert.success {background-color: #4CAF50;}
    .alert.info {background-color: #2196F3;}
    .alert.warning {background-color: #ff9800;}

    .closebtn {
        margin-left: 15px;
        color: white;
        font-weight: bold;
        float: right;
        font-size: 22px;
        line-height: 20px;
        cursor: pointer;
        transition: 0.3s;
    }

    .closebtn:hover {
        color: black;
    }
    /* CSS */
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources_ru"/>
<html>

<head>
    <title>St_Cashier</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<jsp:include page="/WEB-INF/parts/st_cashier_navbar.jsp"/>

<p>
<div style="text-align: center;">
    <h2><fmt:message key="AllReports_jsp.label_All_Reports"/> </h2>
</div>
<br> <%-- специальный отступ для нормального вида поисковика, не убирать!!!--%>
<table class="table_blur">
    <thead>
    <tr>
        <th><fmt:message key="AllReports_jsp.table.Id_Report"/> </th>
        <th><fmt:message key="AllReports_jsp.table.Quantity_of_receipts"/> </th>
        <th><fmt:message key="AllReports_jsp.table.Last_receipt_id"/> </th>
        <th><fmt:message key="AllReports_jsp.table.Time"/> </th>
        <th><fmt:message key="AllReports_jsp.table.Total_sum"/> </th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="reports" scope="request" type="java.util.List"/>
    <c:forEach items="${reports}" var="rep">
        <tr>
            <th>${rep.idreport}</th>
            <th> ${rep.quantityOfReceipts}</th>
            <th>${rep.lastReceiptId}</th>
            <th>${rep.time}</th>
            <th>${rep.totalSum}</th>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<form name="returnSt_Cashier" method="post" action="st_cashier">
    <div style="text-align: center;">
        <button class="button-37" role="button"><fmt:message key="button_Return"/> </button>
    </div>
</form>

</p>
</body>
</html>
