<%--
  Created by IntelliJ IDEA.
  User: meizo
  Date: 08.02.2022
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>
    body {font-family: Arial, Helvetica, sans-serif;}

    /* Full-width input fields */
    input[type=text], input[type=password] {
      width: 100%;
      padding: 12px 20px;
      margin: 8px 0;
      display: inline-block;
      border: 1px solid #ccc;
      box-sizing: border-box;
    }

    /* Set a style for all buttons */
    button {
      background-color: #04AA6D;
      color: white;
      padding: 14px 20px;
      margin: 8px 0;
      border: none;
      cursor: pointer;
      width: 100%;
    }

    button:hover {
      opacity: 0.8;
    }

    /* Extra styles for the cancel button */
    .cancelbtn {
      width: auto;
      padding: 10px 18px;
      background-color: #f44336;
    }

    /* Center the image and position the close button */
    .imgcontainer {
      text-align: center;
      margin: 24px 0 12px 0;
      position: relative;
    }

    .container {
      padding: 16px;
    }

    span.psw {
      float: right;
      padding-top: 16px;
    }

    /* The Modal (background) */
    .modal {
      display: none;
      position: fixed;
      z-index: 1;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      overflow: auto;
      background-color: rgb(0,0,0);
      background-color: rgba(0,0,0,0.4);
      padding-top: 60px;
    }

    /* Modal Content/Box */
    .modal-content {
      background-color: #fefefe;
      margin: 5% auto 15% auto;
      border: 1px solid #888;
      width: 80%;
    }

    /* The Close Button (x) */
    .close {
      position: absolute;
      right: 25px;
      top: 0;
      color: #000;
      font-size: 35px;
      font-weight: bold;
    }

    .close:hover,
    .close:focus {
      color: red;
      cursor: pointer;
    }

    /* Add Zoom Animation */
    .animate {
      -webkit-animation: animatezoom 0.6s;
      animation: animatezoom 0.6s
    }

    @-webkit-keyframes animatezoom {
      from {-webkit-transform: scale(0)}
      to {-webkit-transform: scale(1)}
    }

    @keyframes animatezoom {
      from {transform: scale(0)}
      to {transform: scale(1)}
    }

    /* Change styles for span and cancel button on extra small screens */
    @media screen and (max-width: 300px) {
      span.psw {
        display: block;
        float: none;
      }
      .cancelbtn {
        width: 100%;
      }
    }
  </style>
</head>
<body>
<jsp:include page="/WEB-INF/parts/navbar.jsp"/>
<div  style="text-align: center;">
<button onclick="document.getElementById('id01').style.display='block'" style="width:auto;"><fmt:message key="index_jsp.button.Login1"/> </button>
</div>
<div id="id01" class="modal">

  <form class="modal-content animate" name="loginForm" method="post" action="login">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container">
      <%--@declare id="psw"--%><%--@declare id="uname"--%><label for="uname"><b><fmt:message key="index_jsp.label.Username"/></b></label>
      <input type="text" placeholder=<fmt:message key="index_jsp.placeholder.Username" /> name="username" required>

      <label for="psw"><b><fmt:message key="index_jsp.label.Password"/> </b></label>
      <input type="password" placeholder=<fmt:message key="index_jsp.placeholder.Password"/> name="password" required>

      <button type="submit"><fmt:message key="index_jsp.button.Login2"/> </button>
      <label>
        <input type="checkbox" checked="checked" name="remember"> <fmt:message key="index_jsp.checkbox.Remember_me"/>
      </label>
    </div>

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn"><fmt:message key="index_jsp.button.Cancel"/></button>
      <span class="psw"> <a href="/SignUp"><fmt:message key="index_jsp.href.sign_up"/> </a></span>
    </div>
  </form>
</div>

<script>
  // Get the modal
  var modal = document.getElementById('id01');

  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function(event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  }
</script>
</body>
</html>

