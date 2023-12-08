package com.dongyang.moviewreviewweb.moviereviewer.review.controller;

import com.dongyang.moviewreviewweb.moviereviewer.log.Log;
import com.dongyang.moviewreviewweb.moviereviewer.log.LogDAO;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReportService;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewLikeService;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewLikeServiceImpl;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewLikeService reviewLikeService;
    private final ReportService reportService;
    private final LogDAO logDAO;
    @PostMapping("/write-review")
    public String writeReview (HttpSession session, float rating, String reviewComment, String movieId) {
        String memberId = (String) session.getAttribute("userId");
        reviewService.saveReview(movieId, memberId, reviewComment, rating);
        Log log = new Log("새로운 리뷰 작성 : 영화 ID = " + movieId, memberId);
        logDAO.create(log);
        return "redirect:/movies/" + movieId;
    }
    @PostMapping("/like-review")
    public String likeReview (HttpSession session, String movieId, long reviewId) {
        String memberId = (String)session.getAttribute("userId");
        if (memberId == null)
            return "redirect:/movies/" + movieId;
        reviewLikeService.updateReviewLike(memberId, movieId, reviewId);
        Log log = new Log("리뷰 좋아요: 좋아요한 리뷰 ID = " + reviewId, memberId);
        logDAO.create(log);
        return "redirect:/movies/" + movieId;
    }
    @PostMapping("/delete-review")
    public String deleteReview (HttpServletRequest request, HttpSession session, String author, long reviewId) {
        String referer = request.getHeader("Referer");
        if (!session.getAttribute("userId").equals(author))
            return returnPage(referer);
        reportService.removeReport(reviewId);
        reviewLikeService.removeLike(reviewId);
        reviewService.deleteReview(reviewId);
        Log log = new Log("리뷰 삭제: 삭제 리뷰 ID = " + reviewId, author);
        logDAO.create(log);
        return returnPage(referer);
    }
    @PostMapping("/report-review")
    public String reportReview (HttpServletRequest request, HttpSession session, String reporter, long reviewId) {
        String referer = request.getHeader("Referer");
        if (session.getAttribute("userId") == null)
            return returnPage(referer);
        reportService.reportReview(reporter, reviewId);
        Log log = new Log("회원 신고: 신고 리뷰 ID = " + reviewId, reporter);
        logDAO.create(log);
        return returnPage(referer);
    }
    public String returnPage (String referer) {
        if (referer == null || referer.equals("/login") || referer.equals("/register"))
            return "redirect:/";
        return "redirect:" + referer;
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleException(Exception e) {
        return e.getMessage() + "\n 페이지를 뒤로 이동해주세요";
    }
}
