package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;
import com.dongyang.moviewreviewweb.moviereviewer.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    @Override
    public List<Review> getReviewByMovie (String movieId) {
        return reviewRepository.findByMovieId(movieId);
    }
    @Override
    public void saveReview (String movieId, String userId, String comment, float rating) {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        Review review = new Review(rating, comment, userId, date, movieId);
        reviewRepository.save(review);
    }
}
