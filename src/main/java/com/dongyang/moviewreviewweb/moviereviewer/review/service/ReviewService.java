package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewByMovie (String movieId);
}
