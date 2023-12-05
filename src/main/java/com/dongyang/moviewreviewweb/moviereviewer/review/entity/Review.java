package com.dongyang.moviewreviewweb.moviereviewer.review.entity;

import java.sql.Timestamp;

public class Review {
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

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getAuthor() {
        return author;
    }

    public Timestamp getWriteTime() {
        return writeTime;
    }

    public String getMovieId() {
        return movieId;
    }
}
