<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String pageIndex = (String) session.getAttribute("pageIndex");
%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/idx.css">
    <link rel="stylesheet" type="text/css" href="../resources/wrap.css">
</head>
<body>
    <header>
        <%@ include file="header.jsp" %>
    </header>
    <%@ include file="home.jsp" %>
</body>
</html>