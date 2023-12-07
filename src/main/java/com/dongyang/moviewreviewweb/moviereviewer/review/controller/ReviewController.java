package com.dongyang.moviewreviewweb.moviereviewer.review.controller;

import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewLikeServiceImpl;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewLikeServiceImpl reviewLikeService;
    @PostMapping("/write-review")
    public String writeReview (HttpSession session, float rating, String reviewComment, String movieId) {
        String userId = (String) session.getAttribute("userId");
        reviewService.saveReview(movieId, userId, reviewComment, rating);
        return "redirect:/movies/" + movieId;
    }
    @PostMapping("/like-review")
    public String likeReview (HttpSession session, String movieId, long reviewId) {
        String memberId = (String)session.getAttribute("userId");
        if (memberId == null)
            return "redirect:/movies/" + movieId;
        reviewLikeService.updateReviewLike(memberId, movieId, reviewId);
        return "redirect:/movies/" + movieId;
    }
}
