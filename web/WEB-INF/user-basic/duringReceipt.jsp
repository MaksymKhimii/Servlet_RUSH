<%--
  Created by IntelliJ IDEA.
  User: meizo
  Date: 28.03.2022
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>.table_blur {
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
        background: linear-gradient(rgba(255, 255, 255, 0), rgba(255, 255, 255, .08));
    }

    .table_blur tr:nth-child(odd) {
        background: #ebf3f9;
    }

    .table_blur th:first-child {
        border-left: 1px solid #777777;
        border-bottom: 1px solid #777777;
        box-shadow: inset 1px 1px 0 #999999, inset 0 -1px 0 #999999;
    }

    .table_blur th:last-child {
        border-right: 1px solid #777777;
        border-bottom: 1px solid #777777;
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
        body {
            font-family: Arial;
            color: white;
        }

        .split {
            height: 100%;
            width: 50%;
            position: fixed;
            top: 8%;
            overflow-x: auto;
            padding-top: 20px;
        }

        .left {
            left: 0;
            background-color: #2196F3;
        }

        .right {
            right: 0;
            background-color: white;
        }

        .centered {
            position: relative;
            top: 45%;
            left: 50%;
            transform: translate(-50%, -50%);
            text-align: center;
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
    <title><fmt:message key="cashier.header.Cashier"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/parts/cashier_navbar.jsp"/>
<div class="split left">
    <div class="centered">
        <p>
        <div style="text-align: center;">
            <fmt:message key="cashier.label.cashier_successfully_logged_in"/>
            <h2><fmt:message key="st_cashier_jsp.label.Receipt_Id"/> ${rec}</h2>
        </div>
        <p>
        <table class="table_blur">
            <thead>
            <tr>
                <th><fmt:message key="st_cashier_jsp.label.idreceipt"/></th>
                <th><fmt:message key="merchandiser_jsp.table.idproducts"/></th>
                <th><fmt:message key="merchandiser_jsp.table.name"/></th>
                <th><fmt:message key="merchandiser_jsp.table.quantity"/></th>
                <th><fmt:message key="merchandiser_jsp.table.weight"/></th>
                <th><fmt:message key="merchandiser_jsp.table.tonnage"/></th>
                <th><fmt:message key="merchandiser_jsp.table.price"/></th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="basket" scope="request" type="java.util.List"/>
            <c:forEach items="${basket}" var="bas">
                <tr>
                    <th>${bas.idreceipt}</th>
                    <th>${bas.idproduct}</th>
                    <th>${bas.name}</th>
                    <th>${bas.quantity}</th>
                    <th>${bas.weight}</th>
                    <th>${bas.tonnage}</th>
                    <th>${bas.price}</th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </p>
        <h2><fmt:message key="report.label.Total_Sum"/>${totalSum}</h2>
        <form name="closeReceipt" method="post" action="closeReceipt">
            <div style="text-align: center;">
                <button class="button-37" role="button"><fmt:message key="button_Close_receipt"/></button>
            </div>
        </form>
    </div>
</div>

<div class="split right">
    <div class="centered">
        <h2><fmt:message key="navbar_jsp.label.Cash_register_at_your_service"/></h2>
        <form class="example" action="searchProductForReceipt" style="margin:auto;max-width:300px">
            <input type="text" placeholder=
            <fmt:message key="st_cashier_jsp.placeholder.Search_product_by_name.."/> name="name">
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>
        <p>
        <table class="table_blur">
            <thead>
            <tr>
                <th><fmt:message key="merchandiser_jsp.table.idproducts"/></th>
                <th><fmt:message key="merchandiser_jsp.table.name"/></th>
                <th><fmt:message key="merchandiser_jsp.table.quantity"/></th>
                <th><fmt:message key="merchandiser_jsp.table.weight"/></th>
                <th><fmt:message key="merchandiser_jsp.table.tonnage"/></th>
                <th><fmt:message key="merchandiser_jsp.table.price"/></th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="products" scope="request" type="java.util.List"/>
            <c:forEach items="${products}" var="prod">
                <tr>
                    <th>${prod.idproducts}</th>
                    <th>${prod.name}</th>
                    <th>${prod.quantity}</th>
                    <th>${prod.weight}</th>
                    <th>${prod.tonnage}</th>
                    <th>${prod.price}</th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </p>
    </div>
</div>
</body>
<html>

