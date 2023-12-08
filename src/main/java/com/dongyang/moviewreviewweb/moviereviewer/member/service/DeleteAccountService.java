package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DeleteAccountService {
    void deleteAccount (String memberId) throws SQLException, ClassNotFoundException;
}
