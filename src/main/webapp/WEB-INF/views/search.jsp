<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/movies.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/wrap.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/search.css"   >
    <link href='https://fonts.googleapis.com/css?family=Preahvihear' rel='stylesheet'>
    <script src="../resources/script/mbtimovies.js" defer="defer"></script>
</head>
<body>
<header>
    <%@ include file="header.jsp" %>
</header>
<div id="content">
    <div id="contentTitle">
        <p id="pageTitle">Result: ${searchValue}</p>
    </div>
    <div id="movieContent">
        <p id="noSearch" style="display:${movies.size() == 0 ? 'block' : 'none'}">검색 결과가 없습니다.</p>
        <c:forEach var="movie" items="${movies}">
            <div class="movieBox">
                <div class="moviePosterImage">
                    <img src="${movie.posterLink}">
                </div>
                <p class="movieTitle">${movie.title}</p>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>