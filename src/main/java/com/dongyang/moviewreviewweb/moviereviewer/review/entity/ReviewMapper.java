package com.dongyang.moviewreviewweb.moviereviewer.review.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper {
    public static Review mapToEntity (ResultSet rs) {
        Review review = null;
        try {
            review = new Review(rs.getFloat("rating"),
                    rs.getString("comment"),
                    rs.getString("author"),
                    rs.getTimestamp("report_time"),
                    rs.getString("movieId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }
}
