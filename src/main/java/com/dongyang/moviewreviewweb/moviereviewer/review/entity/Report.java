package com.dongyang.moviewreviewweb.moviereviewer.review.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Report {
    private String reporter;
    private String reviewId;
    private Timestamp reportTime;
}
