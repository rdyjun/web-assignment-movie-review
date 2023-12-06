package com.dongyang.moviewreviewweb.moviereviewer.review.entity;

public class ReportFace {
    private String target;
    private String comment;

    public ReportFace(String target, String comment) {
        this.target = target;
        this.comment = comment;
    }

    public String getTarget() {
        return target;
    }

    public String getComment() {
        return comment;
    }
}
