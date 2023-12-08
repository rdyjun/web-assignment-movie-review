package com.dongyang.moviewreviewweb.moviereviewer.review.repository;

import com.dongyang.moviewreviewweb.moviereviewer.dbconnector.DBConnector;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReviewLike;
import com.dongyang.moviewreviewweb.moviereviewer.review.mapper.ReviewLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class ReviewLikeDAO implements ReviewLikeRepository {
    private final DBConnector dbConnector;
    @Override
    public List<ReviewLike> findByMovieId(String movieId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        List<ReviewLike> reviewLikeList = new ArrayList<>();
        String sql = "SELECT * FROM reviewlike WHERE movie_id = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, movieId);
            rs = pstmt.executeQuery();
            while(rs.next())
                reviewLikeList.add(ReviewLikeMapper.mapToEntity(rs));
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewLikeList;
    }
    @Override
    public List<ReviewLike> findByMovieIdAndMemberId(String movieId, String memberId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        List<ReviewLike> reviewLikeList = new ArrayList<>();
        String sql = "SELECT * FROM reviewlike WHERE movie_id = ? AND member_id = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, movieId);
            pstmt.setString(2, memberId);
            rs = pstmt.executeQuery();
            while(rs.next())
                reviewLikeList.add(ReviewLikeMapper.mapToEntity(rs));
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewLikeList;
    }
    @Override
    public Optional<ReviewLike> findByMemberIdAndMovieIdAndReviewId(String memberId, String movieId, long reviewId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        Optional<ReviewLike> reviewLikeList = Optional.empty();
        String sql = "SELECT * FROM reviewlike WHERE member_id = ? and movie_id = ? and review_id = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.setString(2, movieId);
            pstmt.setLong(3, reviewId);
            rs = pstmt.executeQuery();
            if (rs.next())
                reviewLikeList = Optional.ofNullable(ReviewLikeMapper.mapToEntity(rs));
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewLikeList;
    }
    @Override
    public void save(String memberId, String movieId, long reviewId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "INSERT INTO reviewlike (member_id, movie_id, review_id) VALUES (?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.setString(2, movieId);
            pstmt.setLong(3, reviewId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void remove(String memberId, String movieId, long reviewId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "DELETE FROM reviewlike WHERE member_id = ? and movie_id = ? and review_id = ?;";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.setString(2, movieId);
            pstmt.setLong(3, reviewId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Map<Long, Integer> countByMovieIdAndGroupByReviewId(String movieId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        Map<Long, Integer> reviewLikeCountList = new HashMap<>();
        String sql = "SELECT review_id, COUNT(*) as count_like FROM reviewlike WHERE movie_id = ? GROUP BY review_id";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, movieId);
            rs = pstmt.executeQuery();
            while (rs.next())
                reviewLikeCountList.put(rs.getLong("review_id"), rs.getInt("count_like"));
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewLikeCountList;
    }
    @Override
    public void removeById(long reviewId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "DELETE FROM reviewlike WHERE review_id = ?;";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, reviewId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int findByReviewId(long reviewId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        int likeCount = 0;
        String sql = "SELECT COUNT(*) as count_like FROM reviewlike WHERE review_id = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, reviewId);
            rs = pstmt.executeQuery();
            if (rs.next())
                likeCount = rs.getInt("count_like");
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likeCount;
    }
    @Override
    public List<ReviewLike> findByMemberId(String memberId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        List<ReviewLike> likeList = new ArrayList<>();
        String sql = "SELECT * FROM reviewlike WHERE member_id = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            while (rs.next())
                likeList.add(ReviewLikeMapper.mapToEntity(rs));
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likeList;
    }
    @Override
    public void removeByMemberId (String memberId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "DELETE FROM reviewlike WHERE member_id = ?;";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
