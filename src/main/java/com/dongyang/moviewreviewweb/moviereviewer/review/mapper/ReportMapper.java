package com.dongyang.moviewreviewweb.moviereviewer.review.mapper;

import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Report;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMapper {
    public static Report mapToEntity (ResultSet rs) {
        Report report = null;
        try {
            report = new Report(rs.getString("reporter"),
                    rs.getString("movieId"),
                    rs.getString("reviewid"),
                    rs.getTimestamp("reportTime"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }
}
