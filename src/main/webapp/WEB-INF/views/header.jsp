<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String id = (String) session.getAttribute("userId");
    if (id == null)
        id = "";
%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../resources/header.css">
</head>
<body>
<div id="gnb">
    <div id="logo-menu">
        <a href="/"><div id="logo">Talk Films</div></a>
        <div class="menu">Movies</div>
        <% if (id.equals("admin")) { %>
        <div class="menu">Admin</div>
        <% } %>
    </div>
    <
    <% if (id.equals("")) { %>
    <div id="buttons">
        <button onclick="location.href='login'">Login</button>
        <button onclick="location.href='register'">Register</button>
    </div>
    <% } %>
</div>
</body>
</html>
