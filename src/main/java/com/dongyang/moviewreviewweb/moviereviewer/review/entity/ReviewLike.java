package com.dongyang.moviewreviewweb.moviereviewer.review.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewLike {
    private long id;
    private String memberId;
    private long reviewId;
    private String movieId;
}
