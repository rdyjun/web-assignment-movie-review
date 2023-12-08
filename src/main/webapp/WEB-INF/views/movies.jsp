<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/movies.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/wrap.css">
    <link href='https://fonts.googleapis.com/css?family=Preahvihear' rel='stylesheet'>
    <script src="../resources/script/movies.js" defer="defer"></script>
</head>
<body>
    <header>
        <%@ include file="header.jsp" %>
    </header>
    <div id="content">
        <div id="contentTitle">
            <p id="pageTitle">Trending Movies</p>
            <select onchange="changeSort();" id="movieSelecter" name="sort">
                <option value="pop" selected>인기순</option>
                <option value="rec" id="rec">최신순</option>
            </select>
        </div>

        <div id="movieContent">
            <c:forEach var="movie" items="${movies}">
                <div class="movieBox">
                    <div class="moviePosterImage" onclick="location.href='/movies/${movie.id}'">
                        <img src="${movie.posterLink}">
                    </div>
                    <p class="movieTitle">${movie.title}</p>
                    <div id="eachMoviesVote">
                        <c:forEach var="i" begin="1" end="5" step="1" varStatus="status">
                            <c:set var="fillPercent" value="${((vote.get(String.valueOf(movie.id)) * 100) - ((i - 1) * 100.0))}" />
                            <c:set var="purpleColor" value="${fillPercent >= 100.0 ? 100 : 0}" />
                            <svg class="reviewRating" fill="#9664FF" display="${purpleColor <= 0 ? 'none' : 'block'}" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 22 22">
                                <path d="M12 2.5l2.405 7.385h7.795l-6.305 4.573 2.405 7.387-6.305-4.572-6.305 4.572 2.405-7.387-6.305-4.573h7.795z"/>
                            </svg>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>