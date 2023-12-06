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
                    <div class="movieBox" onclick="location.href='/movies/${movie.id}'">
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