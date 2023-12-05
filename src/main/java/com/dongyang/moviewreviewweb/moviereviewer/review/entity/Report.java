package com.dongyang.moviewreviewweb.moviereviewer.review.entity;

import java.sql.Timestamp;

public class Report {
    private String reporter;
    private String movieId;
    private String reviewId;
    private Timestamp reportTime;
    public Report(String reporter, String movieId, String reviewId, Timestamp reportTime) {
        this.reporter = reporter;
        this.movieId = movieId;
        this.reviewId = reviewId;
        this.reportTime = reportTime;
    }

    public String getReporter() {
        return reporter;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getReviewId() {
        return reviewId;
    }
    public Timestamp getReportTime () {
        return reportTime;
    }
}
