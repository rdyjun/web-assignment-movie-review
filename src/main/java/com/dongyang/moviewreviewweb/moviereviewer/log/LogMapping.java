package com.dongyang.moviewreviewweb.moviereviewer.log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LogMapping {
    public static Log mapToLog (ResultSet rs) {
        Log log = null;
        try {
            Timestamp dt = rs.getTimestamp("logDate");
            String content = rs.getString("content");
            String author = rs.getString("author");
            log = new Log(dt, content, author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return log;
    }
}
