package com.dongyang.moviewreviewweb.moviereviewer.review.service;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReviewLike;
import com.dongyang.moviewreviewweb.moviereviewer.review.repository.ReviewLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReviewLikeServiceImpl implements ReviewLikeService {
    private final ReviewLikeRepository reviewLikeRepository;
    @Override
    public Set<Long> getReviewLikeListByMovieIdAndMemberId(String movieId, String memberId) {
        List<ReviewLike> reviewLikeList = reviewLikeRepository.findByMovieIdAndMemberId(movieId, memberId);
        Set<Long> likeId = new HashSet<>();
        for (ReviewLike reviewLike : reviewLikeList)
            likeId.add(reviewLike.getReviewId());
        return likeId;
    }
    @Override
    public void updateReviewLike(String memberId, String movieId, long reviewId) {
        if (isAlreadyLike(memberId, movieId, reviewId)) {
            reviewLikeRepository.remove(memberId, movieId, reviewId);
            return;
        }
        reviewLikeRepository.save(memberId, movieId, reviewId);
    }
    @Override
    public boolean isAlreadyLike(String memberId, String movieId, long reviewId) {
        return reviewLikeRepository.findByMemberIdAndMovieIdAndReviewId(memberId, movieId, reviewId).isPresent();
    }
    @Override
    public Map<Long, Integer> getLikeCount(String movieId) {
        return reviewLikeRepository.countByMovieIdAndGroupByReviewId(movieId);
    }
    @Override
    public void removeLike(long reviewId) {
        reviewLikeRepository.removeById(reviewId);
    }
    @Override
    public int getReviewLikeByReviewId(long reviewId) {
        return reviewLikeRepository.findByReviewId(reviewId);
    }
    @Override
    public List<ReviewLike> getReviewLikeByMemberId (String memberId) {
        return reviewLikeRepository.findByMemberId(memberId);
    }
}
