package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReviewLike;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ReviewLikeService {
    Set<Long> getReviewLikeListByMovieIdAndMemberId(String movieId, String memberId);

    void updateReviewLike(String memberId, String movieId, long reviewId);

    boolean isAlreadyLike(String memberId, String movieId, long reviewId);

    Map<Long, Integer> getLikeCount(String movieId);

    void removeLike(long reviewId);

    int getReviewLikeByReviewId(long reviewId);

    List<ReviewLike> getReviewLikeByMemberId(String memberId);
}
