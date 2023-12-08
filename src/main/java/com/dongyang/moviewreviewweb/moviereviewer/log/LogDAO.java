package com.dongyang.moviewreviewweb.moviereviewer.log;

import com.dongyang.moviewreviewweb.moviereviewer.dbconnector.DBConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LogDAO implements LogRepository {
    private final DBConnector dbConnector;
    @Override
    public boolean create(Log log) {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        String sql = "INSERT INTO log (log_date, content, author) VALUES (?, ?, ?)";
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setTimestamp(1, timestamp);
            pstmt.setString(2, log.getContent());
            pstmt.setString(3, log.getAuthor());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    @Override
    public List<Log> getAllLog () {
        Connection connection = dbConnector.getConnect();
        PreparedStatement pstmt;
        ResultSet rs;
        List<Log> logList = new ArrayList<>();
        String sql = "SELECT * FROM log";
        try {
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Log log = LogMapping.mapToLog(rs);
                logList.add(log);
            }

            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logList;
    }
}
