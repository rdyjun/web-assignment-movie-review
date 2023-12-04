<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/movies.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/wrap.css">
    <link href='https://fonts.googleapis.com/css?family=Preahvihear' rel='stylesheet'>
</head>
<body>
<header>
    <%@ include file="header.jsp" %>
</header>
<div id="content">
    <div>
        <p id="pageTitle">Trending Movies</p>
        <select>
            <option selected>I</option>
            <option>E</option>
        </select>
        <select>
            <option selected>N</option>
            <option>S</option>
        </select>
        <select>
            <option selected>F</option>
            <option>T</option>
        </select>
        <select>
            <option selected>P</option>
            <option>J</option>
        </select>
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