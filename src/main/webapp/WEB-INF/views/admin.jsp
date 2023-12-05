<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/admin.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/wrap.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="../resources/script/admin.js" defer="defer"></script>
</head>
<body>
    <header>
        <%@ include file="header.jsp" %>
    </header>
    <div id="adminContent">
        <div id="contentHeader">
            <div id="leftContent">
                <div id="selectList">
                    <button id="userList" onclick="showUnblockUser()">유저 목록</button>
                    <button id="blockList" onclick="showblockUser()">블락 목록</button>
                </div>
                <div id="whitelist">
                    <p class="boxTitle">회원 관리</p>
                    <div class="dataTable">
                        <ul class="dataTitle">
                            <li>Name</li>
                            <li>ID</li>
                        </ul>
                        <c:forEach var="whiteList" items="${whiteList}">
                            <ul>
                                <li>${whiteList.name}</li>
                                <li>${whiteList.id}</li>
                                <form method="post" action="/blockMember">
                                    <input type="text" name="memberId" style="display:none" value="${whiteList.id}">
                                    <button type="submit" class="blockIcon">
                                    <span class="material-icons">
                                        block
                                    </span>
                                    </button>
                                </form>
                            </ul>
                        </c:forEach>
                    </div>
                </div>
                <div id="blackList">
                    <p class="boxTitle">블랙 리스트</p>
                    <nav class="infoBlock">
                        <div class="dataTable">
                            <ul class="dataTitle">
                                <li>Name</li>
                                <li>ID</li>
                            </ul>
                            <c:forEach var="blackList" items="${blackList}">
                                <ul>
                                    <li class="userData">${blackList.name}</li>
                                    <li class="userData" >${blackList.id}</li>
                                    <form method="post" action="/unblockMember">
                                        <input type="text" name="memberId" style="display:none" value="${blackList.id}">
                                        <button type="submit" class="unblockIcon">
                                            <span class="material-icons" >
                                                settings_backup_restore
                                            </span>
                                        </button>
                                    </form>
                                </ul>
                            </c:forEach>
                        </div>
                    </nav>
                </div>
            </div>
            <div id="report">
                <p class="boxTitle">신고 리뷰</p>
                <nav class="infoBlock">
                    <div class="dataTable">
                        <ul class="dataTitle">
                            <li>Time</li>
                            <li>Name</li>
                            <li>Comment</li>
                        </ul>
<%--                        <c:forEach var="report" items="${report}" varStatus="status">--%>
<%--                            <ul>--%>
<%--                                <li class="userData">${report.reportTime}</li>--%>
<%--                                <li class="userData">${report.reporter}</li>--%>
<%--                                <li class="userData" >//해야할 곳</li>--%>
<%--                                <form method="post" action="/blockMember">--%>
<%--                                    <input type="text" name="memberId" style="display:none" value="${report.id}">--%>
<%--                                    <button type="submit">--%>
<%--                                    <span class="material-icons" class="blockIcon">--%>
<%--                                        block--%>
<%--                                    </span>--%>
<%--                                    </button>--%>
<%--                                </form>--%>
<%--                            </ul>--%>
<%--                        </c:forEach>--%>
                    </div>
                </nav>
            </div>
        </div>
        <div id="log">
            <p class="boxTitle">Log</p>
            <div class="dataTable">
                <ul class="dataTitle">
                    <li class="time">Time</li>
                    <li class="logContent">Log</li>
                    <li class="author">author</li>
                </ul>
                <c:forEach var="log" items="${logList}">
                    <ul>
                        <li class="userData, time">${log.time}</li>
                        <li class="userData, logContent">${log.content}</li>
                        <li class="userData, author">${log.author}</li>
                    </ul>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>