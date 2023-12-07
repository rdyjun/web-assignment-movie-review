package com.dongyang.moviewreviewweb.moviereviewer.review.mapper;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper {
    public static Review mapToEntity (ResultSet rs) {
        Review review = null;
        try {
            review = new Review(rs.getLong("id"),
                    rs.getFloat("rating"),
                    rs.getString("comment"),
                    rs.getString("author"),
                    rs.getTimestamp("writeTime"),
                    rs.getString("movieId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }
}
