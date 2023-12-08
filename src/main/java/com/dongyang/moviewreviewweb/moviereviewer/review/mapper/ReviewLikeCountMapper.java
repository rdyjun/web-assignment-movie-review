package com.dongyang.moviewreviewweb.moviereviewer.review.mapper;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReviewLikeCount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewLikeCountMapper {
    public static ReviewLikeCount mapToEntity (ResultSet rs) {
        ReviewLikeCount reviewLikeCount = null;
        try {
            reviewLikeCount = new ReviewLikeCount(rs.getLong("review_id"),
                    rs.getInt("count_like"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewLikeCount;
    }
}
