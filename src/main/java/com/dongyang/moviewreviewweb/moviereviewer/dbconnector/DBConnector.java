package com.dongyang.moviewreviewweb.moviereviewer.dbconnector;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    @Value("${database.driver}")
    private static String dbDriver;
    @Value("${database.username}")
    private static String userName;
    @Value("${database.password}")
    private static String password;
    public static Connection getConnect () throws ClassNotFoundException, SQLException {
        Class.forName(dbDriver);
        return DriverManager.getConnection(dbDriver, userName, password);
    }
}
