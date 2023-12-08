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
                title: "${movie.title}",
                category: "${movie.category}",
                runtime: "${movie.runtime}",
                releaseDate: "${movie.releaseDate}",
                overView: "${movie.overView}",
                voteAverage: "${movie.vote_average}",
                voteCount: "${movie.vote_count}",
                poster: "${movie.posterLink}"
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
        <p id="movieNameKor" class="movieName">${movies.get(0).title}</p>
        <div id="movieData">
            <p id="genre">장르 : ${movies.get(0).category}</p>
            <p id="release">개봉일 : ${movies.get(0).releaseDate}</p>
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
                <p id="voteInner" class="simpleNumber">

                </p>
                <p class="simpleName">
                    평점
                </p>
            </div>
            <div id="simpleLike">
                <p id="voteOuter" class="simpleNumber">
                    ${movies.get(0).vote_average}
                </p>
                <p class="simpleName">
                    외부평점
                </p>
            </div>
        </div>
        <div id="movieTrailerContent">
            <p id="movieTrailerTitle">Movie Trailer</p>
            <iframe id="trailer" width="320" height="180" src="https://www.youtube.com/embed/-AZ7cnwn2YI?si=hqTEmbEOItH6AhqP" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
        </div>
    </div>
    <div id="moviePosters">
        <div>
            <img id="first" class="moviePosterContainer animate-focus" src="${movies.get(0).posterLink}">
        </div>
        <div>
            <img id="second" class="moviePosterContainer animate-first" src="${movies.get(1).posterLink}">
        </div>
        <div>
            <img id="third" class="moviePosterContainer animate-second" src="${movies.get(2).posterLink}">
        </div>
    </div>
</body>
</html>