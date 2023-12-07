package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import java.util.Map;
import java.util.Set;

public interface ReviewLikeService {
    Set<Long> getReviewLikeListByMovieIdAndMemberId(String movieId, String memberId);

    void updateReviewLike(String memberId, String movieId, long reviewId);

    boolean isAlreadyLike(String memberId, String movieId, long reviewId);

    Map<Long, Integer> getLikeCount(String movieId);
}
