<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/home.css">
    <link rel="stylesheet" type="text/css" href="../resources/wrap.css">
</head>
<body>
    <div id="movieInfo">
        <p id="movieCategory">Best & Popular<br>Movie</p>
        <p id="movieNameKor" class="movieName">서울의 봄</p>
        <p id="movieNameEng" class="movieName">Spring in Seoul</p>
        <div id="movieData">
            <p>감독 : </p>
            <p>장르 : </p>
            <p>등급 : </p>
        </div>
        <div id="otherMovies">
            <div id="nextMovie">
                <img class="nextMovie" src="https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202311/20443_101_1.jpg">
            </div>
            <div id="nextOfNextMovie">
                <img class="nextMovie" src="https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202311/20443_101_1.jpg">
            </div>
        </div>
        <div id="simpleReview">
            <div id="watcher">
                <p class="simpleNumber">
                    13.6k
                </p>
                <p class="simpleName">
                    관객 수
                </p>
            </div>
            <div id="simpleShare">
                <p class="simpleNumber">
                    12.2k
                </p>
                <p class="simpleName">
                    공유
                </p>
            </div>
            <div id="simpleLike">
                <p class="simpleNumber">
                    20.4k
                </p>
                <p class="simpleName">
                    좋아요
                </p>
            </div>
        </div>
        <div id="movieTrailerContent">
            <p id="movieTrailerTitle">Movie Trailer</p>
            <iframe id="trailer" width="320" height="180" src="https://www.youtube.com/embed/-AZ7cnwn2YI?si=hqTEmbEOItH6AhqP" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
        </div>
    </div>
    <div id="bigMoviePoster">
        <div id="gradientDiv">
            <img id="bigPoster" src="https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202311/20443_101_1.jpg">
        </div>
    </div>
</body>
</html>