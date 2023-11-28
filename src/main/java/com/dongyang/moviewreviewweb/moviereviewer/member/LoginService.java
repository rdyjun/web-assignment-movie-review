package com.dongyang.moviewreviewweb.moviereviewer.member;

import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;

public interface LoginService {
    void login (LoginDTO loginData, HttpSession httpSession) throws SQLException, ClassNotFoundException;
}
