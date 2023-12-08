package com.dongyang.moviewreviewweb.moviereviewer.review.repository;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    void save(Review review);

    void removeByMemberId(String memberId);

    Optional<Review> findById(String id);

    List<Review> findByMovieId(String movieId);

    List<Review> findByMemberIdAndMovieId(String memberId, String movieId);

    Double averageByMovieId(String movieId);

    List<Review> findByMemberId(String memberId);

    void removeById(long reviewId);
}
