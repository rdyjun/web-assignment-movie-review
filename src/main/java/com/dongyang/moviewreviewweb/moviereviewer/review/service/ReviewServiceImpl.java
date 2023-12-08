package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.MovieList;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;
import com.dongyang.moviewreviewweb.moviereviewer.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Double> getMovieVote(List<MovieList> movieList) {
        Map<String, Double> movieVoteList = new HashMap<>();
        for (MovieList movie : movieList)
            movieVoteList.put(String.valueOf(movie.getId()), reviewRepository.averageByMovieId(String.valueOf(movie.getId())));

        return movieVoteList;
    }
    @Override
    public List<Review> getReviewByMemberId(String memberId) {
        return reviewRepository.findByMemberId(memberId);
    }
    @Override
    public void deleteReview (long reviewId) {
        reviewRepository.removeById(reviewId);
    }
}
