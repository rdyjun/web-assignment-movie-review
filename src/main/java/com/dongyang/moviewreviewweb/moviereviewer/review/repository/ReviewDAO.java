package com.dongyang.moviewreviewweb.moviereviewer.review.repository;

import com.dongyang.moviewreviewweb.moviereviewer.dbconnector.DBConnector;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Member;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.MemberMapper;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewDAO {
    @Autowired
    private DBConnector dbConnector;
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
}
