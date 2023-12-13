<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/home.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/wrap.css">
    <script src="../resources/script/home.js" defer="defer"></script>
    <script>
        var movieData = [
            <c:forEach var="movie" items="${movies}" varStatus="status">
            {
                id: "${movie.id}",
                title: "${movie.title}",
                category: "${movie.category}",
                runtime: "${movie.runtime}",
                releaseDate: "${movie.releaseDate}",
                overView: "${movie.overView}",
                voteAverage: "${movie.vote_average}",
                voteCount: "${movie.vote_count}",
                poster: "${movie.posterLink}",
                videosLink: "${movie.videosLink}"
            },
            <c:if test="${status.last}">,</c:if>
            </c:forEach>
        ];
    </script>
</head>
<body>
    <div id="bg">

    </div>
    <div id="movieInfo">
        <p id="movieCategory">Best & Popular<br>Movie</p>
        <span id="movieNameKor" class="movieName">${movies.get(0).title}</span>
        <div id="movieData">
            <p id="genre">장르 : ${movies.get(0).category}</p>
            <p id="overView">내용 : ${movies.get(0).overView}</p>
        </div>
        <div id="simpleReview">
            <div id="watcher">
                <p id="runtime" class="simpleNumber">
                    ${movies.get(0).runtime}분
                </p>
                <p class="simpleName">
                    런타임
                </p>
            </div>
            <div id="simpleShare">
                <p id="release" class="simpleNumber">
                    ${movies.get(0).releaseDate}
                </p>
                <p class="simpleName">
                    개봉일
                </p>
            </div>
            <div id="simpleLike">
                <p id="voteOuter" class="simpleNumber">
                    ${String.valueOf(movies.get(0).vote_average).substring(0, 3)}
                </p>
                <p class="simpleName">
                    외부평점
                </p>
            </div>
        </div>
        <div id="movieTrailerContent">
            <p id="movieTrailerTitle">Movie Trailer</p>
            <iframe id="trailer" width="320" height="180" src="${movies.get(0).videosLink}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
        </div>
    </div>
    <div id="moviePosters">
        <div onclick="location.href = '/movies/${movies.get(0).id}'">
            <img id="first" class="moviePosterContainer animate-focus" src="${movies.get(0).posterLink}">
        </div>
        <div onclick="location.href = '/movies/${movies.get(1).id}'">
            <img id="second" class="moviePosterContainer animate-first" src="${movies.get(1).posterLink}">
        </div>
        <div onclick="location.href = '/movies/${movies.get(2).id}'">
            <img id="third" class="moviePosterContainer animate-second" src="${movies.get(2).posterLink}">
        </div>
    </div>
</body>
</html>