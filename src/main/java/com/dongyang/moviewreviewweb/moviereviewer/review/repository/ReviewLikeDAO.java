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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewLikeDAO {
    private final DBConnector dbConnector;
    public List<ReviewLike> findByMovieId (String movieId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        List<ReviewLike> reviewLikeList = new ArrayList<>();
        String sql = "SELECT * FROM reviewlike WHERE movieId = ?";
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
    public List<ReviewLike> findByMovieIdAndMemberId (String movieId, String memberId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        List<ReviewLike> reviewLikeList = new ArrayList<>();
        String sql = "SELECT * FROM reviewlike WHERE movieId = ? AND memberId = ?";
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
    public Optional<ReviewLike> findByMemberIdAndMovieIdAndReviewId (String memberId, String movieId, long reviewId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        Optional<ReviewLike> reviewLikeList = Optional.empty();
        String sql = "SELECT * FROM reviewlike WHERE memberId = ? and movieId = ? and reviewId = ?";
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
    public void save (String memberId, String movieId, long reviewId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "INSERT INTO reviewlike (memberId, movieId, reviewId) VALUES (?, ?, ?)";
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
    public void remove (String memberId, String movieId, long reviewId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "DELETE FROM reviewlike WHERE memberId = ? and movieId = ? and reviewId = ?;";
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
}
