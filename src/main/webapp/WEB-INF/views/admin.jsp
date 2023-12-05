<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/admin.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/wrap.css">
</head>
<body>
    <div id="adminContent">
        <div>
            <ul id="userList">
                <p class="boxTitle">회원 관리</p>
                <li>ID</li>
                <li>Name</li>
                <c:forEach var="movie" items="${movies}">
                <li></li>
                </c:forEach>
            </ul>
            <ul id="blackList">
                <p class="boxTitle">블랙 리스트</p>
                <li>ID</li>
                <li>Name</li>
                <c:forEach var="movie" items="${movies}">
                    <li></li>
                </c:forEach>
            </ul>
        </div>
        <div>

        </div>
    </div>
</body>
</html>