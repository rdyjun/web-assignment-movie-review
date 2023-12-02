package com.dongyang.moviewreviewweb.moviereviewer.dbconnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBConnector {
    private static Environment env;
    @Autowired
    public DBConnector(Environment env) {
        DBConnector.env = env;
    }
    public static Connection getConnect() throws ClassNotFoundException, SQLException {
        String dbDriver = env.getProperty("database.driver");
        String dbUrl = env.getProperty("database.url");
        String userName = env.getProperty("database.username");
        String password = env.getProperty("database.password");

        Class.forName(dbDriver);
        return DriverManager.getConnection(dbUrl, userName, password);
    }
}