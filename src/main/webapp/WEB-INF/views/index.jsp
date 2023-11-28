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
    <%@ include file="header.jsp" %>

    <% if (pageIndex == null) { %>
    <%@ include file="home.jsp" %>
    <% } %>
    <%
        response.sendRedirect();
    %>
    <% if (pageIndex.equals("login")) { %>
    <%@ include file="login.jsp" %>
    <% } %>
</body>
</html>