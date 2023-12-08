package com.dongyang.moviewreviewweb.moviereviewer.review.repository;

import com.dongyang.moviewreviewweb.moviereviewer.dbconnector.DBConnector;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;
import com.dongyang.moviewreviewweb.moviereviewer.review.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class ReviewDAO implements ReviewRepository {
    @Autowired
    private DBConnector dbConnector;
    @Override
    public void save (Review review) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "INSERT INTO review(writeTime, author, movieId, comment, rating) VALUES (?, ?, ?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setTimestamp(1, review.getWriteTime());
            pstmt.setString(2, review.getAuthor());
            pstmt.setString(3, review.getMovieId());
            pstmt.setString(4, review.getComment());
            pstmt.setFloat(5, review.getRating());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void removeByMemberId (String memberId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "DELETE FROM review WHERE author = ?;";
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
    @Override
    public Optional<Review> findById (String id) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        Optional<Review> review = null;
        String sql = "SELECT * FROM review WHERE id = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                review = Optional.ofNullable(ReviewMapper.mapToEntity(rs));
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (review == null)
            return Optional.empty();
        return review;
    }
    @Override
    public List<Review> findByMovieId (String movieId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        List<Review> review = new ArrayList<>();
        String sql = "SELECT * FROM review WHERE movieId = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, movieId);
            rs = pstmt.executeQuery();
            while (rs.next())
                review.add(ReviewMapper.mapToEntity(rs));
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }
    @Override
    public List<Review> findByMemberIdAndMovieId (String memberId, String movieId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        List<Review> review = new ArrayList<>();
        String sql = "SELECT * FROM review WHERE author = ? and movieId = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.setString(2, movieId);
            rs = pstmt.executeQuery();
            while (rs.next())
                review.add(ReviewMapper.mapToEntity(rs));
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }
    @Override
    public Double averageByMovieId(String movieId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        Double reviewVoteAverage = null;
        String sql = "SELECT movieId, AVG(rating) as rating FROM review WHERE movieId = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, movieId);
            rs = pstmt.executeQuery();
            if (rs.next())
                reviewVoteAverage = (double) rs.getFloat("rating");
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewVoteAverage;
    }
    @Override
    public List<Review> findByMemberId(String memberId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        List<Review> review = new ArrayList<>();
        String sql = "SELECT * FROM review WHERE author = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            while (rs.next())
                review.add(ReviewMapper.mapToEntity(rs));
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }
    @Override
    public void removeById (long reviewId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "DELETE FROM review WHERE id = ?;";
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
}
