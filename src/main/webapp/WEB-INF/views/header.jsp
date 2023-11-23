<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        #header {
            width: 1000px;
            display: flex;
            justify-content: space-between;
        }
        #logo-menu {
            display: flex;
        }
        #buttons {
            display: flex;
        }
    </style>
</head>
<body>
<div id="header">
    <div id="logo-menu">
        <div id="logo">Logo Text</div>
        <!-- 메뉴바들을 아래와 같이 추가하세요. -->
        <div class="menu">Menu1</div>
        <div class="menu">Menu2</div>
        <!-- ... -->
    </div>
    <div id="buttons">
        <button onclick="location.href='login.jsp'">Login</button>
        <button onclick="location.href='register.jsp'">Register</button>
    </div>
</div>
</body>
</html>
