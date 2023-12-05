package com.dongyang.moviewreviewweb.moviereviewer.review.entity;

import com.dongyang.moviewreviewweb.moviereviewer.dbconnector.DBConnector;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Member;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.MemberMapper;
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
public class ReportDAO {
    private final DBConnector dbConnector;
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
}
