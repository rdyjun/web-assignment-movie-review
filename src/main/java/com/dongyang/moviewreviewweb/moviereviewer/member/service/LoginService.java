package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.LoginDTO;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;

public interface LoginService {
    void login (LoginDTO loginData, HttpSession httpSession) throws SQLException, ClassNotFoundException;
}
