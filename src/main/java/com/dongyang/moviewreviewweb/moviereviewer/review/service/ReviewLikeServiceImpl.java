package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReviewLike;
import com.dongyang.moviewreviewweb.moviereviewer.review.repository.ReviewLikeDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReviewLikeServiceImpl {
    private final ReviewLikeDAO reviewLikeDAO;
    public Set<Long> getReviewLikeListByMovieIdAndMemberId (String movieId, String memberId) {
        List<ReviewLike> reviewLikeList = reviewLikeDAO.findByMovieIdAndMemberId(movieId, memberId);
        Set<Long> likeId = new HashSet<>();
        for (ReviewLike reviewLike : reviewLikeList)
            likeId.add(reviewLike.getReviewId());
        return likeId;
    }
    public void updateReviewLike (String memberId, String movieId, long reviewId) {
        if (isAlreadyLike(memberId, movieId, reviewId)) {
            reviewLikeDAO.remove(memberId, movieId, reviewId);
            return;
        }
        reviewLikeDAO.save(memberId, movieId, reviewId);
    }
    public boolean isAlreadyLike (String memberId, String movieId, long reviewId) {
        return reviewLikeDAO.findByMemberIdAndMovieIdAndReviewId(memberId, movieId, reviewId).isPresent();
    }
}
