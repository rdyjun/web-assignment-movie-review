<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/movieReview.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/mypage.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/wrap.css">
    <link href='https://fonts.googleapis.com/css?family=Preahvihear' rel='stylesheet'>
    <script src="../resources/script/moviereview.js" defer="defer"></script>
    <script src="../resources/script/mypage.js" defer="defer"></script>
</head>
<body>
<header>
    <%@ include file="header.jsp" %>
</header>
<div id="content">
    <div id="top">
        <button onclick="">
            <span id="modifyName" class="material-icons" onclick="openModify()">
                draw
            </span>
        </button>
        <form id="modifyForm" action="/modify-name" method="post">
            <input id="modifyInput" type="text" name="newName">
            <button id="cancelButton" type="button" onclick="closeModify()">취소</button>
            <button id="saveButton" type="submit">저장</button>
        </form>

        <p id="movieTitle">${member.name}</p>
        <form method="post" action="/logout">
            <input type="submit" id="logout" value="로그아웃">
        </form>
    </div>
    <div id="reviews">
        <div id="reviewTitleBox">
            <p id="reviewTitle">작성한 리뷰</p>
        </div>
        <p id="reviewNotFound" style="display: ${reviews.size() == 0 ? "block" : "none"}">작성한 리뷰가 없습니다.</p>
        <div id="reviewList">
            <c:forEach var="review" items="${reviews}" varStatus="sts">
                <div class="movieBox" onclick="location.href='/movies/${review.movieId}'" style="cursor: pointer">
                    <p class="reviewAuthor">${reviewMovieTitle.get(sts.index)}</p>
                    <div class="stars">
                        <c:forEach var="i" begin="1" end="5" step="1" varStatus="status">
                            <svg class="reviewRating" fill="${review.rating >= i ? "#9664FF" : "#888888"}" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20">
                                <path d="M12 2.5l2.405 7.385h7.795l-6.305 4.573 2.405 7.387-6.305-4.572-6.305 4.572 2.405-7.387-6.305-4.573h7.795z"/>
                            </svg>
                        </c:forEach>
                    </div>
                    <p class="comment">${review.comment}</p>
                    <p class="date">${review.writeTime}</p>
                    <form class="likeButtonBox" method="post" action="/like-review" onsubmit="return notInMyPage()">
                        <input name="movieId" value="../mypage" type="hidden">
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