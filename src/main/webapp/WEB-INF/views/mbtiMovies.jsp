<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/movies.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/mbtiMovies.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/wrap.css">
    <link href='https://fonts.googleapis.com/css?family=Preahvihear' rel='stylesheet'>
    <script src="../resources/script/mbtimovies.js" defer="defer"></script>
</head>
<body>
<header>
    <%@ include file="header.jsp" %>
</header>
<div id="content">
    <div id="contentTitle">
        <p id="pageTitle">Movies For MBTI</p>
        <div id="mbtiSelectBlock">
            <c:set var="mbti" value="${param.mbti}" />
            <select id="first" onchange="mbtipage()">
                <option ${mbti.contains("I") ? 'selected' : ''}>I</option>
                <option ${mbti.contains("E") ? 'selected' : ''}>E</option>
            </select>
            <select id="second" onchange="mbtipage()">
                <option ${mbti.contains("N") ? 'selected' : ''}>N</option>
                <option ${mbti.contains("S") ? 'selected' : ''}>S</option>
            </select>
            <select id="third" onchange="mbtipage()">
                <option ${mbti.contains("F") ? 'selected' : ''}>F</option>
                <option ${mbti.contains("T") ? 'selected' : ''}>T</option>
            </select>
            <select id="fourth" onchange="mbtipage()">
                <option ${mbti.contains("P") ? 'selected' : ''}>P</option>
                <option ${mbti.contains("J") ? 'selected' : ''}>J</option>
            </select>
        </div>
    </div>


    <div id="movieContent">
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