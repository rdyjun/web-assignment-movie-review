package com.dongyang.moviewreviewweb.moviereviewer.DBConnector;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection connect;
    @Value("${database.driver}")
    private String dbDriver;
    @Value("${database.username}")
    private String userName;
    @Value("${database.password}")
    private String password;
    public DBConnector () throws ClassNotFoundException, SQLException {
        Class.forName(dbDriver);
        this.connect = DriverManager.getConnection(this.dbDriver, this.userName, this.password);
    }
    public Connection getconnector () {
        return this.connect;
    }
}
