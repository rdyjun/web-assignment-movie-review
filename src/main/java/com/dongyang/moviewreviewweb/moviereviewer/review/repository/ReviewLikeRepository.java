package com.dongyang.moviewreviewweb.moviereviewer.review.repository;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReviewLike;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReviewLikeRepository {
    List<ReviewLike> findByMovieId(String movieId);

    List<ReviewLike> findByMovieIdAndMemberId(String movieId, String memberId);

    Optional<ReviewLike> findByMemberIdAndMovieIdAndReviewId(String memberId, String movieId, long reviewId);

    void save(String memberId, String movieId, long reviewId);

    void remove(String memberId, String movieId, long reviewId);

    Map<Long, Integer> countByMovieIdAndGroupByReviewId(String movieId);

    void removeById(long reviewId);

    int findByReviewId(long reviewId);

    List<ReviewLike> findByMemberId(String memberId);

    void removeByMemberId(String memberId);
}
