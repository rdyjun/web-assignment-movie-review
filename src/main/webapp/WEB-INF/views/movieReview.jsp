<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/movieReview.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/wrap.css">
    <link href='https://fonts.googleapis.com/css?family=Preahvihear' rel='stylesheet'>
    <script src="../resources/script/movies.js" defer="defer"></script>
</head>
<body>
<header>
    <%@ include file="header.jsp" %>
</header>
<div id="content">
    <div id="top">
        <div id="poster">
            <img src="${movie.posterLink}">
        </div>
        <ul id="movieInfo">
            <li id="movieTitle">${movie.title}</li>
            <li id="multiInfo">
                <p id="releaseDate">${movie.releaseDate} 개봉</p>
                <div id="runtimeBlock">
                    <span class="material-icons">
                        schedule
                    </span>
                    <p id="runtime">${movie.runtime}분</p>
                </div>

            </li>
            <li id="category">장르: ${String.join(", ", movie.category)}</li>
            <li id="overView">설명: ${movie.overView}</li>
            <iframe src="${movie.videosLink}" id="videos" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
        </ul>
    </div>
    <div id="reviews">

    </div>
</div>
</body>
</html>