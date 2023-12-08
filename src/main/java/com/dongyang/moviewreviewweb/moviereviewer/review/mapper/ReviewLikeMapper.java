package com.dongyang.moviewreviewweb.moviereviewer.review.mapper;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReviewLike;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewLikeMapper {
    public static ReviewLike mapToEntity (ResultSet rs) {
        ReviewLike reviewLike = null;
        try {
            reviewLike = new ReviewLike(rs.getLong("id"),
                    rs.getString("member_id"),
                    rs.getLong("review_id"),
                    rs.getString("movie_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewLike;
    }
}
