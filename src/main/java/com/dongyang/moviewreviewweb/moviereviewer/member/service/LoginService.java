package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import com.dongyang.moviewreviewweb.moviereviewer.member.entity.Login;
import jakarta.servlet.http.HttpSession;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

public interface LoginService {
    void login (Login loginData, HttpSession httpSession) throws SQLException, ClassNotFoundException, AccessDeniedException;
}
