package com.dongyang.moviewreviewweb.moviereviewer.dbconnector;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBConnector {
    private final Environment env;
    @Autowired
    public DBConnector (Environment env) {
        this.env = env;
    }
    public Connection getConnect() {
        String dbDriver = env.getProperty("database.driver");
        String dbUrl = env.getProperty("database.url");
        String userName = env.getProperty("database.username");
        String password = env.getProperty("database.password");
        try {
            Class.forName(dbDriver);
            return DriverManager.getConnection(dbUrl, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
