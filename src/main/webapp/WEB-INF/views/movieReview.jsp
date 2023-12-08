<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/movieReview.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/wrap.css">
    <link href='https://fonts.googleapis.com/css?family=Preahvihear' rel='stylesheet'>
    <script src="../resources/script/moviereview.js" defer="defer"></script>
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
            <li id="category">${String.join(", ", movie.category)}</li>
            <li id="overView">${movie.overView}</li>
            <iframe src="${movie.videosLink}" id="videos" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
        </ul>
    </div>
    <div id="reviews">
        <div id="reviewTitleBox">
            <p id="reviewTitle">리뷰</p>
            <button id="reviewWrite" onclick="onWriteBox('${sessionScope.get('userId')}')">작성</button>
        </div>
        <form id="reviewWriteBox" method="post" action="/write-review" style="display:none">
            <input id="rating" name="rating" type="number" value="0" style="display:none">
            <input name="movieId" value="${movie.id}" style="display:none">
            <div class="rating-stars">
                <c:forEach var="i" begin="1" end="5" step="1" varStatus="status">
                <svg class="writeStar" onclick="selectRating(this)" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                    <path d="M12 2.5l2.405 7.385h7.795l-6.305 4.573 2.405 7.387-6.305-4.572-6.305 4.572 2.405-7.387-6.305-4.573h7.795z"/>
                </svg>
                </c:forEach>
            </div>
            <textarea id="reviewTextArea" placeholder="이곳에 리뷰를 작성해주세요." maxlength="50" value="" name="reviewComment"></textarea>
            <div id="saveOrCancel">
                <button id="cancelButton" onclick="cancel()" type="button">취소</button>
                <button id="saveButton" type="submit">저장</button>
            </div>
        </form>
        <p id="reviewNotFound" style="display: ${reviews.size() == 0 ? "block" : "none"}">작성된 리뷰가 없습니다.</p>
        <div id="reviewList">
            <c:forEach var="review" items="${reviews}" varStatus="sts">
                <div class="movieBox">
                    <p class="reviewAuthor">${reviewName.get(sts.index)}</p>
                    <div class="stars">
                        <c:forEach var="i" begin="1" end="5" step="1" varStatus="status">
                        <svg class="reviewRating" fill="${review.rating >= i ? "#9664FF" : "#888888"}" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20">
                            <path d="M12 2.5l2.405 7.385h7.795l-6.305 4.573 2.405 7.387-6.305-4.572-6.305 4.572 2.405-7.387-6.305-4.573h7.795z"/>
                        </svg>
                        </c:forEach>
                    </div>

                    <p class="comment">${review.comment}</p>
                    <p class="date">${review.writeTime}</p>
                    <form class="likeButtonBox" method="post" action="/like-review" onsubmit="return validateForm('${sessionScope.get('userId')}')">
                        <input name="movieId" value="${movie.id}" type="hidden">
                        <input name="reviewId" value="${review.id}" type="hidden">
                        <button type="submit" >
                        <span class="material-icons" style="color:${like.contains(review.id) ? "#9664FF" : "#888"}">
                            thumb_up
                        </span>
                        <p class="likeCount">${likeCount.getOrDefault(review.id, 0)}</p>
                        </button>
                    </form>
                    <div class="reviewMenuBox">
                        <span class="material-icons" onclick="showMenu(this)">
                            more_vert
                        </span>
                        <div class="reviewMenu">
                            <form class="reportReview" method="post" action="/report-review" onsubmit="return validateForm('${sessionScope.get('userId')}')">
                                <input name="reporter" type="hidden" value="${sessionScope.get("userId")}">
                                <input name="reviewId" type="hidden" value="${review.id}">
                                <button type="submit">신고</button>
                            </form>
                            <form style="display: ${sessionScope.get("userId").equals(review.author) ? "block" : "none"}" class="removeReview" action="/delete-review" method="post" onsubmit="return validateForm('${sessionScope.get('userId')}')">
                                <input name="author" type="hidden" value="${sessionScope.get("userId")}">
                                <input name="reviewId" type="hidden" value="${review.id}">
                                <button type="submit">삭제</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>