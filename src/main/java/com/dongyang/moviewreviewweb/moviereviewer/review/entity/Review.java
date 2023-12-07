package com.dongyang.moviewreviewweb.moviereviewer.review.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Review {
    private long id;
    private float rating;
    private String comment;
    private String author;
    private Timestamp writeTime;
    private String movieId;

    public Review(float rating, String comment, String author, Timestamp writeTime, String movieId) {
        this.rating = rating;
        this.comment = comment;
        this.author = author;
        this.writeTime = writeTime;
        this.movieId = movieId;
    }
}
