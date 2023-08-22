<%@page import="kr.farmstory1.dto.UserDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
	
	if(sessUser == null){
		response.sendRedirect("/Farmstory1/user/login.jsp?success=101");
		return;
	}
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팜스토리</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"/>
    <link rel="stylesheet" href="./css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <script src="/Farmstory1/admin/js/popup.js"></script>
</head>
<body>
    <div id="container">
        <header>
            <a href="./index.html" class="logo"><img src="./images/admin_logo.jpg" alt="로고"/></a>
            <p>
                <a href="/Farmstory1">HOME |</a>
                <a href="#">로그아웃 |</a>
                <a href="#">고객센터</a>
            </p>
        </header>