<%--
  Created by IntelliJ IDEA.
  User: meizo
  Date: 04.04.2022
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
<body>
<jsp:include page="/WEB-INF/parts/merchandiser_navbar.jsp"/>
<div class="alert success" style="text-align: center;">
    <span class="closebtn">×</span>
    <strong>Success!</strong> The product has been deleted from receipt!
</div>
<script>
    var close = document.getElementsByClassName("closebtn");
    var i;

    for (i = 0; i < close.length; i++) {
        close[i].onclick = function(){
            var div = this.parentElement;
            div.style.opacity = "0";
            setTimeout(function(){ div.style.display = "none"; }, 600);
        }
    }
</script>
<form name="returnSt_Cashier" method="post" action="st_cashier">
    <div style="text-align: center;">
        <button class="button-37" role="button">Return</button>
    </div>
</form>
</body>
</html>




