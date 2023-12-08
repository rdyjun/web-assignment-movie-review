package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.MovieList;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<Review> getReviewByMovie (String movieId);
    void saveReview (String movieId, String memberId, String comment, float rating);

    Map<String, Double> getMovieVote(List<MovieList> movieList);

    List<Review> getReviewByMemberId(String memberId);

    void deleteReview(long reviewId);
}
