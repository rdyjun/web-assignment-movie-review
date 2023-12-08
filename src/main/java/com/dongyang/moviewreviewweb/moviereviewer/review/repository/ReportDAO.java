package com.dongyang.moviewreviewweb.moviereviewer.review.repository;

import com.dongyang.moviewreviewweb.moviereviewer.dbconnector.DBConnector;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Report;
import com.dongyang.moviewreviewweb.moviereviewer.review.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReportDAO implements ReportRepository {
    private final DBConnector dbConnector;
    @Override
    public List<Report> findAll () {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        List<Report> reportList = new ArrayList<>();
        String sql = "SELECT * FROM report";
        try {
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                reportList.add(ReportMapper.mapToEntity(rs));
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportList;
    }

    @Override
    public void save(String reporter, long reviewId, Timestamp reportTime) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "INSERT INTO report(reporter, reviewId, report_time) VALUES (?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, reporter);
            pstmt.setLong(2, reviewId);
            pstmt.setTimestamp(3, reportTime);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Optional<Report> findByReporterIdAndReviewId(String reporterId, long reviewId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        Optional<Report> report = Optional.empty();
        String sql = "SELECT * FROM report WHERE reporter = ? AND reviewId = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, reporterId);
            pstmt.setLong(2, reviewId);
            rs = pstmt.executeQuery();
            if (rs.next())
                report = Optional.ofNullable(ReportMapper.mapToEntity(rs));
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }
    @Override
    public void removeByReviewId(long reviewId) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "DELETE FROM report WHERE reviewId = ?;";
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
