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
    <link rel="stylesheet" type="text/css" href="../resources/css/header.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="../resources/script/header.js" defer="defer"></script>
</head>
<body>
<div id="gnb">
    <div id="logo-menu">
        <a href="/"><div id="logo">Talk Films</div></a>
        <button onclick="location.href='movies'" class="menu">Movies</button>
        <% if (id.equals("admin")) { %>
        <div class="menu">Admin</div>
        <% } %>
    </div>
    <div id="rightHeaderContent">
        <div id="searchBox">
            <input type="text" id="searchInput">
            <span class="material-icons">
                search
            </span>
        </div>
        <div id="buttons">
            <% if (id.equals("")) { %>
                <button onclick="location.href='login'">Login</button>
                <button onclick="location.href='register'">Register</button>
            <% } %>
            <% if (!id.equals("")) { %>
                <span class="material-icons" onclick="showMyApps()">
                    person
                </span>
                <div id="myApps">
                    <button onclick="location.href='myPage'" id="linkToMyPage">마이페이지</button>
                    <form method="post" action="/logout">
                        <input type="submit" id="logout" value="로그아웃">
                    </form>
                    <form method="post" action="/delete-account" id="deleteAccountForm" onsubmit="return isConfirm()">
                        <input type="submit"id="deleteAccount" value="회원탈퇴">
                    </form>
                </div>
            <% } %>
        </>
    </div>
</div>
</body>
</html>
