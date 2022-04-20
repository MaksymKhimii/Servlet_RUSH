<%--
  Created by IntelliJ IDEA.
  User: meizo
  Date: 04.04.2022
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
</html>
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
<style>

    .dropup-content a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    .dropup-content a:hover {background-color: #ccc}

</style>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>

<head>
    <title><fmt:message key="merchandiser_jsp.title_Merchandiser"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/parts/st_cashier_navbar.jsp"/>
<div style="text-align: center">
    <h2><fmt:message key="st_cashier_jsp.label.Receipt_Id"/>: ${rec}</h2>
</div>

<p>

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
        <jsp:useBean id="receipt" scope="request" type="java.util.List"/>
        <c:forEach items="${receipt}" var="recc">

            <tr>
                <form method="get" action="deleteProductFromReceipt">
                    <th><input type="number" value="${recc.idproduct}" name="idproduct"></th>
                    <th><input type="text" value="${recc.name}" name="name"></th>
                    <th><input type="number" value="${recc.quantity}" name="quantity"> </th>
                    <th><input type="number" value="${recc.weight}" name="weight"></th>
                    <th><input list="tonnage" name="tonnage" value="${recc.tonnage}" id="tonnages">
                    <datalist id="tonnage">
                        <option value="TRUE">
                        <option value="FALSE">
                    </datalist></th>
                <th><input type="number" step="0.01" value="${recc.price}" name="price"></th>
                <th><input type="submit" name="Delete" value=<fmt:message key="button_Delete"/>></th>
        </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>

<div style="text-align: center">

    <h2><fmt:message key="AllReports_jsp.table.Total_sum"/>: ${SUM}</h2>
</div>

<form name="returnStCashier" method="post" action="st_cashier">
    <div style="text-align: center;">
        <button class="button-37" role="button"><fmt:message key="button_Return"/></button>
    </div>
</form>
</form>
</body>
</html>


